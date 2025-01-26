/** Clasa pentru gestionarea entitatii Utilizator
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

// Clasa ce reprezinta entitatea Utilizator, mapata la tabela "utilizator"
@Entity
@Table(name = "utilizator")
public class Utilizator {

    // Campul primar al entitatii, autogenerat
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utilizator_id")
    private Integer utilizator_id;

    // Coloana "nume", NOT NULL, lungime maxima de 50 caractere
    @Column(name = "nume", nullable = false, length = 50)
    private String nume;

    // Coloana "tip_utilizator", lungime maxima de 50 caractere
    @Column(name = "tip_utilizator", length = 50)
    private String tip_utilizator;

    // Getter pentru utilizator_id
    public Integer getUtilizator_id() {
        return utilizator_id;
    }

    // Setter pentru utilizator_id
    public void setUtilizator_id(Integer utilizator_id) {
        this.utilizator_id = utilizator_id;
    }

    // Getter pentru nume
    public String getNume() {
        return nume;
    }

    // Setter pentru nume
    public void setNume(String nume) {
        this.nume = nume;
    }

    // Getter pentru tip_utilizator
    public String getTip_utilizator() {
        return tip_utilizator;
    }

    // Setter pentru tip_utilizator
    public void setTip_utilizator(String tip_utilizator) {
        this.tip_utilizator = tip_utilizator;
    }
}
