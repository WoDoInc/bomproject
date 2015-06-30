package com.java.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.error.NotFoundException;

import javax.validation.Valid;
import java.util.List;

/**
 * This controller provides the public API that is used to manage the information of BillOfMaterial entries.
 */
@RestController
@RequestMapping("/api/billofmaterials")
final public class BillOfMaterialsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillOfMaterialsController.class);
    private final BillOfMaterialsService service;

    @Autowired
	public BillOfMaterialsController(BillOfMaterialsService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    BillOfMaterialsDTO create(@RequestBody @Valid BillOfMaterialsDTO billOfMaterialsEntry) {
        LOGGER.info("Creating a new bill of materials entry with information: {}", billOfMaterialsEntry);

        BillOfMaterialsDTO created = service.create(billOfMaterialsEntry);
        LOGGER.info("Created a new bill of materials entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    BillOfMaterialsDTO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting bill of materials entry with id: {}", id);

        BillOfMaterialsDTO deleted = service.delete(id);
        LOGGER.info("Deleted bill of materials entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<BillOfMaterialsDTO> findAll() {
        LOGGER.info("Finding all bill of materials entries");

        List<BillOfMaterialsDTO> billOfMaterialsEntries = service.findAll();
        LOGGER.info("Found {} bill of materials entries", billOfMaterialsEntries.size());

        return billOfMaterialsEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    BillOfMaterialsDTO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding bill of materials entry with id: {}", id);

        BillOfMaterialsDTO billOfMaterialsEntry = service.findById(id);
        LOGGER.info("Found bill of materials entry with information: {}", billOfMaterialsEntry);

        return billOfMaterialsEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    BillOfMaterialsDTO update(@RequestBody @Valid BillOfMaterialsDTO billOfMaterialsEntry) {
        LOGGER.info("Updating bill of materials entry with information: {}", billOfMaterialsEntry);

        BillOfMaterialsDTO updated = service.update(billOfMaterialsEntry);
        LOGGER.info("Updated bill of materials entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleBillOfMaterialsNotFound(NotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
