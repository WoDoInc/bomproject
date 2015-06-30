package com.java.rest;

import static com.java.util.PreCondition.isTrue;
import static com.java.util.PreCondition.notEmpty;
import static com.java.util.PreCondition.notNull;

import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * This is a Bill of Materials entity, it describes the bill of materials object.
 */
final public class BillOfMaterials {
    static final int MAX_LENGTH_DESCRIPTION = 500;

    @Id
    private String id;

    private String description;
    private List<PartsList> parts;

    public BillOfMaterials() {}
    
    /**
     * Constructs a BillOfMaterials using a builder object.
     * @param builder The builder object.
     */
    private BillOfMaterials(Builder builder) {
        this.description = builder.description;
        this.parts = builder.parts;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<PartsList> getParts() {
		return parts;
	}

	public void setParts(List<PartsList> parts) {
		this.parts = parts;
	}

	/**
	 * Update method for the description and parts fields.
	 * 
	 * @param description The BOM description.
	 * @param parts The List of parts.
	 */
	public void update(String description, List<PartsList> parts) {
        checkDescription(description);
        checkPartsList(parts);

        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "BillOfMaterials[id=%s, description=%s, parts=%s]",
                this.id,
                this.description,
                this.parts
        );
    }

    /**
     * Builder pattern to help facilitate construction for use and tests.
     */
    public static class Builder {
        private String description;
        private List<PartsList> parts;

        private Builder() {}

        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder parts(List<PartsList> parts) {
            this.parts = parts;
            return this;
        }

        public BillOfMaterials build() {
            BillOfMaterials build = new BillOfMaterials(this);
            build.checkDescription(build.getDescription());

            return build;
        }
    }

    /**
     * Validation rule for descriptions. Should never be null. Should never be empty.
     * 
     * @param description A non-null, non-empty description.
     */
    private void checkDescription(String description) {
        notNull(description, "The description cannot be null");
        notEmpty(description, "The description cannot be empty");
        if (description != null) {
            isTrue(description.length() <= MAX_LENGTH_DESCRIPTION,
                    "Description cannot be longer than %d characters",
                    MAX_LENGTH_DESCRIPTION
            );
        }
    }
    
    /**
     * Validation rule for parts lists. Should never be null. Can be empty.
     * 
     * @param parts A List of parts.
     */
    private void checkPartsList(List<PartsList> parts) {
        notNull(parts, "The parts list cannot be null");
    }
}
