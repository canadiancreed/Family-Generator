package com.creed.projects.javaspring.familyTreeGenerator.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createSpouse() {
    }

    @Test
    public void generateBirthYear() {
    }

    @Test
    public void generateDeathYear() {
    }

    @Test
    public void rollDice() {
    }

    @Test
    public void generateRandomID() {
    }

    @Test
    public void getOppositeGender() {
    }

    @Test
    public void calculateYearsTillReMarriage() {
        final int result = PersonUtil.calculateYearsTillReMarriage(0);

        assertTrue(1 <= result && result <= 4);
    }
}