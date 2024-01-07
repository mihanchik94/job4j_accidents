package ru.job4j.accidents.repository.accident;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.CrudRepository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnAccidentRepository implements AccidentRepository {
    private final CrudRepository crudRepository;

    @Override
    public Collection<Accident> findAll() {
        return crudRepository.query("from Accident a join fetch a.rules order by a.id", Accident.class);
    }

    @Override
    public Accident create(Accident accident) {
        try {
            crudRepository.run(session -> session.persist(accident));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accident;
    }

    @Override
    public Optional<Accident> findById(int id) {
       return crudRepository.optional("from Accident a join fetch a.rules where a.id = :fId", Accident.class,
               Map.of("fId", id));
    }

    @Override
    public Optional<Accident> update(Accident accident) {
        try {
            crudRepository.run(session -> session.update(accident));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(accident);
    }
}