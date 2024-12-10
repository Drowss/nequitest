package com.drow.nequitest.infrastructure.out.repository;

import com.drow.nequitest.infrastructure.out.entities.FranchiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFranchiseRepository extends JpaRepository<FranchiseEntity, Integer> {
}
