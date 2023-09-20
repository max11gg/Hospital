package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "palat")
public class Palat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Number Of Bed is required")
    private String numberPalat;

    @ManyToOne
    private Bed bed;

    @OneToMany(mappedBy = "palat")
    private List<Hospital> hospital;

    public Palat(long id, String numberPalat) {
        this.id = id;
        this.numberPalat = numberPalat;
    }

    public Palat() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumberPalat() {
        return numberPalat;
    }

    public void setNumberPalat(String numberPalat) {
        this.numberPalat = numberPalat;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    public List<Hospital> getHospital() {
        return hospital;
    }

    public void setHospital(List<Hospital> hospital) {
        this.hospital = hospital;
    }
}
