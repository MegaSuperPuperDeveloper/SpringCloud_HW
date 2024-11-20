package com.example.repository;

import com.example.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Person a SET a.name = :name WHERE a.id = :id")
    void updateNameById(Long id, String name);

    @Transactional
    @Modifying
    @Query("UPDATE Person a SET a.balance = :balance WHERE a.id = :id")
    void updatePriceById(Long id, Double balance);

    @Query("SELECT a FROM Person a WHERE a.name = :name")
    Person findByName(String name);

}