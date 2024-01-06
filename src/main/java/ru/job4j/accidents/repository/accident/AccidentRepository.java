package ru.job4j.accidents.repository.accident;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRepository {
    Collection<Accident> findAll();
    Accident create(Accident accident);
    Optional<Accident> findById(int id);
    Optional<Accident> update(Accident accident);
}