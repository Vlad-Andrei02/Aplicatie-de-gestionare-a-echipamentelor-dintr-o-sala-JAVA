/** Clasa pentru operarea controlerului Echipament
 * @author Ionescu Vlad-Andrei
 * @version 25 Decembrie 2024
 */
package com.example.proiect.controller;

import com.example.proiect.model.Echipament;
import com.example.proiect.repository.EchipamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // marcheaza clasa ca fiind un controler pentru servicii REST
@RequestMapping("/api/echipamente") // defineste ruta de baza pentru toate endpoint-urile acestui controler
public class EchipamentController {

    @Autowired
    private EchipamentRepository echipamentRepository;

    // GET: Toate echipamentele
    @GetMapping
    public List<Echipament> getAllEchipamente() {
        return echipamentRepository.findAll();
    }

    // GET: Un echipament dupa ID
    @GetMapping("/{id}")
    public ResponseEntity<Echipament> getEchipamentById(@PathVariable Integer id) {
        Optional<Echipament> echipament = echipamentRepository.findById(id);
        return echipament.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST: Adauga un echipament nou
    @PostMapping
    public ResponseEntity<Echipament> addEchipament(@RequestBody Echipament echipament) {
        try {
            Echipament newEchipament = echipamentRepository.save(echipament);
            return ResponseEntity.status(HttpStatus.CREATED).body(newEchipament);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PUT: Actualizeaza un echipament
    @PutMapping("/{id}")
    public ResponseEntity<Echipament> updateEchipament(@PathVariable Integer id, @RequestBody Echipament echipamentDetails) {
        Optional<Echipament> existingEchipament = echipamentRepository.findById(id);

        if (existingEchipament.isPresent()) {
            Echipament echipamentToUpdate = existingEchipament.get();
            echipamentToUpdate.setNume(echipamentDetails.getNume());

            echipamentRepository.save(echipamentToUpdate);
            return ResponseEntity.ok(echipamentToUpdate);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE: Sterge un echipament dupa ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEchipament(@PathVariable Integer id) {
        Optional<Echipament> echipament = echipamentRepository.findById(id);
        if (echipament.isPresent()) {
            echipamentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
