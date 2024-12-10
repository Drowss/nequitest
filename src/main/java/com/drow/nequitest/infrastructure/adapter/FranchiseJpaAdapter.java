package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.application.exceptionhandler.exceptions.FranchisesException;
import com.drow.nequitest.domain.model.FranchiseModel;
import com.drow.nequitest.domain.spi.IFranchisePersistencePort;
import com.drow.nequitest.infrastructure.out.entities.FranchiseEntity;
import com.drow.nequitest.infrastructure.out.repository.IFranchiseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.drow.nequitest.core.Constants.FRANCHISE_NOT_FOUND;
import static com.drow.nequitest.core.Constants.PRODUCT_NOT_FOUND;

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

    @Override
    public void updateName(Integer franchiseId, String name) {
        FranchiseEntity franchiseEntity = franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchisesException(FRANCHISE_NOT_FOUND));
        franchiseEntity.setName(name);
        franchiseRepository.save(franchiseEntity);
    }
}
