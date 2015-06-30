package com.java.test;

import org.assertj.core.api.AbstractAssert;

import com.java.rest.BillOfMaterialsDTO;
import com.java.rest.PartsList;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

class BillOfMaterialsDTOAssert extends AbstractAssert<BillOfMaterialsDTOAssert, BillOfMaterialsDTO> {

    private BillOfMaterialsDTOAssert(BillOfMaterialsDTO actual) {
        super(actual, BillOfMaterialsDTOAssert.class);
    }

    static BillOfMaterialsDTOAssert assertThatBillOfMaterialsDTO(BillOfMaterialsDTO actual) {
        return new BillOfMaterialsDTOAssert(actual);
    }

    public BillOfMaterialsDTOAssert hasId(String expectedId) {
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

    public BillOfMaterialsDTOAssert hasNoId() {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <null> but was <%s>", actualId)
                .isNull();

        return this;
    }
    
    public BillOfMaterialsDTOAssert hasDescription(String expectedDescription) {
        isNotNull();

        String actualDescription = actual.getDescription();
        assertThat(actualDescription)
                .overridingErrorMessage("Expected description to be <%s> but was <%s>",
                        expectedDescription,
                        actualDescription
                )
                .isEqualTo(expectedDescription);

        return this;
    }

    public BillOfMaterialsDTOAssert hasNoDescription() {
        isNotNull();

        String actualDescription = actual.getDescription();
        assertThat(actualDescription)
                .overridingErrorMessage("Expected description to be <null> but was <%s>", actualDescription)
                .isNull();

        return this;
    }

    public BillOfMaterialsDTOAssert hasParts(List<PartsList> expectedParts) {
        isNotNull();

        List<PartsList> actualParts = actual.getParts();
        assertThat(actualParts)
                .overridingErrorMessage("Expected parts to be <%s> but was <%s>",
                		expectedParts,
                		actualParts
                )
                .isEqualTo(expectedParts);

        return this;
    }

    public BillOfMaterialsDTOAssert hasNoParts() {
        isNotNull();

        List<PartsList> actualParts = actual.getParts();
        assertThat(actualParts)
                .overridingErrorMessage("Expected description to be <null> but was <%s>", actualParts)
                .isNull();

        return this;
    }
}
