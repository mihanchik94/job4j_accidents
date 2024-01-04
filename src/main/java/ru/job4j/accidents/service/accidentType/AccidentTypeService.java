package ru.job4j.accidents.service.accidentType;

import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

public interface AccidentTypeService {
    List<AccidentType> findAll();
    AccidentType save(AccidentType type);
    Optional<AccidentType> findById(int id);
}