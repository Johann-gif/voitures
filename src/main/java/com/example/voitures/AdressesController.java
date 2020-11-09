package com.example.voitures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/adresses")
public class AdressesController {
    @Autowired
    private AdressesRepository adressesRepository;
    @GetMapping
    public List<Adresses> getAdresses() {
        return adressesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresses> getAdresse(@PathVariable int id) throws ResourceNotFoundException {
        Adresses i = adressesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse non trouvée :: " + id));
        return ResponseEntity.ok().body(i);
    }

    @PostMapping()
    public ResponseEntity<Adresses> addAdresse (@RequestParam int id, @RequestParam String adresse) {
        Adresses i = new Adresses();
        i.setId(id);
        i.setAdresse(adresse);
        adressesRepository.save(i);
        return ResponseEntity.ok().body(i);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdresse (@PathVariable int id) throws ResourceNotFoundException {
        Adresses i = adressesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse non trouvée :: " + id));
        adressesRepository.delete(i);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Adresses> changeAdresse (@PathVariable int id, @RequestParam String adresse) throws ResourceNotFoundException {
        Adresses i = adressesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adresse non trouvée :: " + id));
        i.setAdresse(adresse);
        adressesRepository.save(i);
        return ResponseEntity.ok().body(i);
    }
}
