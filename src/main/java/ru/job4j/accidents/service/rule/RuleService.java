package ru.job4j.accidents.service.rule;

import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RuleService {
    List<Rule> findAll();
    Rule create(Rule rule);
    Optional<Rule> findById(int id);
    Set<Rule> convertFromStringArrayToRulesSet(String[] ids);
}