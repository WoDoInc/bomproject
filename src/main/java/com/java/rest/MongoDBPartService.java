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
        dto.setApplications(model.getApplications());
        dto.setCapacitance(model.getCapacitance());
        dto.setCapacitorType(model.getCapacitorType());
        dto.setComposition(model.getComposition());
        dto.setCorporatePartNumber(model.getCorporatePartNumber());
        dto.setCurrent(model.getCurrent());
        dto.setDatasheets(model.getDatasheets());
        dto.setDescription(model.getDescription());
        dto.setFeatures(model.getFeatures());
        dto.setHeightSeated(model.getHeightSeated());
        dto.setImage(model.getImage());
        dto.setImpedance(model.getImpedance());
        dto.setLifetimeTemp(model.getLifetimeTemp());
        dto.setManufacturer(model.getManufacturer());
        dto.setManufacturerPartNumber(model.getManufacturerPartNumber());
        dto.setMountingType(model.getMountingType());
        dto.setOperatingTemperature(model.getOperatingTemperature());
        dto.setPackageCase(model.getPackageCase());
        dto.setPackaging(model.getPackaging());
        dto.setPower(model.getPower());
        dto.setPowerMax(model.getPowerMax());
        dto.setResistance(model.getResistance());
        dto.setRippleCurrent(model.getRippleCurrent());
        dto.setSeries(model.getSeries());
        dto.setSizeDimension(model.getSizeDimension());
        dto.setSupplierDevicePackage(model.getSupplierDevicePackage());
        dto.setSurfaceMountLandSize(model.getSurfaceMountLandSize());
        dto.setTemperatureCoefficient(model.getTemperatureCoefficient());
        dto.setTolerance(model.getTolerance());
        dto.setType(model.getType());
        dto.setUnitPrice(model.getUnitPrice());
        dto.setVoltage(model.getVoltage());
        dto.setVoltageRating(model.getVoltageRating());
        dto.setVoltageZener(model.getVoltageZener());
        
        return dto;
    }
}
