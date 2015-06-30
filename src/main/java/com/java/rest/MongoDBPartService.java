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
 * This service class saves {@link com.java.rest.Part} objects to MongoDB database.
 */
@Service
public final class MongoDBPartService implements PartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBPartService.class);
    private final PartRepository repository;

    @Autowired
	public MongoDBPartService(PartRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PartDTO> findAll() {
        LOGGER.info("Finding all part entries.");
        List<Part> partEntries = repository.findAll();
        LOGGER.info("Found {} part entries", partEntries.size());

        return convertToDTOs(partEntries);
    }

    private List<PartDTO> convertToDTOs(List<Part> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

    @Override
    public PartDTO findById(String id) {
        LOGGER.info("Finding part entry with id: {}", id);
        Part found = findPartById(id);
        LOGGER.info("Found part entry: {}", found);

        return convertToDTO(found);
    }

    private Part findPartById(String id) {
        Optional<Part> result = repository.findOne(id);
        return result.orElseThrow(() -> new NotFoundException(id));

    }

    private PartDTO convertToDTO(Part model) {
        PartDTO dto = new PartDTO();
        dto.setId(model.getId());
        dto.setDescription(model.getDescription());

        return dto;
    }
}
