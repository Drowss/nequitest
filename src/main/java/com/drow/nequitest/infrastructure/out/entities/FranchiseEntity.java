package com.drow.nequitest.infrastructure.out.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "franchises")
public class FranchiseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "franchises")
    private List<BranchEntity> branches;

    public FranchiseEntity() {
    }

    public FranchiseEntity(Integer id, String name, List<BranchEntity> branches) {
        this.id = id;
        this.name = name;
        this.branches = branches;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BranchEntity> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchEntity> branches) {
        this.branches = branches;
    }
}
