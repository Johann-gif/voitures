package com.example.voitures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/marques")
public class MarquesController {
    @Autowired
    private MarquesRepository marquesRepository;
    @GetMapping
    public List<Marques> getMarques() {
        return marquesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marques> getMarque(@PathVariable int id) throws ResourceNotFoundException {
        Marques i = marquesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marque non trouvée :: " + id));
        return ResponseEntity.ok().body(i);
    }

    @PostMapping()
    public ResponseEntity<Marques> addMarque (@RequestParam int id, @RequestParam String nom) {
        Marques i = new Marques();
        i.setId(id);
        i.setNom(nom);
        marquesRepository.save(i);
        return ResponseEntity.ok().body(i);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarque (@PathVariable int id) throws ResourceNotFoundException {
        Marques i = marquesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marque non trouvée :: " + id));
        marquesRepository.delete(i);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Marques> changeMarque (@PathVariable int id, @RequestParam String nom) throws ResourceNotFoundException {
        Marques i = marquesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marque non trouvée :: " + id));
        i.setNom(nom);
        marquesRepository.save(i);
        return ResponseEntity.ok().body(i);
    }
}
