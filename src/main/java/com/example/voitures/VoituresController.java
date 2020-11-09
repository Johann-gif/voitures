package com.example.voitures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/voitures")
public class VoituresController {
    @Autowired
    private VoituresRepository voituresRepository;
    @GetMapping
    public List<Voitures> getVoitures() {
        return voituresRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voitures> getVoiture(@PathVariable int id) throws ResourceNotFoundException {
        Voitures i = voituresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture non trouvée :: " + id));
        return ResponseEntity.ok().body(i);
    }

    @PostMapping()
    public ResponseEntity<Voitures> addVoiture (@RequestParam int id, @RequestParam String serie/*, @RequestParam int marques_id, @RequestParam int clients_id*/) {
        Voitures i = new Voitures();
        i.setId(id);
        i.setSerie(serie);
/*        i.setMarques_id(marques_id);
        i.setClients_id(clients_id);*/
        voituresRepository.save(i);
        return ResponseEntity.ok().body(i);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiture (@PathVariable int id) throws ResourceNotFoundException {
        Voitures i = voituresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture non trouvée :: " + id));
        voituresRepository.delete(i);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Voitures> changeVoiture (@PathVariable int id, @RequestParam String serie) throws ResourceNotFoundException {
        Voitures i = voituresRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voiture non trouvée :: " + id));
        i.setSerie(serie);
        voituresRepository.save(i);
        return ResponseEntity.ok().body(i);
    }
}
