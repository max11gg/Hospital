package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "laboratory")
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Name is required")
    private String nameLaboratory;
    @NotEmpty(message = "Name is required")
    private String contract;
    @NotEmpty(message = "Name is required")
    private String profile;

    @OneToMany(mappedBy = "laboratory")
    private List<Hospital> hospital;

    public Laboratory(long id, String nameLaboratory, String contract, String profile) {
        this.id = id;
        this.nameLaboratory = nameLaboratory;
        this.contract = contract;
        this.profile = profile;
    }

    public Laboratory() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameLaboratory() {
        return nameLaboratory;
    }

    public void setNameLaboratory(String nameLaboratory) {
        this.nameLaboratory = nameLaboratory;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<Hospital> getHospital() {
        return hospital;
    }

    public void setHospital(List<Hospital> hospital) {
        this.hospital = hospital;
    }
}
