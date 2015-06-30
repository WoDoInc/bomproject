package com.java.test;

import java.util.ArrayList;
import java.util.List;

import com.java.rest.BillOfMaterialsDTO;
import com.java.rest.PartsList;

class BillOfMaterialsDTOBuilder {

    private String description;
    private String id;
    private List<PartsList> parts = new ArrayList<>();

    BillOfMaterialsDTOBuilder() {}

    BillOfMaterialsDTOBuilder id(String id) {
        this.id = id;
        return this;
    }
    
    BillOfMaterialsDTOBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    BillOfMaterialsDTOBuilder parts(List<PartsList> parts) {
        this.parts = parts;
        return this;
    }

    BillOfMaterialsDTO build() {
        BillOfMaterialsDTO dto = new BillOfMaterialsDTO();
        dto.setId(id);
        dto.setDescription(description);
        dto.setParts(parts);

        return dto;
    }
}
