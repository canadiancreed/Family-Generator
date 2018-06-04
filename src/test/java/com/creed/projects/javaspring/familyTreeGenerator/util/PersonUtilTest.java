package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonUtilTest {

    private ArrayList<Person> personObjects;
    private Person initialPersonObject = new Person();

    @Before
    public void setUp() throws Exception {
        //Create Initial Person Object

        initialPersonObject.setId(PersonUtil.generateRandomID());
        initialPersonObject.setGender(Gender.MALE);
        initialPersonObject.setFName("John");
        initialPersonObject.setLName("Doe");
        initialPersonObject.setMoniker("test");
        initialPersonObject.setBYear(10);

        //Create Spouse Person Object
        Person spousePersonObject = new Person();
    }

    @Test
    public void rollD2Dice() {
        final int diceSize = 2;
        final int result = PersonUtil.rollDice(diceSize);
        assertTrue("Number is out of range: " + result, 0 <= result && result <= diceSize);
    }

    @Test
    public void rollD4Dice() {
        final int diceSize = 4;
        final int result = PersonUtil.rollDice(diceSize);
        assertTrue("Number is out of range: " + result, 0 <= result && result <= diceSize);
    }

    @Test
    public void rollD6Dice() {
        final int diceSize = 6;
        final int result = PersonUtil.rollDice(diceSize);
        assertTrue("Number is out of range: " + result, 0 <= result && result <= diceSize);
    }

    @Test
    public void rollD8Dice() {
        final int diceSize = 8;
        final int result = PersonUtil.rollDice(diceSize);
        assertTrue("Number is out of range: " + result, 0 <= result && result <= diceSize);
    }

    @Test
    public void rollD10Dice() {
        final int diceSize = 10;
        final int result = PersonUtil.rollDice(diceSize);
        assertTrue("Number is out of range: " + result, 0 <= result && result <= diceSize);
    }

    @Test
    public void rollD20Dice() {
        final int diceSize = 20;
        final int result = PersonUtil.rollDice(diceSize);
        assertTrue("Number is out of range: " + result, 0 <= result && result <= diceSize);
    }

    @Test
    public void rollD100Dice() {
        final int diceSize = 100;
        final int result = PersonUtil.rollDice(diceSize);
        assertTrue("Number is out of range: " + result, 0 <= result && result <= diceSize);
    }

    @Test
    public void generateRandomID() {
    }

    @Test
    public void createFamily() {
    }

    @Test
    public void createSpouseNoMarriedYear() {
        final Person spouseObject = PersonUtil.createSpouse(initialPersonObject, 0);

        System.out.println(spouseObject);

        assertTrue("Death Year is greater then Birth Year", spouseObject.getDYear() >= spouseObject.getBYear());
        assertTrue("Death Age is greater then Married Age", spouseObject.getDYear() >= 15);
    }

    @Test
    public void createSpouseSpecifiedMarriedYear() {
        final Person spouseObject = PersonUtil.createSpouse(initialPersonObject, 30);

        System.out.println(spouseObject);

        assertTrue("Death Year is greater then Birth Year", spouseObject.getDYear() >= spouseObject.getBYear());
        assertTrue("Death Age is greater then Married Age", spouseObject.getDYear() >= 30);
    }

    @Test
    public void getOppositeGender() {
        assertEquals("Expected Male", Gender.MALE, PersonUtil.getOppositeGender(Gender.FEMALE));
        assertEquals("Expected Female", Gender.FEMALE, PersonUtil.getOppositeGender(Gender.MALE));
    }

    @Test
    public void getBirthYearFemale() {
        final Integer result = PersonUtil.getBirthYear(100, Gender.FEMALE);

        assertTrue("BirthYear is out of range: " + result, 79 <= result && result <= 87);
    }

    @Test
    public void getBirthYearMale() {
        final Integer result = PersonUtil.getBirthYear(100, Gender.MALE);

        assertTrue("BirthYear is out of range: " + result, 64 <= result && result <= 87);
    }

    @Test
    public void getDeathAge() {
        final Integer deathAge = PersonUtil.getDeathAge(15);

        assertTrue("Death Age is less then Married Age", deathAge >= 15);
    }

    @Test
    public void getMarriageYear() {
    }

    @Test
    public void getRandomGender() {
    }
}