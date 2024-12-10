package com.drow.nequitest.infrastructure.out.repository;

import com.drow.nequitest.infrastructure.out.entities.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBranchRepository extends JpaRepository<BranchEntity, Integer> {
}
