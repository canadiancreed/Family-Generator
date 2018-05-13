package com.company.util;

import com.company.domain.Person;

import java.util.Random;

/**
 * Contains all helper methods for populating Person related data
 */
public class PersonUtil {

    /**
     * Roll a D-sided dice, resulting in a number from 1 to D
     *
     * @param sides
     * @return long
     */
    public static byte rollDice(final int sides) {
        return (byte) (Math.round(Math.random() * (sides-1)) + 1);
    }

    /**
     * Generate random long id for person
     *
     * @return long
     */
    public static int generateRandomID() {
        Random random = new Random();

        return random.nextInt();
    }

    /**
     * Create a family for the particular person
     *
     * @param id
     */
    public static void createFamily(final long id) {
    }

    public static Person createSpouse(final Person person, final Integer manualMarriageYear) {
        Person currentSpouse = new Person();

        final int marriedYear = manualMarriageYear == 0 ? getMarriageYear(person.getGender()) : manualMarriageYear;
        final int marriedAge = 0;

        currentSpouse.setId(PersonUtil.generateRandomID());
        currentSpouse.setGender(PersonUtil.getOppositeGender(person.getGender()));
        currentSpouse.setFName(NameGenerator.generateFirstName(person.getGender()));
        currentSpouse.setLName(NameGenerator.generalLastName(person.getGender()));
        currentSpouse.setMoniker("");
        currentSpouse.setBYear(getBirthYear(marriedYear,currentSpouse.getGender()));
//        currentSpouse.setDYear(getDeathYear(person.getBYear(),currentSpouse.getGender()));
//        currentSpouse.setFatherID(0);
//        currentSpouse.setMotherID(0);


    }

    /**
     * Gets opposite gender for spouse
     *
     * @param gender
     * @return
     */
    private static Gender getOppositeGender(final Gender gender) { // flip gender (for the spouse) if one is given
        Gender newgen = null;

        switch(gender.toString()) {			 // otherwise randomize it
            case "M": newgen = Gender.FEMALE;break;
            case "F": newgen = Gender.MALE;break;
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
    private static Integer getBirthYear(final Integer marriedYear, final Gender gender) {
        Integer marriedAge;
        Integer birthYear;

        //women (who are likely to produce progeny) marry from age 13-21
        marriedAge = rollDice(9) + 12;

        // if male, potentially add a few years
        if (gender.toString().equals("M")) {
            for (int i = 0; i < 5; i++) {
                marriedAge += rollDice(4) - 1;
            }
        }

        birthYear = marriedYear - marriedAge;

        return birthYear;
    }

    private static Integer getDeathYear(final Integer marriedYear, final Integer marriedAge) {
        Integer deathAge = 0;

        var temp1a = rollD(20);
        var temp1b = rollD(20);
        var temp1;
        (temp1a < temp1b) ? temp1=temp1a : temp1=temp1b; // temp1 is the low of 2d20

        var temp2=rollD(8);

        var dage;
        if (temp2<5) {  // 50% dies as a child or teenager
            dage=temp1;
        } // this is to shape the prob curve of deaths
        else if (temp2<7) {
            deathAge=temp1a+20;  // 25% die in their 20-30's
        }
        else if (temp2==7) { // 12.5% die in their 40-50's
            deathAge=temp1a+40;
        }
        else if (temp2==8) { // 12.5% die in their 60-70's
            deathAge=temp1a+60;
        }

        if (deathAge && marriedAge) {  // Generating spouse, so should be alive when married...
            while (deathAge < marriedAge) {   // if died before married, set to marriage.
                deathAge = marriedAge;
            }
        }

        return deathAge;
    }

    private static Integer getMarriageYear(final Gender gender) {
       return Math.abs(getBirthYear(0,gender));
    }

    /**
     * Get a random gender. Used when making kid records
     * @return
     */
    private Gender getRandomGender() { // random gender
        return rollDice(6)<4 ? Gender.MALE : Gender.FEMALE;
    }
}
