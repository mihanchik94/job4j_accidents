package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

@Service
public interface AccidentService {
    List<Accident> findAll();
    Accident create(Accident accident);
    Optional<Accident> findById(int id);
}