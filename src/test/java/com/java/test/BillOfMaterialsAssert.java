package com.java.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.AbstractAssert;

import com.java.rest.BillOfMaterials;
import com.java.rest.PartsList;

class BillOfMaterialsAssert extends AbstractAssert<BillOfMaterialsAssert, BillOfMaterials> {

    private BillOfMaterialsAssert(BillOfMaterials actual) {
        super(actual, BillOfMaterialsAssert.class);
    }

    static BillOfMaterialsAssert assertThatBillOfMaterials(BillOfMaterials actual) {
        return new BillOfMaterialsAssert(actual);
    }

    BillOfMaterialsAssert hasId(String expectedId) {
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

    BillOfMaterialsAssert hasNoId() {
        isNotNull();

        String actualId = actual.getId();
        assertThat(actualId)
                .overridingErrorMessage("Expected id to be <null> but was <%s>", actualId)
                .isNull();

        return this;
    }
    
    BillOfMaterialsAssert hasDescription(String expectedDescription) {
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

    BillOfMaterialsAssert hasNoDescription() {
        isNotNull();

        String actualDescription = actual.getDescription();
        assertThat(actualDescription)
                .overridingErrorMessage("Expected description to be <null> but was <%s>", actualDescription)
                .isNull();

        return this;
    }
    
    BillOfMaterialsAssert hasParts(List<PartsList> expectedParts) {
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

    BillOfMaterialsAssert hasNoParts() {
        isNotNull();

        List<PartsList> actualParts = actual.getParts();
        assertThat(actualParts)
                .overridingErrorMessage("Expected description to be <null> but was <%s>", actualParts)
                .isNull();

        return this;
    }
}
