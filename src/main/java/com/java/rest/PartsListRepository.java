package com.java.rest;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This repository provides CRUD operations for {@link com.java.rest.PartsList} objects.
 */
public interface PartsListRepository extends Repository<PartsList, String> {

    /**
     * Deletes a parts list entry from the database.
     * @param deleted   The deleted parts list entry.
     */
    void delete(PartsList deleted);

    /**
     * Finds all parts list entries from the database.
     * @return  The information of all parts list entries that are found from the database.
     */
    List<PartsList> findAll();

    /**
     * Finds the information of a single parts list entry.
     * @param id    The id of the requested parts list entry.
     * @return      The information of the found parts list entry. If no parts list entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<PartsList> findOne(String id);

    /**
     * Saves a new parts list entry to the database.
     * @param saved The information of the saved parts list entry.
     * @return      The information of the saved parts list entry.
     */
    PartsList save(PartsList saved);
}
