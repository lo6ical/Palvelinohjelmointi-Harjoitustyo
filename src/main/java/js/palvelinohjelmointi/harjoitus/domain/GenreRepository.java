package js.palvelinohjelmointi.harjoitus.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findByName(String string);
    void deleteByName(String string);
}
