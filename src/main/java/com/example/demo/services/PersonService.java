package com.example.demo.services;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PersonDto;
import com.example.demo.entities.Person;
import com.example.demo.repositories.PersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<PersonDto> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PersonDto getPersonById(Long id) {
        return personRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new java.util.NoSuchElementException("Person not found with id: " + id));
    }

    public PersonDto getPersonByEmail(String email) {
        System.out.println("Searching for person with email: " + email);
        return personRepository.findByEmail(email)
                .map(this::toDto)
                .orElseThrow(() -> new java.util.NoSuchElementException("Person not found with email: " + email));
    }

    public PersonDto createPerson(PersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());
        person.setEmail(personDto.getEmail());
        Person saved = personRepository.save(person);
        return toDto(saved);
    }

    public PersonDto updatePerson(Long id, PersonDto personDto) {
        if (personDto.getId() != null && !personDto.getId().equals(id)) {
            throw new IllegalArgumentException(
                    "id in request body (" + personDto.getId() + ") does not match id in path (" + id + ")");
        }

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new java.util.NoSuchElementException("Person not found with id: " + id));

        BeanUtils.copyProperties(personDto, person, ignoredPropertyNames(personDto, "id"));

        Person saved = personRepository.save(person);
        return toDto(saved);
    }

    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new java.util.NoSuchElementException("Person not found with id: " + id);
        }
        personRepository.deleteById(id);
    }

    /**
     * Returns the set of property names to skip when copying: any property whose
     * value is null on the source, plus any explicitly ignored names (e.g. "id").
     */
    private String[] ignoredPropertyNames(Object source, String... additionalIgnored) {
        BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.concat(
                Stream.of(wrappedSource.getPropertyDescriptors())
                        .map(FeatureDescriptor::getName)
                        .filter(name -> wrappedSource.getPropertyValue(name) == null),
                Stream.of(additionalIgnored))
                .toArray(String[]::new);
    }

    private PersonDto toDto(Person person) {
        return new PersonDto(person.getId(), person.getName(), person.getEmail());
    }
}
