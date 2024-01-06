package ru.job4j.accidents.repository.accidentType;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplateRepository implements AccidentTypeRepository {
    private final JdbcTemplate jdbc;

    @Override
    public List<AccidentType> findAll() {
        return jdbc.query("select id, name from accident_types",
                (rs, row) -> {
            AccidentType accidentType = new AccidentType();
            accidentType.setId(rs.getInt("id"));
            accidentType.setName(rs.getString("name"));
            return accidentType;
        });
    }

    @Override
    public AccidentType save(AccidentType type) {
        jdbc.update("insert into accident_types (name) values(?)", type.getName());
        return type;
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        AccidentType type = jdbc.queryForObject(
                "select id, name from accident_types where id = ?",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                }, id);
        return Optional.ofNullable(type);
    }
}