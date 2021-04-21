package js.palvelinohjelmointi.harjoitus.web;

import js.palvelinohjelmointi.harjoitus.domain.Game;
import js.palvelinohjelmointi.harjoitus.domain.GameRepository;
import js.palvelinohjelmointi.harjoitus.domain.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class GameController {
    @Autowired
    private GameRepository gamerepository;

    @Autowired
    private GenreRepository genrerepository;

    //fetch all games
    @RequestMapping(value = "/gamelist", method= RequestMethod.GET)
    public String getGames(Model model) {
        model.addAttribute("games", gamerepository.findAll());
        return "gamelist";
    }
    //Rest Json fetch all
    @RequestMapping(value="/games", method = RequestMethod.GET)
    public @ResponseBody
    List<Game> gameListRest() {
        return (List<Game>) gamerepository.findAll();
    }
    //Rest Json fetch by id
    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<Game> findGameRest(@PathVariable("id") Long gameId) {
        return gamerepository.findById(gameId);
    }
    //delete game by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteGame(@PathVariable("id") Long gameId, Model model) {
        gamerepository.deleteById(gameId);
        return "redirect:../gamelist";
    }
    //add a new game
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String addGame(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("genres", genrerepository.findAll());
        return "addgame";
    }
    //save game
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Game game) {
        gamerepository.save(game);
        return "redirect:gamelist";
    }
    //edit game
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", gamerepository.findById(id));
        return "editgame";
    }
    //update game
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateGame(@PathVariable("id") Long gameId, @Validated Game game) {
        gamerepository.save(game);
        return "redirect:gamelist";
    }
}
