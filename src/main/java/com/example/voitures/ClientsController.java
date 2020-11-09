package com.example.voitures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientsController {
    @Autowired
    private ClientsRepository clientsRepository;
    @GetMapping
    public List<Clients> getClients() {
        return clientsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clients> getClient(@PathVariable int id) throws ResourceNotFoundException {
        Clients i = clientsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvée :: " + id));
        return ResponseEntity.ok().body(i);
    }

    @PostMapping()
    public ResponseEntity<Clients> addClient (@RequestParam int id, @RequestParam String nom, @RequestParam String prenom) {
        Clients i = new Clients();
        i.setId(id);
        i.setNom(nom);
        i.setPrenom(prenom);
        clientsRepository.save(i);
        return ResponseEntity.ok().body(i);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient (@PathVariable int id) throws ResourceNotFoundException {
        Clients i = clientsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvée :: " + id));
        clientsRepository.delete(i);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Clients> changeClient (@PathVariable int id, @RequestParam String nom, @RequestParam String prenom) throws ResourceNotFoundException {
        Clients i = clientsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvée :: " + id));
        i.setNom(nom);
        i.setPrenom(prenom);
        clientsRepository.save(i);
        return ResponseEntity.ok().body(i);
    }
}
