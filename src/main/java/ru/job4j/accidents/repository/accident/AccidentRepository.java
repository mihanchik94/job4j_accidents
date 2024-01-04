package ru.job4j.accidents.repository.accident;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository {
    List<Accident> findAll();
    Accident create(Accident accident);
    Optional<Accident> findById(int id);
}