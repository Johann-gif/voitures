package com.example.voitures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/concessionnaires")
public class ConcessionnairesController {
    @Autowired
    private ConcessionnairesRepository concessionnairesRepository;
    @GetMapping
    public List<Concessionnaires> getConcessionnaires() {
        return concessionnairesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Concessionnaires> getConcessionnaire(@PathVariable int id) throws ResourceNotFoundException {
        Concessionnaires i = concessionnairesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Concessionnaire non trouvée :: " + id));
        return ResponseEntity.ok().body(i);
    }

    @PostMapping()
    public ResponseEntity<Concessionnaires> addConcessionnaire (@RequestParam int id, @RequestParam String nom) {
        Concessionnaires i = new Concessionnaires();
        i.setId(id);
        i.setNom(nom);
        concessionnairesRepository.save(i);
        return ResponseEntity.ok().body(i);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConcessionnaire (@PathVariable int id) throws ResourceNotFoundException {
        Concessionnaires i = concessionnairesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Concessionnaire non trouvée :: " + id));
        concessionnairesRepository.delete(i);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Concessionnaires> changeConcessionnaire (@PathVariable int id, @RequestParam String nom) throws ResourceNotFoundException {
        Concessionnaires i = concessionnairesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Concessionnaire non trouvée :: " + id));
        i.setNom(nom);
        concessionnairesRepository.save(i);
        return ResponseEntity.ok().body(i);
    }
}
