package com.java.rest;

import java.util.List;

/**
 * This interface declares the methods that provides CRUD operations for {@link com.java.rest.PartsList} objects.
 */
public interface PartsListService {
    /**
     * Creates a new PartsList entry.
     * @param PartsList  The information of the created PartsList entry.
     * @return      The information of the created PartsList entry.
     */
    PartsListDTO create(PartsListDTO parts);

    /**
     * Deletes a PartsList entry.
     * @param id    The id of the deleted PartsList entry.
     * @return      THe information of the deleted PartsList entry.
     * @throws com.java.error.NotFoundException if no PartsList entry is found.
     */
    PartsListDTO delete(String id);

    /**
     * Finds all PartsList entries.
     * @return      The information of all PartsList entries.
     */
    List<PartsListDTO> findAll();

    /**
     * Finds a single PartsList entry.
     * @param id    The id of the requested PartsList entry.
     * @return      The information of the requested PartsList entry.
     * @throws com.java.error.NotFoundException if no PartsList entry is found.
     */
    PartsListDTO findById(String id);

    /**
     * Updates the information of a PartsList entry.
     * @param PartsList  The information of the updated PartsList entry.
     * @return      The information of the updated PartsList entry.
     * @throws com.java.error.NotFoundException if no PartsList entry is found.
     */
    PartsListDTO update(PartsListDTO parts);
}
