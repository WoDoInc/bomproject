package com.java.test;

import static com.java.test.BillOfMaterialsAssert.assertThatBillOfMaterials;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.java.error.NotFoundException;
import com.java.rest.BillOfMaterials;
import com.java.rest.BillOfMaterialsDTO;
import com.java.rest.BillOfMaterialsRepository;
import com.java.rest.MongoDBBillOfMaterialsService;
import com.java.rest.Part;
import com.java.rest.PartsList;

@RunWith(MockitoJUnitRunner.class)
public class MongoDBBillOfMaterialsServiceTest {

    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final String UPDATED_DESCRIPTION = "updated description";
    private static final List<PartsList> PARTS = new ArrayList<>();
    private static final PartsList LIST = new PartsList();
    private static final Part PART = new Part();
    static {
    	PART.setId("ZZZ");
    	PART.setType("Capacitor");
    	PART.setCapacitance("10µF");
    	LIST.setCount(1L);
    	LIST.setPart(PART);
    	PARTS.add(LIST);
    }

    @Mock
    private BillOfMaterialsRepository repository;

    private MongoDBBillOfMaterialsService service;

    @Before
    public void setUp() {
        this.service = new MongoDBBillOfMaterialsService(repository);
    }

    @Test
    public void create_ShouldSaveNewBillOfMaterialsEntry() {
        BillOfMaterialsDTO newBillOfMaterials = new BillOfMaterialsDTOBuilder()
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(repository.save(isA(BillOfMaterials.class))).thenAnswer(invocation -> (BillOfMaterials) invocation.getArguments()[0]);

        service.create(newBillOfMaterials);

        ArgumentCaptor<BillOfMaterials> savedBillOfMaterialsArgument = ArgumentCaptor.forClass(BillOfMaterials.class);

        verify(repository, times(1)).save(savedBillOfMaterialsArgument.capture());
        verifyNoMoreInteractions(repository);

        BillOfMaterials savedBillOfMaterials = savedBillOfMaterialsArgument.getValue();
        assertThatBillOfMaterials(savedBillOfMaterials)
                .hasDescription(DESCRIPTION);
    }

    @Test
    public void create_ShouldReturnTheInformationOfCreatedBillOfMaterialsEntry() {
        BillOfMaterialsDTO newBillOfMaterials = new BillOfMaterialsDTOBuilder()
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(repository.save(isA(BillOfMaterials.class))).thenAnswer(invocation -> {
            BillOfMaterials persisted = (BillOfMaterials) invocation.getArguments()[0];
            ReflectionTestUtils.setField(persisted, "id", ID);
            return persisted;
        });

        BillOfMaterialsDTO returned = service.create(newBillOfMaterials);

        BillOfMaterialsDTOAssert.assertThatBillOfMaterialsDTO(returned)
                .hasId(ID)
                .hasDescription(DESCRIPTION);
    }

    @Test(expected = NotFoundException.class)
    public void delete_BillOfMaterialsEntryNotFound_ShouldThrowException() {
        when(repository.findOne(ID)).thenReturn(Optional.empty());

        service.findById(ID);
    }

    @Test
    public void delete_BillOfMaterialsEntryFound_ShouldDeleteTheFoundBillOfMaterialsEntry() {
        BillOfMaterials deleted = new BillOfMaterialsBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(deleted));

        service.delete(ID);

        verify(repository, times(1)).delete(deleted);
    }

    @Test
    public void delete_BillOfMaterialsEntryFound_ShouldReturnTheDeletedBillOfMaterialsEntry() {
        BillOfMaterials deleted = new BillOfMaterialsBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(deleted));

        BillOfMaterialsDTO returned = service.delete(ID);

        BillOfMaterialsDTOAssert.assertThatBillOfMaterialsDTO(returned)
                .hasId(ID)
                .hasDescription(DESCRIPTION);
    }

    @Test
    public void findAll_OneBillOfMaterialsEntryFound_ShouldReturnTheInformationOfFoundBillOfMaterialsEntry() {
        BillOfMaterials expected = new BillOfMaterialsBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(repository.findAll()).thenReturn(Arrays.asList(expected));

        List<BillOfMaterialsDTO> todoEntries = service.findAll();
        assertThat(todoEntries).hasSize(1);

        BillOfMaterialsDTO actual = todoEntries.iterator().next();
        BillOfMaterialsDTOAssert.assertThatBillOfMaterialsDTO(actual)
                .hasId(ID)
                .hasDescription(DESCRIPTION);
    }

    @Test(expected = NotFoundException.class)
    public void findById_BillOfMaterialsEntryNotFound_ShouldThrowException() {
        when(repository.findOne(ID)).thenReturn(Optional.empty());

        service.findById(ID);
    }

    @Test
    public void findById_BillOfMaterialsEntryFound_ShouldReturnTheInformationOfFoundBillOfMaterialsEntry() {
        BillOfMaterials found = new BillOfMaterialsBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(found));

        BillOfMaterialsDTO returned = service.findById(ID);

        BillOfMaterialsDTOAssert.assertThatBillOfMaterialsDTO(returned)
                .hasId(ID)
                .hasDescription(DESCRIPTION);
    }

    @Test(expected = NotFoundException.class)
    public void update_UpdatedBillOfMaterialsEntryNotFound_ShouldThrowException() {
        when(repository.findOne(ID)).thenReturn(Optional.empty());

        BillOfMaterialsDTO updated = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .build();

        service.update(updated);
    }

    @Test
    public void update_UpdatedBillOfMaterialsEntryFound_ShouldSaveUpdatedBillOfMaterialsEntry() {
        BillOfMaterials existing = new BillOfMaterialsBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        BillOfMaterialsDTO updated = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .description(UPDATED_DESCRIPTION)
                .parts(PARTS)
                .build();

        service.update(updated);

        verify(repository, times(1)).save(existing);
        assertThatBillOfMaterials(existing)
                .hasId(ID)
                .hasDescription(UPDATED_DESCRIPTION);
    }

    @Test
    public void update_UpdatedBillOfMaterialsEntryFound_ShouldReturnTheInformationOfUpdatedBillOfMaterialsEntry() {
        BillOfMaterials existing = new BillOfMaterialsBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .build();

        when(repository.findOne(ID)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        BillOfMaterialsDTO updated = new BillOfMaterialsDTOBuilder()
                .id(ID)
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        BillOfMaterialsDTO returned = service.update(updated);
        BillOfMaterialsDTOAssert.assertThatBillOfMaterialsDTO(returned)
                .hasId(ID)
                .hasDescription(DESCRIPTION);
    }
}
