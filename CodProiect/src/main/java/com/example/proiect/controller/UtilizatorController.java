/** Clasa pentru operarea controlerului Utilizator
 * @author Ionescu Vlad-Andrei
 * @version 25 Decembrie 2024
 */
package com.example.proiect.controller;

import com.example.proiect.model.Utilizator;
import com.example.proiect.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController //marcheaza clasa ca fiind un controler pentru servicii REST
@RequestMapping("/api/utilizatori") //definește ruta de baza pentru toate endpoint-urile acestui controler
public class UtilizatorController {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    // GET: Toți utilizatorii
    @GetMapping
    public List<Utilizator> getAllUtilizatori() {
        return utilizatorRepository.findAll();
    }

    // GET: Un utilizator după ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilizator> getUtilizatorById(@PathVariable Integer id) {
        Optional<Utilizator> utilizator = utilizatorRepository.findById(id);
        return utilizator.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST: Adaugă un utilizator nou
    @PostMapping
    public ResponseEntity<Utilizator> addUtilizator(@RequestBody Utilizator utilizator) {
        try {
            Utilizator newUtilizator = utilizatorRepository.save(utilizator);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUtilizator);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PUT: Actualizează un utilizator
    @PutMapping("/{id}")
    public ResponseEntity<Utilizator> updateUtilizator(@PathVariable Integer id, @RequestBody Utilizator utilizatorDetails) {
        Optional<Utilizator> existingUtilizator = utilizatorRepository.findById(id);

        if (existingUtilizator.isPresent()) {
            Utilizator utilizatorToUpdate = existingUtilizator.get();
            utilizatorToUpdate.setNume(utilizatorDetails.getNume());
            utilizatorToUpdate.setTip_utilizator(utilizatorDetails.getTip_utilizator());
            utilizatorRepository.save(utilizatorToUpdate);
            return ResponseEntity.ok(utilizatorToUpdate);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE: Șterge un utilizator după ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilizator(@PathVariable Integer id) {
        Optional<Utilizator> utilizator = utilizatorRepository.findById(id);
        if (utilizator.isPresent()) {
            utilizatorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
