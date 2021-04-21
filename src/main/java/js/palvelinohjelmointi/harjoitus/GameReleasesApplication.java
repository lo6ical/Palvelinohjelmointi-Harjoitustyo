package js.palvelinohjelmointi.harjoitus;

import js.palvelinohjelmointi.harjoitus.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class GameReleasesApplication {
	private static final Logger log = LoggerFactory.getLogger(GameReleasesApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GameReleasesApplication.class, args);
	}

	@Bean
	public CommandLineRunner gamereleases(GameRepository gamerepository, GenreRepository genrerepository, UserRepository urepository) {
		return (args) -> {
			Genre ge1 = new Genre("First-person shooter");
			genrerepository.save(ge1);
			Genre ge2 = new Genre("Story");
			genrerepository.save(ge2);
			Genre ge3 = new Genre("MOBA");
			genrerepository.save(ge3);

			gamerepository.save(new Game("Call of Duty: Modern Warfare 2", "Activision", "15.11.2021", ge1));
			gamerepository.save(new Game("Grand Theft Auto VI", "Rockstar Games", "20.7.2021", ge2));
			gamerepository.save(new Game("Dota 3", "Valve", "6.5.2021", ge3));

			User user1 = new User("user", "$2a$10$Or.oucI98vA6bqMrTHdhmeVH7R7isyb2a0txTsYXE/tGy/FWVjmRS", "USER");
			User user2 = new User("admin", "$2a$10$VZcB06V5EX6utiAd5xllz.nb3m3CHb.AtH8uSBPgr37CtBYjFzwBu", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("Fetch all genres");
			for (Genre genre : genrerepository.findAll()) {
				log.info(genre.toString());
			}

			log.info("Fetch all games");
			for (Game game : gamerepository.findAll()) {
				log.info(game.toString());
			}

		};
	}

}
