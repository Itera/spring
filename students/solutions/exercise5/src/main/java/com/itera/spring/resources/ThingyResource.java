package com.itera.spring.resources;

import com.itera.spring.models.Thingy;
import com.itera.spring.services.ThingyService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ThingyResource {
    private final ThingyService thingyService;

    public ThingyResource(ThingyService thingyService) {
        this.thingyService = thingyService;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Thingy> allTheThings() {
        return thingyService.allThingies();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Thingy justTheThing(@PathVariable Integer id) {
        Optional<Thingy> thingy = thingyService.oneThingy(id);

        if (thingy.isPresent()) {
            return thingy.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Thingy not found");
        }
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Thingy oneMoreThing(@RequestBody Thingy thingy) {
        Optional<Thingy> newThingy = thingyService.addThingy(thingy);

        if (newThingy.isPresent()) {
            return newThingy.get();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Thingy already exists");
        }
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<Void> oneLessThing(@RequestParam Integer id) {
        Optional<Thingy> thingy = thingyService.deleteThingy(id);

        if (thingy.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Thingy did not exist");
        }
    }
}
