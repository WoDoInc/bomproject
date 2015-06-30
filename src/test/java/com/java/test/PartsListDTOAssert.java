package com.java.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

import com.java.rest.Part;
import com.java.rest.PartsListDTO;

public class PartsListDTOAssert extends AbstractAssert<PartsListDTOAssert, PartsListDTO> {

    private PartsListDTOAssert(PartsListDTO actual) {
        super(actual, PartsListDTOAssert.class);
    }

    static PartsListDTOAssert assertThatPartsListDTO(PartsListDTO actual) {
        return new PartsListDTOAssert(actual);
    }

    public PartsListDTOAssert hasId(String expectedId) {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <%s> but was <%s>",
                        expectedId,
                        actualId
                )
                .isEqualTo(expectedId);

        return this;
    }

    public PartsListDTOAssert hasNoId() {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <null> but was <%s>", actualId)
                .isNull();

        return this;
    }
    
    public PartsListDTOAssert hasCount(Long expectedCount) {
        isNotNull();

        Long actualCount = actual.getCount();
        assertThat(actualCount)
                .overridingErrorMessage("Expected count to be <%s> but was <%s>",
                        expectedCount,
                        actualCount
                )
                .isEqualTo(expectedCount);

        return this;
    }

    public PartsListDTOAssert hasNoCount() {
        isNotNull();

        Long actualCount = actual.getCount();
        assertThat(actualCount)
                .overridingErrorMessage("Expected count to be <null> but was <%s>", actualCount)
                .isNull();

        return this;
    }

    public PartsListDTOAssert hasParts(Part expectedParts) {
        isNotNull();

        Part actualParts = actual.getPart();
        assertThat(actualParts)
                .overridingErrorMessage("Expected parts to be <%s> but was <%s>",
                		expectedParts,
                		actualParts
                )
                .isEqualTo(expectedParts);

        return this;
    }

    public PartsListDTOAssert hasNoParts() {
        isNotNull();

        Part actualParts = actual.getPart();
        assertThat(actualParts)
                .overridingErrorMessage("Expected description to be <null> but was <%s>", actualParts)
                .isNull();

        return this;
    }
}