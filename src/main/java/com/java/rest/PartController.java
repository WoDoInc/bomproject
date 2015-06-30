package com.java.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.error.NotFoundException;

/**
 * This controller provides the public API that is used to manage the information of Part entries.
 */
@RestController
@RequestMapping("/api/part")
final public class PartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartController.class);
    private final PartService service;

    @Autowired
	public PartController(PartService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<PartDTO> findAll() {
        LOGGER.info("Finding all part entries");

        List<PartDTO> partsEntries = service.findAll();
        LOGGER.info("Found {} part entries", partsEntries.size());

        return partsEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    PartDTO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding part entry with id: {}", id);

        PartDTO partsEntry = service.findById(id);
        LOGGER.info("Found part entry with information: {}", partsEntry);

        return partsEntry;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handlePartNotFound(NotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
