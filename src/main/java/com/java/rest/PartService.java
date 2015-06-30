package com.java.rest;

import java.util.List;

/**
 * This interface declares the methods that provides CRUD operations for {@link com.java.rest.Part} objects.
 */
public interface PartService {
    /**
     * Finds all Part entries.
     * @return      The information of all Part entries.
     */
    List<PartDTO> findAll();

    /**
     * Finds a single Part entry.
     * @param id    The id of the requested Part entry.
     * @return      The information of the requested Part entry.
     * @throws com.java.error.NotFoundException if no Part entry is found.
     */
    PartDTO findById(String id);
}
