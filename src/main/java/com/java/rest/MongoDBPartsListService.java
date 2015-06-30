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
 * This service class saves {@link com.java.rest.partsList} objects to MongoDB database.
 */
@Service
public final class MongoDBPartsListService implements PartsListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBPartsListService.class);
    private final PartsListRepository repository;

    @Autowired
	public MongoDBPartsListService(PartsListRepository repository) {
        this.repository = repository;
    }

    @Override
    public PartsListDTO create(PartsListDTO parts) {
        LOGGER.info("Creating a new part list entry with information: {}", parts);
        PartsList persisted = PartsList.getBuilder()
                .count(parts.getCount())
                .part(parts.getPart())
                .build();

        persisted = repository.save(persisted);
        LOGGER.info("Created a new part list entry with information: {}", persisted);

        return convertToDTO(persisted);
    }

    @Override
    public PartsListDTO delete(String id) {
        LOGGER.info("Deleting a part list entry with id: {}", id);
        PartsList deleted = findPartsById(id);
        repository.delete(deleted);
        LOGGER.info("Deleted part list entry with informtation: {}", deleted);

        return convertToDTO(deleted);
    }

    @Override
    public List<PartsListDTO> findAll() {
        LOGGER.info("Finding all part list entries.");
        List<PartsList> partsEntries = repository.findAll();
        LOGGER.info("Found {} parts entries", partsEntries.size());

        return convertToDTOs(partsEntries);
    }

    private List<PartsListDTO> convertToDTOs(List<PartsList> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

    @Override
    public PartsListDTO findById(String id) {
        LOGGER.info("Finding part list entry with id: {}", id);
        PartsList found = findPartsById(id);
        LOGGER.info("Found part list entry: {}", found);

        return convertToDTO(found);
    }

    @Override
    public PartsListDTO update(PartsListDTO parts) {
        LOGGER.info("Updating part list entry with information: {}", parts);
        PartsList updated = findPartsById(parts.getId());
        updated.update(parts.getCount(), parts.getPart());
        updated = repository.save(updated);
        LOGGER.info("Updated part list entry with information: {}", updated);

        return convertToDTO(updated);
    }

    private PartsList findPartsById(String id) {
        Optional<PartsList> result = repository.findOne(id);
        return result.orElseThrow(() -> new NotFoundException(id));
    }

    private PartsListDTO convertToDTO(PartsList model) {
        PartsListDTO dto = new PartsListDTO();
        dto.setId(model.getId());
        dto.setCount(model.getCount());
        dto.setPart(model.getPart());

        return dto;
    }
}