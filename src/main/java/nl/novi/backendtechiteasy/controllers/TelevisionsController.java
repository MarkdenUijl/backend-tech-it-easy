package nl.novi.backendtechiteasy.controllers;

import nl.novi.backendtechiteasy.classes.Television;
import nl.novi.backendtechiteasy.exceptions.BadRequestException;
import nl.novi.backendtechiteasy.exceptions.ConflictException;
import nl.novi.backendtechiteasy.exceptions.InvalidNameException;
import nl.novi.backendtechiteasy.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {
    private Map<Integer, Television> televisions = new HashMap<>();

    @GetMapping
    public ResponseEntity<ArrayList<Television>> getAllTelevisions() {
        ArrayList<Television> televisionList = new ArrayList<>(televisions.values());

        if (televisionList.size() > 0) {
            return new ResponseEntity<>(televisionList, HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("There are no televisions in the database.");
        }
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<Object> getTelevisionBySerialNumber(@PathVariable int serialNumber) {
        if(televisions.containsKey(serialNumber)) {
            return new ResponseEntity<>(televisions.get(serialNumber), HttpStatus.OK);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<ArrayList<Television>> getTelevisionByFilter(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String name) {

        ArrayList<Television> sortedList = new ArrayList<>();

        for (Television television : televisions.values()) {
            if ((type == null || television.getType().equals(type)) &&
                    (name == null || television.getName().equals(name))) {
                sortedList.add(television);
            }
        }

        if (sortedList.size() > 0) {
            return new ResponseEntity<>(sortedList, HttpStatus.OK);
        } else {
            throw new RecordNotFoundException("No televisions with these parameters found.");
        }
    }

    @PostMapping("/store")
    public ResponseEntity<Object> storeTelevision(@RequestBody Television television) {
        int serial = television.getSerialNumber();

        if (television.getName().length() > 20) {
            throw new InvalidNameException("This name is too long.");
        }

        if (televisions.containsKey(serial)) {
            throw new ConflictException("There is already a television with that serial number.");
        }

        televisions.put(serial, television);

        return new ResponseEntity(television, HttpStatus.CREATED);
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<Object> changeTelevision(@PathVariable int serialNumber, @RequestBody Television television) {
        int newSerialNumber = television.getSerialNumber();

        if (serialNumber != newSerialNumber) {
            throw new BadRequestException("New serial number does not match old serial number.");
        }

        if(televisions.containsKey(serialNumber)) {
            televisions.replace(serialNumber, television);

            return new ResponseEntity(television, HttpStatus.OK);
        } else {
            throw new RecordNotFoundException();
        }
    }

    @DeleteMapping("/{serialNumber}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int serialNumber) {
        if(televisions.containsKey(serialNumber)) {
            televisions.remove(serialNumber);

            return new ResponseEntity<>("Television was removed", HttpStatus.OK);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
