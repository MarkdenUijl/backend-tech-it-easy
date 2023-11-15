package nl.novi.backendtechiteasy.controllers;

import nl.novi.backendtechiteasy.classes.Television;
import nl.novi.backendtechiteasy.classes.TelevisionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TelevisionsController {
    private Map<Integer, Television> televisions = new HashMap<>();

    @GetMapping("/televisions")
    public ResponseEntity<ArrayList<Television>> getAllTelevisions() {
        ArrayList<Television> televisionList = new ArrayList<>(televisions.values());

        return ResponseEntity.ok(televisionList);
    }

    @GetMapping("/televisions/{serialNumber}")
    public ResponseEntity<Object> getTelevisionBySerialNumber(@PathVariable int serialNumber) {
        if(televisions.containsKey(serialNumber)) {
            return ResponseEntity.ok(televisions.get(serialNumber));
        }

        return ResponseEntity.ok("Television not found.");
    }

    @GetMapping("/televisions/filter")
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

        return ResponseEntity.ok(sortedList);
    }

    @PostMapping("/televisions/store")
    public ResponseEntity<Object> storeTelevision(@RequestBody TelevisionRequest televisionRequest) {
        String name = televisionRequest.getName();
        String type = televisionRequest.getType();
        int serial = televisionRequest.getSerialNumber();

        if (televisions.containsKey(serial)) {
            return ResponseEntity.ok("There is already a television with that serial number in the database.");
        }

        Television television = new Television(name, type, serial);
        televisions.put(serial, television);

        return ResponseEntity.created(null).body("Television created.");
    }

    @PutMapping("/televisions/{serialNumber}/changeName")
    public ResponseEntity<Object> changeTelevisionName(@PathVariable int serialNumber, @RequestParam String name) {
        if(televisions.containsKey(serialNumber)) {
            televisions.get(serialNumber).setName(name);

            return ResponseEntity.ok("Television name changed to " + name);
        }

        return ResponseEntity.ok("Television not found.");
    }

    @PutMapping("/televisions/{serialNumber}/changeType")
    public ResponseEntity<Object> changeTelevisionType(@PathVariable int serialNumber, @RequestParam String type) {
        if(televisions.containsKey(serialNumber)) {
            televisions.get(serialNumber).setType(type);

            return ResponseEntity.ok("Television type changed to " + type);
        }

        return ResponseEntity.ok("Television not found.");
    }

    @DeleteMapping("/televisions/{serialNumber}/delete")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int serialNumber) {
        if(televisions.containsKey(serialNumber)) {
            televisions.remove(serialNumber);

            return ResponseEntity.ok("Television was removed.");
        }

        return ResponseEntity.ok("Television not found.");
    }
}
