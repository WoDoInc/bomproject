package com.java.test;

import com.java.rest.Part;
import com.java.rest.PartsListDTO;

class PartsListDTOBuilder {

	    private String id;
	    private Long count;
	    private Part part = new Part();

	    PartsListDTOBuilder() {}

	    PartsListDTOBuilder id(String id) {
	        this.id = id;
	        return this;
	    }
	    
	    PartsListDTOBuilder count(Long count) {
	        this.count = count;
	        return this;
	    }
	    
	    PartsListDTOBuilder part(Part part) {
	        this.part = part;
	        return this;
	    }

	    PartsListDTO build() {
	    	PartsListDTO dto = new PartsListDTO();
	        dto.setId(id);
	        dto.setCount(count);
	        dto.setPart(part);

	        return dto;
	    }
	}
