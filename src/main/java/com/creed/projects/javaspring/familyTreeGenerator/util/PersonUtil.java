package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;
import com.creed.projects.javaspring.familyTreeGenerator.service.NameGeneratorService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Contains all helper methods for populating Person related data
 */
public class PersonUtil {

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
     * Create a family for the particular person
     *
     * @param id
     */
    public static void createFamily(final long id) {
    }

    /**
     * Crates a spouse object for the passed person object.
     *
     * @param spouseObject
     * @param manualMarriageYear
     * @return
     */
    public static Person createSpouse(final Person spouseObject, final Integer manualMarriageYear) {

        LinkedHashMap<Integer, Integer> currentSpouseArray = new LinkedHashMap<>();
        Person currentSpouse = new Person();
        int marriedAge = 0;

        final int marriedYear = manualMarriageYear == 0 ? getMarriageYear(spouseObject.getGender()) : manualMarriageYear;

        currentSpouse.setId(PersonUtil.generateRandomID());
        currentSpouse.setGender(PersonUtil.getOppositeGender(spouseObject.getGender()));
        currentSpouse.setFName(NameGeneratorService.generateFirstName(spouseObject.getGender()));
        currentSpouse.setLName(NameGeneratorService.generalLastName(spouseObject.getGender()));
        currentSpouse.setMoniker("");
        currentSpouse.setBYear(getBirthYear(marriedYear,currentSpouse.getGender()));

        marriedAge = marriedYear - currentSpouse.getBYear();

        int getDeathYear = getDeathAge(marriedAge) + currentSpouse.getBYear();

        currentSpouse.setDYear(getDeathYear);
        currentSpouse.setFatherID(0);
        currentSpouse.setMotherID(0);

        currentSpouseArray.put(spouseObject.getId(), marriedYear);

        currentSpouse.setSpouseArray(currentSpouseArray);

        return currentSpouse;
    }

