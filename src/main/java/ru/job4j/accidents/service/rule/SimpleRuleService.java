package ru.job4j.accidents.service.rule;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.rule.DataRuleRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {
    private final DataRuleRepository ruleRepository;


    @Override
    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }

    @Override
    public Rule create(Rule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

    @Override
    public List<Rule> findRulesByIds(Collection<Integer> ids) {
        return ruleRepository.findAllById(ids);
    }
}