package ru.job4j.accidents.repository.rule;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RuleRepository {
    List<Rule> findAll();
    Rule create(Rule rule);
    Optional<Rule> findById(int id);
    List<Rule> findRulesByIds(Collection<Integer> ids);
}