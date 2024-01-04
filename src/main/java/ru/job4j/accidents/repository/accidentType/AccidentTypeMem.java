package ru.job4j.accidents.repository.accidentType;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@AllArgsConstructor
public class AccidentTypeMem implements AccidentTypeRepository {
    private static final AtomicInteger IDS = new AtomicInteger(3);
    private final Map<Integer, AccidentType> types;

    public AccidentTypeMem() {
        this.types = new ConcurrentHashMap<>();
        types.put(1, new AccidentType(1, "Две машины"));
        types.put(2, new AccidentType(2, "Машина и человек"));
        types.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    @Override
    public List<AccidentType> findAll() {
        return new ArrayList<>(types.values());
    }

    @Override
    public AccidentType save(AccidentType type) {
        if (type.getId() == 0) {
            int accidentId = IDS.incrementAndGet();
            type.setId(accidentId);
        }
        return types.put(type.getId(), type);
    }


    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(types.get(id));
    }
}