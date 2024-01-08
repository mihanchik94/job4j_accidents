package ru.job4j.accidents.repository.accidentType;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.AccidentType;

public interface DataAccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}