package ru.job4j.accidents.service.rule;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RuleService {
    List<Rule> findAll();
    Rule create(Rule rule);
    Optional<Rule> findById(int id);
    List<Rule> findRulesByIds(Collection<Integer> ids);
}