package js.palvelinohjelmointi.harjoitus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "game_seq")
    private Long id;
    private String title;
    private String publisher;
    private String date;

    @ManyToOne
    @JsonIgnoreProperties("games")
    @JoinColumn(name = "")
    private Genre genre;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Game() {
        super();
        this.title = null;
        this.publisher = null;
        this.date = null;
        this.genre = null;
    }

    public Game(String title, String publisher, String date, Genre genre) {
        super();
        this.title = title;
        this.publisher = publisher;
        this.date = date;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }
}
