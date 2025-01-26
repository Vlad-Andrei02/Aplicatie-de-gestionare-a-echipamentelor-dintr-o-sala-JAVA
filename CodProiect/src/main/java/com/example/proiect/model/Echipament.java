/** Clasa pentru gestionarea entitatii Echipament
 * @author Ionescu Vlad-Andrei
 * @version 25 Decembrie 2024
 */
package com.example.proiect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

// Clasa ce reprezinta entitatea Echipament, mapata la tabela "echipament"
@Entity
@Table(name = "echipament")
public class Echipament {

    // Campul primar al entitatii, autogenerat
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "echipament_id")
    private Integer echipament_id;

    // Coloana "nume", NOT NULL, lungime maxima de 50 caractere
    @Column(name = "nume", nullable = false, length = 50)
    private String nume;

    // Getter pentru echipament_id
    public Integer getEchipament_id() {
        return echipament_id;
    }

    // Setter pentru echipament_id
    public void setEchipament_id(Integer echipament_id) {
        this.echipament_id = echipament_id;
    }

    // Getter pentru nume
    public String getNume() {
        return nume;
    }

    // Setter pentru nume
    public void setNume(String nume) {
        this.nume = nume;
    }
}
