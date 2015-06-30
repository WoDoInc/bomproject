package com.java.rest;

import java.util.List;

/**
 * This interface declares the methods that provides CRUD operations for {@link com.java.rest.BillOfMaterials} objects.
 */
public interface BillOfMaterialsService {

    /**
     * Creates a new BillOfMaterials entry.
     * @param BillOfMaterials  The information of the created BillOfMaterials entry.
     * @return      The information of the created BillOfMaterials entry.
     */
    BillOfMaterialsDTO create(BillOfMaterialsDTO billOfMaterials);

    /**
     * Deletes a BillOfMaterials entry.
     * @param id    The id of the deleted BillOfMaterials entry.
     * @return      THe information of the deleted BillOfMaterials entry.
     * @throws com.java.error.NotFoundException if no BillOfMaterials entry is found.
     */
    BillOfMaterialsDTO delete(String id);

    /**
     * Finds all BillOfMaterials entries.
     * @return      The information of all BillOfMaterials entries.
     */
    List<BillOfMaterialsDTO> findAll();

    /**
     * Finds a single BillOfMaterials entry.
     * @param id    The id of the requested BillOfMaterials entry.
     * @return      The information of the requested BillOfMaterials entry.
     * @throws com.java.error.NotFoundException if no BillOfMaterials entry is found.
     */
    BillOfMaterialsDTO findById(String id);

    /**
     * Updates the information of a BillOfMaterials entry.
     * @param BillOfMaterials  The information of the updated BillOfMaterials entry.
     * @return      The information of the updated BillOfMaterials entry.
     * @throws com.java.error.NotFoundException if no BillOfMaterials entry is found.
     */
    BillOfMaterialsDTO update(BillOfMaterialsDTO billOfMaterials);
}
