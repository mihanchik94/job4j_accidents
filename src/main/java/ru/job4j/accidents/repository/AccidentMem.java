package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class AccidentMem implements AccidentRepository {
    private static final AtomicInteger IDS = new AtomicInteger(3);
    private final Map<Integer, Accident> accidentMap;

    public AccidentMem() {
        this.accidentMap = new ConcurrentHashMap<>();
        Accident accident1 = new Accident(1, "name 1", "text 1", "address 1");
        Accident accident2 = new Accident(2, "name 2", "text 2", "address 2");
        Accident accident3 = new Accident(3, "name 3", "text 3", "address 3");
        accidentMap.putIfAbsent(accident1.getId(), accident1);
        accidentMap.putIfAbsent(accident2.getId(), accident2);
        accidentMap.putIfAbsent(accident3.getId(), accident3);
    }

    @Override
    public List<Accident> findAll() {
        return new ArrayList<>(accidentMap.values());
    }

    @Override
    public Accident create(Accident accident) {
        if (accident.getId() == 0) {
            int accidentId = IDS.incrementAndGet();
            accident.setId(accidentId);
        }
        return accidentMap.put(accident.getId(), accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.of(accidentMap.get(id));
    }
}