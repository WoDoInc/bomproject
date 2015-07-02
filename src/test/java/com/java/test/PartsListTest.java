package com.java.test;

import static com.java.test.PartsListAssert.assertThatPartsList;

import org.junit.Test;

import com.java.rest.Part;
import com.java.rest.PartsList;

public class PartsListTest {

    private static final Long COUNT = 42L;
    private static final Part PART = new Part();

    @Test(expected = NullPointerException.class)
    public void build_CountIsNull_ShouldThrowException() {
        PartsList.getBuilder()
                .count(null)
                .part(PART)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void build_CountIsZeroOrLessThan_ShouldThrowException() {
        PartsList.getBuilder()
                .count(0L)
                .part(PART)
                .build();
    }

    @Test
    public void build_WithCountAndPart_ShouldCreateNewPartsListEntry() {
        PartsList build = PartsList.getBuilder()
                .count(COUNT)
                .part(PART)
                .build();

        assertThatPartsList(build)
                .hasCount(COUNT)
                .hasPart(PART);
    }

    @Test(expected = NullPointerException.class)
    public void update_CountIsNull_ShouldThrowException() {
        PartsList updated = PartsList.getBuilder()
                .count(COUNT)
                .part(PART)
                .build();

        updated.update(null, PART);
    }

    @Test(expected = IllegalArgumentException.class)
    public void update_CountIsZero_ShouldThrowException() {
        PartsList updated = PartsList.getBuilder()
                .count(COUNT)
                .part(PART)
                .build();

        updated.update(0L, PART);
    }

    @Test
    public void update_MaxLengthDescription_ShouldUpdateDescription() {
        PartsList updated = PartsList.getBuilder()
                .count(COUNT)
                .part(PART)
                .build();

        updated.update(COUNT, PART);

        assertThatPartsList(updated)
                .hasCount(COUNT)
                .hasPart(PART);
    }

}
