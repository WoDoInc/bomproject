package com.java.rest;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * This data transfer object contains the information of a single Parts List {@link com.java.rest.PartsList}
 * entry and specifies validation rules that are used to ensure that only valid information can be saved to the database.
 */
public final class PartsListDTO {

    private String id;
    private Long count;
    @NotEmpty
    private Part part;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	@Override
    public String toString() {
        return String.format(
                "PartsListDTO[id=%s, count=%s, part=%s]",
                this.id,
                this.count,
                this.part
        );
    }
}
