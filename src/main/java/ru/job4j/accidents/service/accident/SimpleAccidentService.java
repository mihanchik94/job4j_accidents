package ru.job4j.accidents.service.accident;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.accident.AccidentJdbcTemplateRepository;
import ru.job4j.accidents.repository.accidentType.AccidentTypeJdbcTemplateRepository;
import ru.job4j.accidents.repository.accidentType.AccidentTypeRepository;
import ru.job4j.accidents.repository.rule.RuleJdbcTemplateRepository;
import ru.job4j.accidents.repository.rule.RuleRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {
    private final AccidentJdbcTemplateRepository accidentRepository;
    private final AccidentTypeJdbcTemplateRepository accidentTypeRepository;
    private final RuleJdbcTemplateRepository ruleRepository;

    private void setAccidentTypesAndRules(Accident accident, List<Integer> rulesIds) {
        int typeId = accident.getType().getId();
        accident.setType(accidentTypeRepository.findById(typeId).get());
        accident.setRules(new HashSet<>(ruleRepository.findRulesByIds(rulesIds)));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentRepository.findAll();
    }

    @Override
    public Accident create(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        return accidentRepository.create(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    @Override
    public Optional<Accident> update(Accident accident, List<Integer> rulesIds) {
        setAccidentTypesAndRules(accident, rulesIds);
        return accidentRepository.update(accident);
    }
}