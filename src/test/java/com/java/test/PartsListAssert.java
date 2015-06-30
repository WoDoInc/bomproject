package com.java.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

import com.java.rest.Part;
import com.java.rest.PartsList;

class PartsListAssert extends AbstractAssert<PartsListAssert, PartsList> {

    private PartsListAssert(PartsList actual) {
        super(actual, PartsListAssert.class);
    }

    static PartsListAssert assertThatPartsList(PartsList actual) {
        return new PartsListAssert(actual);
    }

    PartsListAssert hasId(String expectedId) {
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

    PartsListAssert hasNoId() {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <null> but was <%s>", actualId)
                .isNull();

        return this;
    }
    
    PartsListAssert hasCount(Long expectedCount) {
        isNotNull();

        Long actualCount = actual.getCount();
        assertThat(actualCount)
                .overridingErrorMessage("Expected description to be <%s> but was <%s>",
                        expectedCount,
                        actualCount
                )
                .isEqualTo(expectedCount);

        return this;
    }

    PartsListAssert hasNoCount() {
        isNotNull();

        Long actualCount = actual.getCount();
        assertThat(actualCount)
                .overridingErrorMessage("Expected description to be <null> but was <%s>", actualCount)
                .isNull();

        return this;
    }
    
    PartsListAssert hasPart(Part expectedPart) {
        isNotNull();

        Part actualPart = actual.getPart();
        assertThat(actualPart)
                .overridingErrorMessage("Expected parts to be <%s> but was <%s>",
                		expectedPart,
                		actualPart
                )
                .isEqualTo(expectedPart);

        return this;
    }

    PartsListAssert hasNoPart() {
        isNotNull();

        Part actualPart = actual.getPart();
        assertThat(actualPart)
                .overridingErrorMessage("Expected description to be <null> but was <%s>", actualPart)
                .isNull();

        return this;
    }
}
