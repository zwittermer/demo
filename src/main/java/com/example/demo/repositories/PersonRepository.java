package com.example.demo.repositories;

import com.example.demo.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

    /**
     * Groups persons by their email domain and counts how many persons belong to
     * each domain. This requires a GROUP BY clause combined with an aggregate
     * function (COUNT) and string manipulation (SUBSTRING/LOCATE), none of which
     * can be expressed via a Spring Data derived (findBy...) query method.
     */
    @Query("SELECT SUBSTRING(p.email, LOCATE('@', p.email) + 1) AS domain, COUNT(p) AS total " +
           "FROM Person p " +
           "GROUP BY SUBSTRING(p.email, LOCATE('@', p.email) + 1) " +
           "ORDER BY total DESC")
    List<EmailDomainCount> countPersonsByEmailDomain();

    interface EmailDomainCount {
        String getDomain();
        Long getTotal();
    }

}
