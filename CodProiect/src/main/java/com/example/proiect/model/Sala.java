/** Clasa pentru gestionarea entitatii Sala
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

// Clasa ce reprezinta entitatea Sala, mapata la tabela "sala"
@Entity
@Table(name = "sala")
public class Sala {

    // Campul primar al entitatii, autogenerat
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sala_id")
    private Integer sala_id;

    // Coloana "nume", NOT NULL, lungime maxima de 50 caractere
    @Column(name = "nume", nullable = false, length = 50)
    private String nume;

    // Coloana "capacitate", stocheaza capacitatea salii
    @Column(name = "capacitate")
    private Integer capacitate;

    // Getter pentru sala_id
    public Integer getSala_id() {
        return sala_id;
    }

    // Setter pentru sala_id
    public void setSala_id(Integer sala_id) {
        this.sala_id = sala_id;
    }

    // Getter pentru nume
    public String getNume() {
        return nume;
    }

    // Setter pentru nume
    public void setNume(String nume) {
        this.nume = nume;
    }

    // Getter pentru capacitate
    public Integer getCapacitate() {
        return capacitate;
    }

    // Setter pentru capacitate
    public void setCapacitate(Integer capacitate) {
        this.capacitate = capacitate;
    }
}