package com.example.api;

import com.example.model.Person;
import com.example.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAllProducts() {
        if (personService.getAllProducts() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getProductById(@PathVariable Long id) {
        if (personService.getProductById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Person> getProductByName(@PathVariable String name) {
        if (personService.getProductByName(name) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personService.getProductByName(name), HttpStatus.OK);
    }

    @PostMapping("/{name}/{balance}")
    public ResponseEntity<Void> createProduct(@PathVariable String name, @PathVariable Double balance) {
        if (name == null || balance < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        personService.addProduct(new Person(name, balance));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (personService.getProductById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/name/{name}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @PathVariable String name) {
        if (personService.getProductById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personService.updateNameById(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/price/{price}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @PathVariable Double price) {
        if (personService.getProductById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personService.updatePriceById(id, price);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}