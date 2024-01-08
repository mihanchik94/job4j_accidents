package ru.job4j.accidents.service.accident;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.accident.DataAccidentRepository;
import ru.job4j.accidents.repository.accidentType.DataAccidentTypeRepository;
import ru.job4j.accidents.repository.rule.DataRuleRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private final DataAccidentRepository accidentRepository;
    private final DataAccidentTypeRepository accidentTypeRepository;
    private final DataRuleRepository ruleRepository;

    private void setAccidentTypesAndRules(Accident accident, List<Integer> rulesIds) {
        int typeId = accident.getType().getId();
        accident.setType(accidentTypeRepository.findById(typeId).get());
        accident.setRules(new HashSet<>(ruleRepository.findAllById(rulesIds)));
    }

    @Override
    public Collection<Accident> findAll() {
        return (Collection<Accident>) accidentRepository.findAll();
    }

    @Override
    public Accident create(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        return accidentRepository.save(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public Optional<Accident> update(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        return Optional.of(accidentRepository.save(accident));
    }
}