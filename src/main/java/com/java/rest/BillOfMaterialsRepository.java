package com.java.rest;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * This repository provides CRUD operations for {@link com.java.rest.BillOfMaterials} objects.
 */
public interface BillOfMaterialsRepository extends Repository<BillOfMaterials, String> {

    /**
     * Deletes a bill of materials entry from the database.
     * @param deleted   The deleted bill of materials entry.
     */
    void delete(BillOfMaterials deleted);

    /**
     * Finds all bill of materials entries from the database.
     * @return  The information of all bill of materials entries that are found from the database.
     */
    List<BillOfMaterials> findAll();

    /**
     * Finds the information of a single bill of materials entry.
     * @param id    The id of the requested bill of materials entry.
     * @return      The information of the found bill of materials entry. If no bill of materials entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<BillOfMaterials> findOne(String id);

    /**
     * Saves a new bill of materials entry to the database.
     * @param saved The information of the saved bill of materials entry.
     * @return      The information of the saved bill of materials entry.
     */
    BillOfMaterials save(BillOfMaterials saved);
}
