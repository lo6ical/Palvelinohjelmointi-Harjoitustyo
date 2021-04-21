package js.palvelinohjelmointi.harjoitus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "game_genre_seq")
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    @JsonIgnore
    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Genre() {
        super();
    }

    public Genre(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
