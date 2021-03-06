package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;

import java.util.LinkedHashMap;
import java.util.Random;

import static com.creed.projects.javaspring.familyTreeGenerator.common.Gender.MALE;

/**
 * Contains all helper methods for populating Person related data
 */
public class PersonUtil {

    //Primary functions

    public static Person createSpouse(final Person partnerPersonObject, final Integer marriedYear) {
        Person spousePersonObject = new Person();
        LinkedHashMap<Integer, Integer> currentSpouseArray = new LinkedHashMap<>();

        spousePersonObject.setId(PersonUtil.generateRandomID());
        spousePersonObject.setFName("");
        spousePersonObject.setLName("");
        spousePersonObject.setMoniker("");
        spousePersonObject.setGender(PersonUtil.getOppositeGender(partnerPersonObject.getGender()));
        spousePersonObject.setBYear(PersonUtil.generateBirthYear(marriedYear, spousePersonObject.getGender()));

        spousePersonObject.setDYear(PersonUtil.useMarriedDataToGetDeathYear(marriedYear,
                                                                marriedYear - spousePersonObject.getBYear(),
                                                                            PersonUtil.generateDeathAge()));

        //todo - Do we really need to have IDs for people that don't exist?
        spousePersonObject.setFatherID(0);
        spousePersonObject.setMotherID(0);

//        if (partnerPersonObject.getFatherID() == 0 && partnerPersonObject.getMotherID() == 0) {
//            spousePersonObject.setFatherID(0);
//            spousePersonObject.setMotherID(0);
//        } else {
//            spousePersonObject.setFatherID(PersonUtil.generateRandomID());
//            spousePersonObject.setMotherID(PersonUtil.generateRandomID());
//        }

        currentSpouseArray.put(partnerPersonObject.getId(), marriedYear);

        spousePersonObject.setSpouseArray(currentSpouseArray);

        return spousePersonObject;
    }

    public static Person createChild(final int fatherID, final int motherID, final int birthYear) {
        Person childPersonObject = new Person();

        childPersonObject.setId(PersonUtil.generateRandomID());
        childPersonObject.setFName("");
        childPersonObject.setLName("");
        childPersonObject.setMoniker("");
        childPersonObject.setGender(PersonUtil.getRandomGender());
        childPersonObject.setBYear(birthYear);
        childPersonObject.setDYear(birthYear + PersonUtil.generateDeathAge());

        childPersonObject.setFatherID(fatherID);
        childPersonObject.setMotherID(motherID);

        return childPersonObject;
    }

    //Secondary functions

    public static Integer generateBirthYear(final int marriedYear, final Gender gender) {

        // women (who are likely to produce progeny) marry from age 13-21
        int marriedAge = rollDice(9) + 12;

        if (gender.toString().equals("MALE")) {
            for (int i = 0; i < 5; i++) {
                marriedAge += rollDice(4) - 1;
            }
        }

        int birthYear = marriedYear - marriedAge;

        return birthYear;
    }

    /**
     * Calculate Death Year by marriedYear and marriedAge
     *
     * The original breakdown was 50% dead at marriedAge, 12.5% for the rest. This leaves for a LOT of dead the same year they're married
     *
     * Revised makes it so 75% is random + 20 or less instead, removing 50% dead at married Age
     *
//     * @param marriedYear
//     * @param marriedAge
     * @return
     */
//    public static Integer generateDeathAge(final int marriedYear, final int marriedAge) {
    public static Integer generateDeathAge() {

        final int temporaryOne = rollDice(20);
        final int temporaryTwo = rollDice(20);

        int randomAge = (temporaryOne < temporaryTwo) ? temporaryOne : temporaryTwo;

        int deathChancePercentage = rollDice(8);

        int deathAge = 0;

        //Odds of death at age x
        if (deathChancePercentage < 5) {
            deathAge = randomAge;
        } else if (deathChancePercentage < 7) {
            deathAge = randomAge + 20;
        } else if (deathChancePercentage == 7) {
            deathAge = randomAge + 40;
        } else if (deathChancePercentage == 8) {
            deathAge = randomAge + 60;
        } else {
            //throw Exzception
        }

        return deathAge;
    }

    //Utility functions that aren't core

    public static int useMarriedDataToGetDeathYear(final int marriedYear, final int marriedAge, final int deathAge) {
        int newDeathAge = 0;

        if (deathAge < marriedAge) {
            newDeathAge = marriedAge;
        } else {
            newDeathAge = deathAge;
        }

        return marriedYear + (newDeathAge - marriedAge);
    }

    /**
     * Roll a D-sided dice, resulting in a number from 1 to D
     *
     * @param sides
     * @return int
     */
    public static int rollDice(final int sides) {
        return (int) (Math.round(Math.random() * (sides-1)) + 1);
    }

    /**
     * Generate random long id for person
     *
     * @return long
     */
    public static int generateRandomID() {
        Random random = new Random();

        return random.nextInt() &  Integer.MAX_VALUE;
    }

    /**
     * Gets opposite gender for spouse
     *
     * @param gender
     * @return
     */
    public static Gender getOppositeGender(final Gender gender) { // flip gender (for the spouse) if one is given
        Gender newgen = null;

        switch(gender.toString()) {			 // otherwise randomize it
            case "MALE": newgen = Gender.FEMALE;break;
            case "FEMALE": newgen = MALE;break;
            default: throw new IllegalArgumentException("Specify a valid gender");
        }

        return newgen;
    }

    /**
     * Calculate years between spouse death and remarriage
     */
    public static int calculateYearsTillReMarriage(final int deathYear) {
        int yearsInGrief = rollDice(2) + rollDice(2) + rollDice(2) - 2;

        int yearWhenRemarry = deathYear + yearsInGrief;

        return yearWhenRemarry;
    }

    /**
     * Calculate fertility rate based on current age
     * @param age
     * @return
     */
    public static Integer getFertilityPercentage(final int age) {
        int chance = 0;

        if (age < 14) { chance = 10; }
        if (age == 14) { chance = 20; }
        if (age == 15) { chance = 40; }
        if (age == 16) { chance = 60; }
        if (age == 17) { chance = 80; }
        if (age > 17 && age < 30) { chance = 98; }
        if (age > 30 && age < 35) { chance = 80; }
        if (age > 35 && age < 40) { chance = 40; }
        if (age > 40 && age < 45) { chance = 20; }
        if (age > 44) { chance = 3; }
        if (age > 52) { chance = 1; }

        return chance;
    }

    public static Gender getRandomGender() {
        final int oddsResult = rollDice(2);
        Gender gender;

        switch (oddsResult) {
            case 1:
                gender = MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            default: throw new IllegalArgumentException("A random gender can't be chosen");
        }

        return gender;
    }
}
