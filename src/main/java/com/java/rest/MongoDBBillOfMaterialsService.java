package com.java.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.error.NotFoundException;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * This service class saves {@link com.java.rest.BillOfMaterials} objects
 * to MongoDB database.
 */
@Service
public final class MongoDBBillOfMaterialsService implements BillOfMaterialsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBBillOfMaterialsService.class);

    private final BillOfMaterialsRepository repository;

    @Autowired
	public MongoDBBillOfMaterialsService(BillOfMaterialsRepository repository) {
        this.repository = repository;
    }

    @Override
    public BillOfMaterialsDTO create(BillOfMaterialsDTO billOfMaterials) {
        LOGGER.info("Creating a new bill of materials entry with information: {}", billOfMaterials);

        BillOfMaterials persisted = BillOfMaterials.getBuilder()
                .description(billOfMaterials.getDescription())
                .build();

        persisted = repository.save(persisted);
        LOGGER.info("Created a new bill of materials entry with information: {}", persisted);

        return convertToDTO(persisted);
    }

    @Override
    public BillOfMaterialsDTO delete(String id) {
        LOGGER.info("Deleting a bill of materials entry with id: {}", id);

        BillOfMaterials deleted = findbillOfMaterialsById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted bill of materials entry with informtation: {}", deleted);

        return convertToDTO(deleted);
    }

    @Override
    public List<BillOfMaterialsDTO> findAll() {
        LOGGER.info("Finding all bill of materials entries.");

        List<BillOfMaterials> billOfMaterialsEntries = repository.findAll();

        LOGGER.info("Found {} billOfMaterials entries", billOfMaterialsEntries.size());

        return convertToDTOs(billOfMaterialsEntries);
    }

    private List<BillOfMaterialsDTO> convertToDTOs(List<BillOfMaterials> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

    @Override
    public BillOfMaterialsDTO findById(String id) {
        LOGGER.info("Finding bill of materials entry with id: {}", id);

        BillOfMaterials found = findbillOfMaterialsById(id);

        LOGGER.info("Found bill of materials entry: {}", found);

        return convertToDTO(found);
    }

    @Override
    public BillOfMaterialsDTO update(BillOfMaterialsDTO billOfMaterials) {
        LOGGER.info("Updating bill of materials entry with information: {}", billOfMaterials);

        BillOfMaterials updated = findbillOfMaterialsById(billOfMaterials.getId());
        updated.update(billOfMaterials.getDescription(), billOfMaterials.getParts());
        updated = repository.save(updated);

        LOGGER.info("Updated bill of materials entry with information: {}", updated);

        return convertToDTO(updated);
    }

    private BillOfMaterials findbillOfMaterialsById(String id) {
        Optional<BillOfMaterials> result = repository.findOne(id);
        return result.orElseThrow(() -> new NotFoundException(id));

    }

    private BillOfMaterialsDTO convertToDTO(BillOfMaterials model) {
        BillOfMaterialsDTO dto = new BillOfMaterialsDTO();

        dto.setId(model.getId());
        dto.setDescription(model.getDescription());

        return dto;
    }
}
