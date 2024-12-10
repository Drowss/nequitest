package com.drow.nequitest.infrastructure.adapter;

import com.drow.nequitest.application.exceptionhandler.exceptions.FranchisesException;
import com.drow.nequitest.domain.model.FranchiseModel;
import com.drow.nequitest.infrastructure.out.entities.FranchiseEntity;
import com.drow.nequitest.infrastructure.out.repository.IFranchiseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static com.drow.nequitest.core.Constants.FRANCHISE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FranchiseJpaAdapterTest {

    @Mock
    private IFranchiseRepository franchiseRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FranchiseJpaAdapter franchiseJpaAdapter;

    @Test
    void createFranchise_shouldSaveFranchiseEntity() {
        FranchiseModel franchiseModel = new FranchiseModel("Franchise A");
        FranchiseEntity franchiseEntity = new FranchiseEntity();
        when(modelMapper.map(franchiseModel, FranchiseEntity.class)).thenReturn(franchiseEntity);

        franchiseJpaAdapter.createFranchise(franchiseModel);

        verify(franchiseRepository, times(1)).save(franchiseEntity);
    }

    @Test
    void updateName_shouldUpdateFranchiseName() {
        Integer franchiseId = 1;
        String newName = "Updated Franchise Name";

        FranchiseEntity franchiseEntity = new FranchiseEntity();
        when(franchiseRepository.findById(franchiseId)).thenReturn(Optional.of(franchiseEntity));

        franchiseJpaAdapter.updateName(franchiseId, newName);

        assertEquals(newName, franchiseEntity.getName());
        verify(franchiseRepository, times(1)).save(franchiseEntity);
    }

    @Test
    void updateName_shouldThrowFranchiseNotFoundException() {
        Integer franchiseId = 1;
        String newName = "Updated Franchise Name";

        when(franchiseRepository.findById(franchiseId)).thenReturn(Optional.empty());

        FranchisesException exception = assertThrows(FranchisesException.class,
                () -> franchiseJpaAdapter.updateName(franchiseId, newName));

        assertEquals(FRANCHISE_NOT_FOUND, exception.getMessage());
    }

}