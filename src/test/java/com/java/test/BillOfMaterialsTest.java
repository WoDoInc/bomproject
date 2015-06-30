package com.java.test;

import static com.java.test.BillOfMaterialsAssert.assertThatBillOfMaterials;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.java.rest.BillOfMaterials;
import com.java.rest.PartsList;

public class BillOfMaterialsTest {

    private static final String DESCRIPTION = "description";
    private static final int MAX_LENGTH_DESCRIPTION = 500;
    private static final List<PartsList> PARTS = new ArrayList<>();

    @Test(expected = NullPointerException.class)
    public void build_DescriptionIsNull_ShouldThrowException() {
        BillOfMaterials.getBuilder()
                .description(null)
                .parts(PARTS)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_DescriptionIsEmpty_ShouldThrowException() {
        BillOfMaterials.getBuilder()
                .description("")
                .parts(PARTS)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_DescriptionIsTooLong_ShouldThrowException() {
        String tooLongDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION + 1);
        BillOfMaterials.getBuilder()
                .description(tooLongDescription)
                .parts(PARTS)
                .build();
    }

    @Test
    public void build_WithDescription_ShouldCreateNewBillOfMaterialsEntryWithCorrectDescription() {
        BillOfMaterials build = BillOfMaterials.getBuilder()
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        assertThatBillOfMaterials(build)
                .hasNoId()
                .hasDescription(DESCRIPTION);
    }

    @Test
    public void build_WithMaxLengthDescription_ShouldCreateNewBillOfMaterialsEntryWithCorrectDescription() {
        String maxLengthDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION);

        BillOfMaterials build = BillOfMaterials.getBuilder()
                .description(maxLengthDescription)
                .parts(PARTS)
                .build();

        assertThatBillOfMaterials(build)
                .hasNoId()
                .hasDescription(maxLengthDescription);
    }

    @Test(expected = NullPointerException.class)
    public void update_DescriptionIsNull_ShouldThrowException() {
        BillOfMaterials updated = BillOfMaterials.getBuilder()
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        updated.update(null, new ArrayList<PartsList>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_DescriptionIsEmpty_ShouldThrowException() {
        BillOfMaterials updated = BillOfMaterials.getBuilder()
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        updated.update("", new ArrayList<PartsList>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_DescriptionIsTooLong_ShouldThrowException() {
        BillOfMaterials updated = BillOfMaterials.getBuilder()
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        String tooLongDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION + 1);
        updated.update(tooLongDescription, new ArrayList<PartsList>());
    }

    
    @Test
    public void update_MaxLengthDescription_ShouldUpdateDescription() {
        BillOfMaterials updated = BillOfMaterials.getBuilder()
                .description(DESCRIPTION)
                .parts(PARTS)
                .build();

        String maxLengthDescription = StringTestUtil.createStringWithLength(MAX_LENGTH_DESCRIPTION);

        updated.update(maxLengthDescription, new ArrayList<PartsList>());

        assertThatBillOfMaterials(updated)
                .hasDescription(maxLengthDescription);
    }
}
