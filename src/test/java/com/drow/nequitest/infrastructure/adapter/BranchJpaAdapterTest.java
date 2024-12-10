package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.application.exceptionhandler.exceptions.FranchisesException;
import com.drow.nequitest.domain.model.BranchModel;
import com.drow.nequitest.infrastructure.out.entities.BranchEntity;
import com.drow.nequitest.infrastructure.out.entities.FranchiseEntity;
import com.drow.nequitest.infrastructure.out.repository.IBranchRepository;
import com.drow.nequitest.infrastructure.out.repository.IFranchiseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

import static com.drow.nequitest.core.Constants.BRANCH_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BranchJpaAdapterTest {

    @Mock
    private IBranchRepository branchRepository;

    @Mock
    private IFranchiseRepository franchiseRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BranchJpaAdapter branchJpaAdapter;

    @Test
    void createBranch_shouldSaveBranchEntity() {
        BranchModel branchModel = new BranchModel(); // Assume this class exists
        BranchEntity branchEntity = new BranchEntity();
        when(modelMapper.map(branchModel, BranchEntity.class)).thenReturn(branchEntity);

        branchJpaAdapter.createBranch(branchModel);

        verify(branchRepository, times(1)).save(branchEntity);
    }

    @Test
    void addBranchToFranchise_shouldAddFranchiseToBranch() {
        Integer branchId = 1;
        Integer franchiseId = 2;

        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setFranchises(new ArrayList<>());
        FranchiseEntity franchiseEntity = new FranchiseEntity();

        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branchEntity));
        when(franchiseRepository.findById(franchiseId)).thenReturn(Optional.of(franchiseEntity));

        branchJpaAdapter.addBranchToFranchise(branchId, franchiseId);

        assertTrue(branchEntity.getFranchises().contains(franchiseEntity));
        verify(branchRepository, times(1)).save(branchEntity);
    }

    @Test
    void addBranchToFranchise_shouldThrowBranchNotFoundException() {
        Integer branchId = 1;
        Integer franchiseId = 2;

        when(branchRepository.findById(branchId)).thenReturn(Optional.empty());

        FranchisesException exception = assertThrows(FranchisesException.class,
                () -> branchJpaAdapter.addBranchToFranchise(branchId, franchiseId));

        assertEquals(BRANCH_NOT_FOUND, exception.getMessage());
    }

    @Test
    void updateName_shouldUpdateBranchName() {
        Integer branchId = 1;
        String newName = "Updated Name";

        BranchEntity branchEntity = new BranchEntity();
        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branchEntity));

        branchJpaAdapter.updateName(branchId, newName);

        assertEquals(newName, branchEntity.getName());
        verify(branchRepository, times(1)).save(branchEntity);
    }

    @Test
    void updateName_shouldThrowBranchNotFoundException() {
        Integer branchId = 1;
        String newName = "Updated Name";

        when(branchRepository.findById(branchId)).thenReturn(Optional.empty());

        FranchisesException exception = assertThrows(FranchisesException.class,
                () -> branchJpaAdapter.updateName(branchId, newName));

        assertEquals(BRANCH_NOT_FOUND, exception.getMessage());
    }
}