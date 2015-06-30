package com.java.test;

import java.util.List;

import org.springframework.test.util.ReflectionTestUtils;

import com.java.rest.BillOfMaterials;
import com.java.rest.PartsList;

class BillOfMaterialsBuilder {

    private String description;
    private String id;
    private List<PartsList> parts;

    BillOfMaterialsBuilder id(String id) {
        this.id = id;
        return this;
    }
    
    BillOfMaterialsBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    BillOfMaterialsBuilder parts(List<PartsList> parts) {
        this.parts = parts;
        return this;
    }

    BillOfMaterials build() {
        BillOfMaterials billOfMaterials = BillOfMaterials.getBuilder()
                .description(description)
                .parts(parts)
                .build();

        ReflectionTestUtils.setField(billOfMaterials, "id", id);

        return billOfMaterials;
    }
}
