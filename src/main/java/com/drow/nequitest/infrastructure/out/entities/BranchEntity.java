package com.drow.nequitest.infrastructure.out.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "branches")
public class BranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "branches")
    private List<ProductEntity> products;
    @ManyToMany
    @JoinTable(
            name = "branch_franchise",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "franchise_id")
    )
    private List<FranchiseEntity> franchises;

    public BranchEntity() {
    }

    public BranchEntity(Integer id, String name, List<ProductEntity> products) {
        this.id = id;
        this.name = name;
        this.products = products;
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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public List<FranchiseEntity> getFranchises() {
        return franchises;
    }

    public void setFranchises(List<FranchiseEntity> franchises) {
        this.franchises = franchises;
    }
}
