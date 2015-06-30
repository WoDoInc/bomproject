package com.java.rest;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * This data transfer object contains the information of a single Bill of Materials {@link com.java.rest.BillOfMaterials}
 * entry and specifies validation rules that are used to ensure that only valid information can be saved to the database.
 */
public final class BillOfMaterialsDTO {

    private String id;

    @NotEmpty
    @Size(max = BillOfMaterials.MAX_LENGTH_DESCRIPTION)
    private String description;
    private List<PartsList> parts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PartsList> getParts() {
		return parts;
	}

	public void setParts(List<PartsList> parts) {
		this.parts = parts;
	}

	@Override
    public String toString() {
        return String.format(
                "BillOfMaterialsDTO[id=%s, description=%s, parts=%s]",
                this.id,
                this.description,
                this.parts
        );
    }
}
