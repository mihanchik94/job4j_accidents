package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private final AccidentRepository repository;

    @Override
    public List<Accident> findAll() {
        return repository.findAll();
    }

    @Override
    public void create(Accident accident) {
        repository.create(accident);
    }
}