package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "hospital")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Name hospital is required")
    private String nameHospital;

    @ManyToOne
    @JoinColumn(name = "palat_id")
    private Palat palat;

    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;

    public Hospital(long id, String nameHospital, Palat palat, Laboratory laboratory) {
        this.id = id;
        this.nameHospital = nameHospital;
        this.palat = palat;
        this.laboratory = laboratory;
    }

    public Hospital() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameHospital() {
        return nameHospital;
    }

    public void setNameHospital(String nameHospital) {
        this.nameHospital = nameHospital;
    }

    public Palat getPalat() {
        return palat;
    }

    public void setPalat(Palat palat) {
        this.palat = palat;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }
}
