package com.company.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class PersonUtilTest {

    @Test
    public void testRollTwoDice() {
        int result = PersonUtil.rollID(2);
        assertThat(result, allOf(greaterThan(0), lessThan(3)));
    }

    @Test
    public void testRollFourDice() {
        int result = PersonUtil.rollID(4);
        assertThat(result, allOf(greaterThan(0), lessThan(5)));
    }

    @Test
    public void testRollSixDice() {
        int result = PersonUtil.rollID(6);
        assertThat(result, allOf(greaterThan(0), lessThan(7)));
    }

    @Test
    public void testRollEightDice() {
        int result = PersonUtil.rollID(8);
        assertThat(result, allOf(greaterThan(0), lessThan(9)));
    }

    @Test
    public void testRollTenDice() {
        int result = PersonUtil.rollID(10);
        assertThat(result, allOf(greaterThan(0), lessThan(11)));
    }

    @Test
    public void testRollTwentyDice() {
        int result = PersonUtil.rollID(20);
        assertThat(result, allOf(greaterThan(0), lessThan(21)));
    }

    @Test
    public void testRollHundredDice() {
        int result = PersonUtil.rollID(100);
        assertThat(result, allOf(greaterThan(0), lessThan(101)));
    }

}