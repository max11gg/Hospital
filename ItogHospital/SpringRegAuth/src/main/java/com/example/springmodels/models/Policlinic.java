package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "policlinic")
public class Policlinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Name hospital is required")
    private String nameHospital;
    @NotEmpty(message = "Store Address is required")
    private String address;

    public Policlinic(long id, String nameHospital, String address) {
        this.id = id;
        this.nameHospital = nameHospital;
        this.address = address;
    }

    public Policlinic() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
