package ru.job4j.accidents.service.accidentType;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.accidentType.DataAccidentTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {
    private final DataAccidentTypeRepository accidentTypeRepository;

    @Override
    public List<AccidentType> findAll() {
        return (List<AccidentType>) accidentTypeRepository.findAll();
    }

    @Override
    public AccidentType save(AccidentType type) {
        return accidentTypeRepository.save(type);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepository.findById(id);
    }
}