package ru.job4j.accidents.repository.rule;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplateRepository implements RuleRepository {
    private JdbcTemplate jdbc;

    @Override
    public List<Rule> findAll() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt("id"));
            rule.setName(rs.getString("name"));
            return rule;
        });
    }

    @Override
    public Rule create(Rule rule) {
        jdbc.update("insert into rules (name) values (?)", rule.getName());
        return rule;
    }

    @Override
    public Optional<Rule> findById(int id) {
        Rule rule = jdbc.queryForObject("select id, name from rules where id = ?",
                (rs, row) -> {
            Rule findingRule = new Rule();
            findingRule.setId(rs.getInt("id"));
            findingRule.setName(rs.getString("name"));
            return findingRule;
            }, id);
        return Optional.ofNullable(rule);
    }

    @Override
    public List<Rule> findRulesByIds(Collection<Integer> ids) {
        String query = "select * from rules where id IN (" + ids.stream().map(Object::toString).
                collect(Collectors.joining(", ")) + ")";
        return new ArrayList<>(
                jdbc.query(query, (rs, row) -> new Rule(rs.getInt("id"), rs.getString("name")))
        );
    }
}