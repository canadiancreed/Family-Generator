package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;

import java.util.LinkedHashMap;
import java.util.Random;

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

        //todo - Make code for generating married year - still needed?

        spousePersonObject.setDYear(PersonUtil.generateDeathYear(marriedYear, marriedYear - spousePersonObject.getBYear()));

        if (partnerPersonObject.getFatherID() == 0 && partnerPersonObject.getMotherID() == 0) {
            spousePersonObject.setFatherID(0);
            spousePersonObject.setMotherID(0);
        } else {
            spousePersonObject.setFatherID(PersonUtil.generateRandomID());
            spousePersonObject.setMotherID(PersonUtil.generateRandomID());
        }

        currentSpouseArray.put(partnerPersonObject.getId(), marriedYear);

        spousePersonObject.setSpouseArray(currentSpouseArray);

        return spousePersonObject;
    }

//    public static Person createChild() {
//
//    }

    //Secondary functions

    public static Integer generateBirthYear(final int marriedYear, final Gender gender) {

        // women (who are likely to produce progeny) marry from age 13-21
        int marriedAge = rollDice(9) + 12;

        if (gender.equals("MALE")) {
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
     * @param marriedYear
     * @param marriedAge
     * @return
     */
    public static Integer generateDeathYear(final int marriedYear, final int marriedAge) {

        final int temporaryOne = rollDice(20);
        final int temporaryTwo = rollDice(20);

        int randomAge = (temporaryOne < temporaryTwo) ? temporaryOne : temporaryTwo;

        int deathChancePercentage = rollDice(8);

        int deathAge = 0;

        //Odds of death at age x
//        if (deathChancePercentage < 5) {
//            deathAge = randomAge;
//        } else if (deathChancePercentage < 7) {
//            deathAge = randomAge + 20;
//        } else if (deathChancePercentage == 7) {
//            deathAge = randomAge + 40;
//        } else if (deathChancePercentage == 8) {
//            deathAge = randomAge + 60;
//        } else {
//            //throw Exzception
//        }

        if (deathChancePercentage < 7) {
            deathAge = randomAge + 20;
        } else if (deathChancePercentage == 7) {
            deathAge = randomAge + 40;
        } else if (deathChancePercentage == 8) {
            deathAge = randomAge + 60;
        } else {
            //throw Exzception
        }

        if (deathAge < marriedAge) {
            deathAge = marriedAge;
        }

        //todo - Not sure about this. Evaluate on non-married
        final int deathYear = marriedYear + (deathAge - marriedAge);

        return deathYear;
    }

    //Utility functions that aren't core

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
            case "FEMALE": newgen = Gender.MALE;break;
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
}
