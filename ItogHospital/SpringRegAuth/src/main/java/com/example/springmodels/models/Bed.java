package com.example.springmodels.models;

import javax.persistence.*;


import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "bed")
public class Bed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Number Of Bed is required")
    private String numberBed;

    @OneToMany(mappedBy = "bed")
    private List<Palat> palats;

    public Bed(long id, String numberBed) {
        this.id = id;
        this.numberBed = numberBed;
    }

    public Bed() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumberBed() {
        return numberBed;
    }

    public void setNumberBed(String numberBed) {
        this.numberBed = numberBed;
    }

    public List<Palat> getPalats() {
        return palats;
    }

    public void setPalats(List<Palat> palats) {
        this.palats = palats;
    }
}
