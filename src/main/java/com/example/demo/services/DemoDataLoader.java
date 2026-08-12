package com.example.demo.services;

import com.example.demo.entities.Person;
import com.example.demo.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DemoDataLoader implements CommandLineRunner {

    private final PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        personRepository.save(new Person(null, "Alice", "alice@example.com"));
        personRepository.save(new Person(null, "Bob", "bob@example.com"));

        System.out.println("[DemoDataLoader] Persons saved: " + personRepository.count());
        personRepository.findAll().forEach(p -> System.out.println("[DemoDataLoader] " + p));
    }
}
