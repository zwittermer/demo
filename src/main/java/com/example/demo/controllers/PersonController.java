package com.example.demo.controllers;

import java.util.List;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.PersonDto;
import com.example.demo.services.PersonService;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<PersonDto> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto personDto) {
        PersonDto created = personService.createPerson(personDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
