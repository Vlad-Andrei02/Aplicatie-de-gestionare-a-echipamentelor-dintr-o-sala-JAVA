/**Interfata pentru operatiile de acces la baza de date pentru entitatea Sala.
 * @author Ionescu Vlad-Andrei
 * @version 25 Decembrie 2024
 */
package com.example.proiect.repository;

import com.example.proiect.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {

}
