package com.java.test;

import org.springframework.test.util.ReflectionTestUtils;

import com.java.rest.PartsList;
import com.java.rest.Part;

public class PartsListBuilder {
    private String id;
    private Long count;
    private Part part = new Part();

    PartsListBuilder id(String id) {
        this.id = id;
        return this;
    }
    
    PartsListBuilder count(Long count) {
        this.count = count;
        return this;
    }
    
    PartsListBuilder part(Part part) {
        this.part = part;
        return this;
    }

    PartsList build() {
        PartsList parts = PartsList.getBuilder()
                .count(count)
                .part(part)
                .build();

        ReflectionTestUtils.setField(parts, "id", id);

        return parts;
    }
}
