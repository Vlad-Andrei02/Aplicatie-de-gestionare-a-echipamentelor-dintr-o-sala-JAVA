/** Clasa pentru gestionarea entitatii Furnizor
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

// Clasa ce reprezinta entitatea Furnizor, mapata la tabela "furnizor"
@Entity
@Table(name = "furnizor")
public class Furnizor {

    // Campul primar al entitatii, autogenerat
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "furnizor_id")
    private Integer furnizor_id;

    // Coloana "nume", NOT NULL, lungime maxima de 50 caractere
    @Column(name = "nume", nullable = false, length = 50)
    private String nume;

    // Coloana "adresa", lungime maxima de 100 caractere
    @Column(name = "adresa", length = 100)
    private String adresa;

    // Coloana "telefon", lungime maxima de 15 caractere
    @Column(name = "telefon", length = 15)
    private String telefon;

    // Getter pentru furnizor_id
    public Integer getFurnizor_id() {
        return furnizor_id;
    }

    // Setter pentru furnizor_id
    public void setFurnizor_id(Integer furnizor_id) {
        this.furnizor_id = furnizor_id;
    }

    // Getter pentru nume
    public String getNume() {
        return nume;
    }

    // Setter pentru nume
    public void setNume(String nume) {
        this.nume = nume;
    }

    // Getter pentru adresa
    public String getAdresa() {
        return adresa;
    }

    // Setter pentru adresa
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    // Getter pentru telefon
    public String getTelefon() {
        return telefon;
    }

    // Setter pentru telefon
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}