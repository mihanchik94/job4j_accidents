package ru.job4j.accidents.service.rule;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.rule.RuleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleRuleService implements RuleService {
    private final RuleRepository ruleRepository;


    @Override
    public List<Rule> findAll() {
        return ruleRepository.findAll();
    }

    @Override
    public Rule create(Rule rule) {
        return ruleRepository.create(rule);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleRepository.findById(id);
    }

    @Override
    public Set<Rule> convertFromStringArrayToRulesSet(String[] ids) {
        return Arrays.stream(ids)
                .map(value -> ruleRepository.findById(Integer.parseInt(value)).get())
                .collect(Collectors.toSet());
    }
}