    public static ArrayList<Person> createChildren(final Person maleSpouseObject, final Person femaleSpouseObject) {

        ArrayList<Person> childredArray = new ArrayList<>();

        int marriageYearEnd = 0;
        int numberYearsMarried = 0;
        int spouseAgeWhenMarried = 0;
        int yearsOfMarriage = 0;

        marriageYearEnd = maleSpouseObject.getDYear() < femaleSpouseObject.getDYear() ?
                maleSpouseObject.getDYear() : femaleSpouseObject.getDYear();

        numberYearsMarried = marriageYearEnd - femaleSpouseObject.getSpouseArray().get(maleSpouseObject.getId());

        spouseAgeWhenMarried = femaleSpouseObject.getSpouseArray().get(maleSpouseObject.getId()) - femaleSpouseObject.getBYear();

        while (yearsOfMarriage <= numberYearsMarried) {
            if (PersonUtil.rollDice(100) <= PersonUtil.getFertilityByAge(spouseAgeWhenMarried)) {
                Person child = new Person();

                child.setId(PersonUtil.generateRandomID());
                child.setGender(PersonUtil.getRandomGender());
                child.setFName(NameGeneratorService.generateFirstName(child.getGender()));
                child.setLName(NameGeneratorService.generalLastName(child.getGender()));
                child.setMoniker("");
                child.setBYear((marriageYearEnd - numberYearsMarried) + yearsOfMarriage);
                child.setDYear(child.getBYear() + PersonUtil.getDeathAge(0));
                child.setFatherID(maleSpouseObject.getId());
                child.setMotherID(femaleSpouseObject.getId());

                childredArray.add(child);
            }

            //Advance counter for next child. Minimum = 1, maximum = 6
            yearsOfMarriage += rollDice(2) + rollDice(2) + rollDice(2) - 2;

        }

        return childredArray;
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
     * Calculate birth year be taking married year and gender
     *
     * @param marriedYear
     * @param gender
     * @return
     */
    public static Integer getBirthYear(final Integer marriedYear, final Gender gender) {
        Integer marriedAge;
        Integer birthYear;

        //women (who are likely to produce progeny) marry from age 13-21
        marriedAge = rollDice(9) + 12;

        // if male, potentially add a few years
        if (gender.toString().equals("MALE")) {
            for (int i = 0; i < 5; i++) {
                marriedAge += rollDice(4) - 1;
            }
        }

        birthYear = marriedYear - marriedAge;

        return birthYear;
    }

    /**
     * Get the age that this person will die
     * 50% - DIes as child or teenager
     * 25% - Dies in 20's or 30's
     * 12.5% - Dies in 40's or 50's
     * 12.5% - Dies in 60's or 70's
     * todo - there's an issue where death age is less then married age with original code.
     * todo - There's a lot where people die on marriage year. Should have it if marriedAge > teenager, there's different odds
     *
     * @param marriedAge - 0 if unmarried
     * @return
     */
    public static Integer getDeathAge(final Integer marriedAge) {
        Integer deathAge = 0;

        final int firstRandomRoll = rollDice(20);
        final int secondRandomRoll = rollDice(20);

        final int getLowerRandomRoll = firstRandomRoll < secondRandomRoll ? firstRandomRoll : secondRandomRoll;

        final int percentage = rollDice(8);

        if (percentage < 5) {  // 50% dies as a child or teenager
            deathAge = getLowerRandomRoll;
        } // this is to shape the prob curve of deaths
        else if (percentage < 7) {
            deathAge = firstRandomRoll + 20;  // 25% die in their 20-30's
        }
        else if (percentage == 7) { // 12.5% die in their 40-50's
            deathAge = firstRandomRoll + 40;
        }
        else if (percentage == 8) { // 12.5% die in their 60-70's
            deathAge = firstRandomRoll + 60;
        }

//        if (deathAge == marriedAge) {  // Generating spouse, so should be alive when married...
//            while (deathAge < marriedAge) {   // if died before married, set to marriage.
//                deathAge = marriedAge;
//            }
//        }

        if (deathAge < marriedAge) {
            deathAge = marriedAge;
        }

        return deathAge;
    }

    public static Integer getMarriageYear(final Gender gender) {
       return Math.abs(getBirthYear(0,gender));
    }

    /**
     * Get a random gender. Used when making kid records
     * @return
     */
    public static Gender getRandomGender() { // random gender
        return rollDice(6)<4 ? Gender.MALE : Gender.FEMALE;
    }

    /**
     * Get chance of conceiving a child based on age
     *
     * @param spouseAgeWhenMarried
     * @return
     */
    public static Integer getFertilityByAge(final Integer spouseAgeWhenMarried) {
        Integer chance = 0;

        if (spouseAgeWhenMarried < 14) { chance = 10; }
        if (spouseAgeWhenMarried == 14) { chance = 20; }
        if (spouseAgeWhenMarried == 15) { chance = 40; }
        if (spouseAgeWhenMarried == 16) { chance = 60; }
        if (spouseAgeWhenMarried == 17) { chance = 80; }
        if (spouseAgeWhenMarried > 17 && spouseAgeWhenMarried < 30) { chance = 98; }
        if (spouseAgeWhenMarried > 30 && spouseAgeWhenMarried < 35) { chance = 80; }
        if (spouseAgeWhenMarried > 35 && spouseAgeWhenMarried < 40) { chance = 40; }
        if (spouseAgeWhenMarried > 40 && spouseAgeWhenMarried < 45) { chance = 20; }
        if (spouseAgeWhenMarried > 44) { chance = 3; }
        if (spouseAgeWhenMarried > 52) { chance = 1; }

        return chance;
    }

    /**
     * Delay in remarrying after a spouses death
     * @return - years
     */

    public static Integer getGriefLength(final Integer startYear) {
        return startYear + (rollDice(2) + rollDice(2) + rollDice(2) - 2);
    }
}
