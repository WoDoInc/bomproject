package com.java.rest;

import static com.java.util.PreCondition.isTrue;
import static com.java.util.PreCondition.notNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class PartsList {
	@Id
	private String id;
	private Long count;
	
	@DBRef
	private Part part;
	
	public PartsList() {}
	
	private PartsList(Builder builder) {
        this.count = builder.count;
        this.part = builder.part;
    }

	public static Builder getBuilder() {
        return new Builder();
    }
	
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

	public void update(Long count, Part part) {
        checkCount(count);
        checkPart(part);

        this.count = count;
        this.part = part;
    }

	@Override
    public String toString() {
        return String.format(
                "PartsList[id=%s, count=%s, part=%s]",
                this.id,
                this.count,
                this.part
        );
    }
	
	/**
     * Builder pattern to help facilitate construction.
     */
    public static class Builder {
        private Long count;
        private Part part;

        private Builder() {}

        public Builder count(Long count) {
            this.count = count;
            return this;
        }
        
        public Builder part(Part part) {
            this.part = part;
            return this;
        }

        public PartsList build() {
            PartsList build = new PartsList(this);
            build.checkCount(build.getCount());
            build.checkPart(build.getPart());

            return build;
        }
    }

    private void checkCount(Long count) {
        notNull(count, "The count cannot be null");
        if (count != null) {
            isTrue(count.longValue() > 0L,
                    "Count cannot be equal to or less than zero."
            );
        }
    }
    
    private void checkPart(Part part) {
        notNull(part, "The part cannot be null");
    }
}
