package com.example.service;

import com.example.model.Person;
import com.example.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> getAllProducts() {
        return personRepository.findAll();
    }

    public Person getProductById(Long id) {
        return personRepository.findById(id).get();
    }

    public Person getProductByName(String name) {
        return personRepository.findByName(name);
    }

    public Person addProduct(Person product) {
        return personRepository.save(product);
    }

    public void deleteProduct(Long id) {
        personRepository.deleteById(id);
    }

    public void updateNameById(Long id, String name) {
        personRepository.updateNameById(id, name);
    }

    public void updatePriceById(Long id, Double price) {
        personRepository.updatePriceById(id, price);
    }

}