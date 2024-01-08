package ru.job4j.accidents.repository.rule;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnRuleRepository implements RuleRepository {
    private final CrudRepository crudRepository;

    @Override
    public List<Rule> findAll() {
       return crudRepository.query("from Rule", Rule.class);
    }

    @Override
    public Rule create(Rule rule) {
        try {
            crudRepository.run(session -> session.persist(rule));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rule;
    }

    @Override
    public Optional<Rule> findById(int id) {
        return crudRepository.optional("from Rule where id = :fId", Rule.class,
                Map.of("fId", id));
    }

    @Override
    public List<Rule> findRulesByIds(Collection<Integer> ids) {
        return crudRepository.query("from Rule where id in (:fIds)", Rule.class,
                Map.of("fIds", ids));
    }
}
