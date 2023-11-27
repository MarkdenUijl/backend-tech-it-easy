package nl.novi.backendtechiteasy.controllers;

import nl.novi.backendtechiteasy.exceptions.RecordNotFoundException;
import nl.novi.backendtechiteasy.models.Television;
import nl.novi.backendtechiteasy.repositories.TelevisionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {
    @Autowired
    private TelevisionRepository televisionRepository;

    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Boolean wifi,
            @RequestParam(required = false) Boolean voiceControl,
            @RequestParam(required = false) Boolean hdr,
            @RequestParam(required = false) Boolean bluetooth,
            @RequestParam(required = false) Boolean ambiLight
    ) {
        List<Television> televisions = televisionRepository.findAllByFilters(brand, type, wifi, voiceControl, hdr, bluetooth, ambiLight);

        if (televisions.isEmpty()) {
            throw new RecordNotFoundException();
        } else {
            return new ResponseEntity<>(televisions, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable("id") Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException();
        } else {
            return new ResponseEntity<>(television.get(), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {
        televisionRepository.save(television);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + television.getId())
                        .toUriString()
        );

        return ResponseEntity.created(uri).body(television);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> replaceTelevision(@PathVariable("id") Long id, @RequestBody Television newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException();
        } else {
            Television existingTelevision = television.get();

            BeanUtils.copyProperties(newTelevision, existingTelevision, "id");

            televisionRepository.save(existingTelevision);

            return new ResponseEntity<>(existingTelevision, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable("id") Long id) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException();
        } else {
            Television existingTelevision = television.get();

            televisionRepository.delete(existingTelevision);

            return new ResponseEntity<>(existingTelevision, HttpStatus.OK);
        }
    }
}
