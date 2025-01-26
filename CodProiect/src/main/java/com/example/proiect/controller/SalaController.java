/** Clasa pentru operarea controlerului Sala
 * @author Ionescu Vlad-Andrei
 * @version 25 Decembrie 2024
 */
package com.example.proiect.controller;

import com.example.proiect.model.Sala;
import com.example.proiect.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //marcheaza clasa ca fiind un controler pentru servicii REST
@RequestMapping("/api/sala") //definește ruta de baza pentru toate endpoint-urile acestui controler
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    // Endpoint pentru a obține toate sălile
    @GetMapping
    public List<Sala> getAllSale() {
        return salaRepository.findAll();
    }

    // Endpoint pentru a obține o sală după ID
    @GetMapping("/{id}")
    public ResponseEntity<Sala> getSalaById(@PathVariable Integer id) {
        Optional<Sala> sala = salaRepository.findById(id);
        return sala.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pentru a adăuga o sală nouă
    @PostMapping
    public ResponseEntity<?> addSala(@RequestBody Sala sala) {
        // Validare: Capacitatea trebuie să fie mai mare decât 0
        if (sala.getCapacitate() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Capacitatea sălii trebuie să fie mai mare decât 0!");
        }

        try {
            Sala newSala = salaRepository.save(sala);
            return ResponseEntity.status(HttpStatus.CREATED).body(newSala);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint pentru a șterge o sală după ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable Integer id) {
        Optional<Sala> sala = salaRepository.findById(id);
        if (sala.isPresent()) {
            salaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint pentru actualizarea unei săli existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSala(@PathVariable Integer id, @RequestBody Sala salaDetails) {
        Optional<Sala> optionalSala = salaRepository.findById(id);

        if (optionalSala.isPresent()) {
            Sala sala = optionalSala.get();

            // Validare: Capacitatea nu trebuie să fie un număr negativ
            if (salaDetails.getCapacitate() < 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Capacitatea sălii nu poate fi un număr negativ!");
            }

            // Actualizează câmpurile sălii existente cu valorile noi
            sala.setNume(salaDetails.getNume() != null ? salaDetails.getNume() : sala.getNume());
            sala.setCapacitate(salaDetails.getCapacitate() != null ? salaDetails.getCapacitate() : sala.getCapacitate());

            // Salvează modificările
            Sala updatedSala = salaRepository.save(sala);
            return ResponseEntity.ok(updatedSala);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Sala cu ID-ul specificat nu a fost găsită.");
        }
    }
}
