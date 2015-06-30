package com.java.rest;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This repository provides CRUD operations for {@link com.java.rest.Part} objects.
 */
public interface PartRepository extends Repository<Part, String> {
    /**
     * Finds all part entries from the database.
     * @return  The information of all part entries that are found from the database.
     */
    List<Part> findAll();

    /**
     * Finds the information of a single part entry.
     * @param id    The id of the requested part entry.
     * @return      The information of the found part entry. If no part entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<Part> findOne(String id);
}
