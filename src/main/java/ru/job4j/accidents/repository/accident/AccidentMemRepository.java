package ru.job4j.accidents.repository.accident;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class AccidentMemRepository implements AccidentRepository {
    private static final AtomicInteger IDS = new AtomicInteger(3);
    private final Map<Integer, Accident> accidentMap;

    public AccidentMemRepository() {
        this.accidentMap = new ConcurrentHashMap<>();
        Accident accident1 = new Accident(1, "name 1", "text 1", "address 1",
                new AccidentType(1, "Две машины"), Set.of(new Rule(1, "Статья. 1")));
        Accident accident2 = new Accident(2, "name 2", "text 2", "address 2",
                new AccidentType(2, "Машина и человек"), Set.of(new Rule(1, "Статья. 1"), new Rule(3, "Статья. 3")));
        Accident accident3 = new Accident(3, "name 3", "text 3", "address 3",
                new AccidentType(3, "Машина и велосипед"), Set.of(new Rule(2, "Статья. 2")));
        accidentMap.putIfAbsent(accident1.getId(), accident1);
        accidentMap.putIfAbsent(accident2.getId(), accident2);
        accidentMap.putIfAbsent(accident3.getId(), accident3);
    }

    @Override
    public Collection<Accident> findAll() {
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

    @Override
    public Optional<Accident> update(Accident accident) {
        Accident updatedAccident = accidentMap.computeIfPresent(accident.getId(), (id, oldAccident) ->
                new Accident(oldAccident.getId(), accident.getName(), accident.getText(), accident.getAddress(), accident.getType(), accident.getRules()));
        return Optional.ofNullable(updatedAccident);
    }
}















