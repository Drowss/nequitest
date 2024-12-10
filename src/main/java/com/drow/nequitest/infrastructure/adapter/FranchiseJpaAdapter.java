package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.domain.model.FranchiseModel;
import com.drow.nequitest.domain.spi.IFranchisePersistencePort;
import com.drow.nequitest.infrastructure.out.entities.FranchiseEntity;
import com.drow.nequitest.infrastructure.out.repository.IFranchiseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FranchiseJpaAdapter implements IFranchisePersistencePort {

    private final IFranchiseRepository franchiseRepository;
    private final ModelMapper modelMapper;

    public FranchiseJpaAdapter(IFranchiseRepository franchiseRepository, ModelMapper modelMapper) {
        this.franchiseRepository = franchiseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createFranchise(FranchiseModel franchiseModel) {
        franchiseRepository.save(modelMapper.map(franchiseModel, FranchiseEntity.class));
    }
}
