package ru.job4j.accidents.repository.rule;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMemRepository implements RuleRepository {
    private static final AtomicInteger IDS = new AtomicInteger(3);
    private final Map<Integer, Rule> rules;

    public RuleMemRepository() {
        this.rules = new ConcurrentHashMap<>();
        rules.put(1, new Rule(1, "Статья. 1"));
        rules.put(2, new Rule(2, "Статья. 2"));
        rules.put(3, new Rule(3, "Статья. 3"));
    }


    @Override
    public List<Rule> findAll() {
        return new ArrayList<>(rules.values());
    }

    @Override
    public Rule create(Rule rule) {
        if (rule.getId() == 0) {
            int ruleId = IDS.incrementAndGet();
            rule.setId(ruleId);
        }
        return rules.put(rule.getId(), rule);
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(rules.get(id));
    }
}