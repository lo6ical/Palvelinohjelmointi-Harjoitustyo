package js.palvelinohjelmointi.harjoitus.web;

import js.palvelinohjelmointi.harjoitus.domain.Game;
import js.palvelinohjelmointi.harjoitus.domain.Genre;
import js.palvelinohjelmointi.harjoitus.domain.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class GenreController {
    @Autowired
    private GenreRepository genrerepository;

    //fetch all genres
    @RequestMapping("/genrelist")
    public String genreList(Model model) {
        model.addAttribute("genres", genrerepository.findAll());
        return "genrelist";
    }

    //Rest Json fetch all
    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public @ResponseBody
    List<Genre> genreListRest() {
        return (List<Genre>) genrerepository.findAll();
    }

    //Rest Json fetch by id
    @RequestMapping(value = "/genres/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Genre> findGenreRest(@PathVariable("id") Long genreId) {
        return genrerepository.findById(genreId);
    }

    //delete by id
    @RequestMapping(value = "/deletegenre/{id}", method = RequestMethod.GET)
    public String deleteGenre(@PathVariable("id") Long genreId, Model model) {
        genrerepository.deleteById(genreId);
        return "redirect:../genrelist";
    }
    //add genre
    @RequestMapping(value = "/addgenre")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "addgenre";
    }
    //save genre
    @RequestMapping(value = "/savegenre", method = RequestMethod.POST)
    public String save(Genre genre) {
        genrerepository.save(genre);
        return "redirect:genrelist";
    }

}
