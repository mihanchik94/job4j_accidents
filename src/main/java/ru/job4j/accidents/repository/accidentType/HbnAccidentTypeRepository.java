package ru.job4j.accidents.repository.accidentType;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnAccidentTypeRepository implements AccidentTypeRepository {
    private final CrudRepository crudRepository;

    @Override
    public List<AccidentType> findAll() {
        return crudRepository.query("from AccidentType", AccidentType.class);
    }

    @Override
    public AccidentType save(AccidentType type) {
        try {
            crudRepository.run(session -> session.persist(type));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
       return crudRepository.optional("from AccidentType where id = :fId", AccidentType.class,
               Map.of("fId", id));
    }
}