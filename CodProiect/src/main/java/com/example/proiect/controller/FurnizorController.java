/** Clasa pentru operarea controlerului Furnizor
 * @author Ionescu Vlad-Andrei
 * @version 25 Decembrie 2024
 */
package com.example.proiect.controller;

import com.example.proiect.model.Furnizor;
import com.example.proiect.repository.FurnizorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // marcheaza clasa ca fiind un controler pentru servicii REST
@RequestMapping("/api/furnizori") // defineste ruta de baza pentru toate endpoint-urile acestui controler
public class FurnizorController {

    @Autowired
    private FurnizorRepository furnizorRepository;

    // GET: Toti furnizorii
    @GetMapping
    public List<Furnizor> getAllFurnizori() {
        return furnizorRepository.findAll();
    }

    // GET: Un furnizor dupa ID
    @GetMapping("/{id}")
    public ResponseEntity<Furnizor> getFurnizorById(@PathVariable Integer id) {
        Optional<Furnizor> furnizor = furnizorRepository.findById(id);
        return furnizor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST: Adauga un furnizor nou
    @PostMapping
    public ResponseEntity<?> addFurnizor(@RequestBody Furnizor furnizor) {
        String telefonPattern = "^07\\d{8}$"; // verifica daca telefonul respecta formatul corect
        if (!furnizor.getTelefon().matches(telefonPattern)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Numarul de telefon este invalid! Formatul trebuie sa fie 07********.");
        }

        try {
            Furnizor newFurnizor = furnizorRepository.save(furnizor);
            return ResponseEntity.status(HttpStatus.CREATED).body(newFurnizor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Eroare la adaugarea furnizorului!");
        }
    }

    // PUT: Actualizeaza un furnizor
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFurnizor(@PathVariable Integer id, @RequestBody Furnizor furnizorDetails) {
        Optional<Furnizor> existingFurnizor = furnizorRepository.findById(id);

        if (existingFurnizor.isPresent()) {
            String telefonPattern = "^07\\d{8}$"; // verifica daca telefonul respecta formatul corect
            if (!furnizorDetails.getTelefon().matches(telefonPattern)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Numarul de telefon este invalid! Formatul trebuie sa fie 07********.");
            }

            Furnizor furnizorToUpdate = existingFurnizor.get();
            furnizorToUpdate.setNume(furnizorDetails.getNume());
            furnizorToUpdate.setAdresa(furnizorDetails.getAdresa());
            furnizorToUpdate.setTelefon(furnizorDetails.getTelefon());

            furnizorRepository.save(furnizorToUpdate);
            return ResponseEntity.ok(furnizorToUpdate);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE: Sterge un furnizor dupa ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFurnizor(@PathVariable Integer id) {
        Optional<Furnizor> furnizor = furnizorRepository.findById(id);
        if (furnizor.isPresent()) {
            furnizorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
