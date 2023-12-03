package nl.novi.backendtechiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.backendtechiteasy.dtos.TelevisionDto;
import nl.novi.backendtechiteasy.dtos.TelevisionInputDto;
import nl.novi.backendtechiteasy.services.TelevisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionsController {
    private final TelevisionService service;

    public TelevisionsController(TelevisionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        return new ResponseEntity<>(service.getAllTelevisions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevisionById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getTelevisionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addTelevision(@Valid @RequestBody TelevisionInputDto televisionDto, BindingResult br) {

        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }
        else {
            service.storeTelevision(televisionDto);

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + televisionDto.id)
                            .toUriString()
            );

            return ResponseEntity.created(uri).body(televisionDto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> replaceTelevision(@PathVariable("id") Long id, @RequestBody TelevisionInputDto newTelevision) {
        return new ResponseEntity<>(service.changeTelevision(id, newTelevision), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TelevisionDto> deleteTelevision(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.deleteTelevision(id), HttpStatus.OK);
    }
}
