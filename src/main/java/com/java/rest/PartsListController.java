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
 * This controller provides the public API that is used to manage the information of PartsList entries.
 */
@RestController
@RequestMapping("/api/partslist")
final public class PartsListController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartsListController.class);
    private final PartsListService service;

    @Autowired
	public PartsListController(PartsListService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    PartsListDTO create(@RequestBody @Valid PartsListDTO partsListEntry) {
        LOGGER.info("Creating a new parts list entry with information: {}", partsListEntry);

        PartsListDTO created = service.create(partsListEntry);
        LOGGER.info("Created a new parts list entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    PartsListDTO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting parts list entry with id: {}", id);

        PartsListDTO deleted = service.delete(id);
        LOGGER.info("Deleted parts list entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<PartsListDTO> findAll() {
        LOGGER.info("Finding all parts list entries");

        List<PartsListDTO> partsListEntries = service.findAll();
        LOGGER.info("Found {} parts list entries", partsListEntries.size());

        return partsListEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    PartsListDTO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding parts list entry with id: {}", id);

        PartsListDTO partsListEntry = service.findById(id);
        LOGGER.info("Found parts list entry with information: {}", partsListEntry);

        return partsListEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    PartsListDTO update(@RequestBody @Valid PartsListDTO partsListEntry) {
        LOGGER.info("Updating parts list entry with information: {}", partsListEntry);

        PartsListDTO updated = service.update(partsListEntry);
        LOGGER.info("Updated parts list entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlePartsListNotFound(NotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
