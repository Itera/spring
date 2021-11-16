package com.itera.spring.services;

import com.itera.spring.models.Thingy;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class ThingyService {

    private final List<Thingy> dataStore = Stream.of(
        new Thingy(1, "Thing 1"),
        new Thingy(2, "Thing 2"),
        new Thingy(3, "Thing 3"),
        new Thingy(4, "Thing 4")
    ).collect(Collectors.toList());

    public List<Thingy> allThingies() {
        return dataStore;
    }

    public Optional<Thingy> oneThingy(Integer id) {
        return findThingyById(id);
    }

    public Optional<Thingy> addThingy(Thingy thingy) {
        if (findThingyById(thingy.getId()).isPresent()) {
            return Optional.empty();
        } else {
            dataStore.add(thingy);
            return Optional.of(thingy);
        }
    }

    public Optional<Thingy> deleteThingy(Integer id) {
        Optional<Thingy> thingy = findThingyById(id);

        if (thingy.isPresent()) {
            dataStore.remove(thingy.get());
            return thingy;
        } else {
            return Optional.empty();
        }
    }

    private Optional<Thingy> findThingyById(Integer id) {
        return dataStore.stream().filter(thingy -> thingy.getId().equals(id)).findFirst();
    }
}
