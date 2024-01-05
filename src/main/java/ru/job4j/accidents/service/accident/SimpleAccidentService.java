package ru.job4j.accidents.service.accident;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.accident.AccidentRepository;
import ru.job4j.accidents.repository.accidentType.AccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private final AccidentRepository repository;
    private final AccidentTypeRepository types;

    @Override
    public List<Accident> findAll() {
        return repository.findAll();
    }

    @Override
    public Accident create(Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(types.findById(typeId).get());
        return repository.create(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Accident> update(Accident accident) {
        int typeId = accident.getType().getId();
        accident.setType(types.findById(typeId).get());
        return repository.update(accident);
    }
}