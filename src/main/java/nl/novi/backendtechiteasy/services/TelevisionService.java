package nl.novi.backendtechiteasy.services;

import nl.novi.backendtechiteasy.dtos.TelevisionDto;
import nl.novi.backendtechiteasy.dtos.TelevisionInputDto;
import nl.novi.backendtechiteasy.exceptions.RecordNotFoundException;
import nl.novi.backendtechiteasy.models.Television;
import nl.novi.backendtechiteasy.repositories.TelevisionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository repos;

    public TelevisionService (TelevisionRepository repos) {
        this.repos = repos;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> televisions = repos.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        if (televisions.isEmpty()) {
            throw new RecordNotFoundException();
        }

        for (Television t : televisions) {
            televisionDtos.add(TelevisionDto.fromTelevision(t));
        }

        return televisionDtos;
    }

    public TelevisionDto getTelevisionById(Long id) {
        Optional<Television> television = repos.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException();
        } else {
            return TelevisionDto.fromTelevision(television.get());
        }
    }

    public TelevisionDto storeTelevision(TelevisionInputDto televisionInputDto) {
        Television television = TelevisionInputDto.toTelevision(televisionInputDto);
        repos.save(television);

        return TelevisionDto.fromTelevision(television);
    }

    public TelevisionDto deleteTelevision(Long id) {
        Optional<Television> television = repos.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException();
        } else {
            Television existingTelevision = television.get();

            repos.delete(existingTelevision);

            return TelevisionDto.fromTelevision(existingTelevision);
        }
    }

    public TelevisionDto changeTelevision(Long id, TelevisionInputDto newTelevision) {
        Optional<Television> television = repos.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException();
        } else {
            Television existingTelevision = television.get();

            BeanUtils.copyProperties(newTelevision, existingTelevision, "id");

            repos.save(existingTelevision);

            return TelevisionDto.fromTelevision(existingTelevision);
        }
    }
}
