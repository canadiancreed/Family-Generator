package com.company;

import com.company.domain.Person;
import com.company.util.Gender;
import com.company.util.PersonUtil;

public class Main {

    private static int currentYear = 0;

    private static Person currentPerson;
    private static Integer currentPersonMarriedYear;

    public static void main(final String[] args) {
        initialize(args);

        PersonUtil.createSpouse(currentPerson);
        //System.out.println(familyTree);
    }

    /**
     * Sets up the enviroment to accept data
     *
     * - 0 - First name of initial starting person
     * - 1 - Last name/house name of initial starting person
     * - 2 - Moniker, if any, of initial starting person
     * - 3 - Birth year/start years
     * - 4 - current/end year
     *
     * @param args
     */
    private static void initialize(final String[] args) {
        Person initialPerson = new Person();

        Gender gender = args[3].equals(Gender.MALE.toString()) ? Gender.FEMALE : Gender.MALE;

        initialPerson.setId(PersonUtil.generateRandomID());
        initialPerson.setFName(args[0]);
        initialPerson.setLName(args[1]);
        initialPerson.setMoniker(args[2]);
        initialPerson.setGender(gender);
        initialPerson.setBYear(Integer.parseInt(args[4]));

        currentPerson = initialPerson;

        currentPersonMarriedYear = Integer.parseInt(args[5]);
        currentYear = Integer.parseInt(args[6]);

//        PersonUtil.createFamily(initialPerson.getId());

//        ArrayList<LinkedHashMap<Integer, Integer>> spouses = initialPerson.getSpouseArray();
//
//        spouses.add(spouse.getId(), spouse.getMYear);


        /**
         * As it's the first person,l we perform all the normal actions for a person here. Like marriage calculations
         * kids, death year, that sort of thing.
         */

        //getSpouse
//        Person initialSpouse = PersonUtil.createSpouse();

        //getKids
        //getDeathYear
//        LinkedHashSet<Person> aaa = getFamilyTree();
//
//        aaa.add(initialPerson);
//
//        setFamilyTree(aaa);
        //familyTree.add(initialPerson);
//        familyTree.add(initialSpouse);

        currentYear = Integer.parseInt(args[6]);
    }



//    private static LinkedHashSet<Person> getFamilyTree() { return familyTree; }
//
//    private static void setFamilyTree(final LinkedHashSet<Person> familyTree) { Main.familyTree = familyTree; }
}
