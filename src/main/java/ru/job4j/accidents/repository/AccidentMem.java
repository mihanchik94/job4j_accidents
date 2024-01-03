package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@AllArgsConstructor
public class AccidentMem implements AccidentRepository {
    private final Map<Integer, Accident> accidentMap = new ConcurrentHashMap<>();

    @Override
    public List<Accident> findAll() {
        inputIntoMemStorage();
        return new ArrayList<>(accidentMap.values());
    }

    @Override
    public void create(Accident accident) {
        accidentMap.putIfAbsent(accident.getId(), accident);
    }

    private void inputIntoMemStorage() {
        Accident accident1 = new Accident(1, "name 1", "text 1", "address 1");
        Accident accident2 = new Accident(2, "name 2", "text 2", "address 2");
        Accident accident3 = new Accident(3, "name 3", "text 3", "address 3");
        accidentMap.putIfAbsent(accident1.getId(), accident1);
        accidentMap.putIfAbsent(accident2.getId(), accident2);
        accidentMap.putIfAbsent(accident3.getId(), accident3);
    }
}