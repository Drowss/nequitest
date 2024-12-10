package com.drow.nequitest.infrastructure.out.repository;

import com.drow.nequitest.infrastructure.out.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {
}
