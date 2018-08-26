package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import com.creed.projects.javaspring.familyTreeGenerator.config.FamilyTreeConfiguration;
import com.creed.projects.javaspring.familyTreeGenerator.config.PersonZeroConfiguration;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static com.creed.projects.javaspring.familyTreeGenerator.util.PersonUtil.createChild;
import static com.creed.projects.javaspring.familyTreeGenerator.util.PersonUtil.createSpouse;
import static com.creed.projects.javaspring.familyTreeGenerator.util.PersonUtil.rollDice;

/**
 * This class builds and creates the entire family tree
 */

public class FamilyTreeBuilder {

    private Integer currentYear;
    private Integer stopAtYear;

    private int currentPersonId;

    private LinkedHashMap<Integer, Person> currentFamilyTreeCollection = new LinkedHashMap<>();

    //todo- Autowiriing this directory won't work for some reason
    private final PersonZeroConfiguration pzc;
    private final FamilyTreeConfiguration ftc;


    public FamilyTreeBuilder(final PersonZeroConfiguration pzc, final FamilyTreeConfiguration ftc) {

        this.pzc = pzc;
        this.ftc = ftc;

        //Create family for group zero
        createPersonZero();

        final int personId = currentPersonId;

        addSpouses();

        final LinkedHashMap<Integer, Integer> personMarriedArray = currentFamilyTreeCollection.get(personId).getSpouseArray();

        personMarriedArray.forEach((spouseId, marriedYear) -> {
            createChildren(currentFamilyTreeCollection.get(personId), currentFamilyTreeCollection.get(spouseId));
        });

//        for ()
//        createChildren();


    }

    /**
     *  Creates person zero, the root of this family tree
     */
    public void createPersonZero() {
        Person initialPersonObject = new Person();

        Gender gender = pzc.getGender().equals(Gender.MALE.toString()) ? Gender.FEMALE : Gender.MALE;

        initialPersonObject.setId(PersonUtil.generateRandomID());
        initialPersonObject.setFName(pzc.getFirstName());
        initialPersonObject.setLName(pzc.getLastName());
        initialPersonObject.setMoniker(pzc.getMoniker());
        initialPersonObject.setGender(gender);
        initialPersonObject.setBYear(pzc.getBirthYear());
        initialPersonObject.setDYear(pzc.getDeathYear());

        currentPersonId = initialPersonObject.getId();

        currentFamilyTreeCollection.put(initialPersonObject.getId(), initialPersonObject);

        currentYear = pzc.getBirthYear();
        stopAtYear = pzc.getCurrentYear();
    }

    public void addSpouses() {
        final Person initialPersonObject = currentFamilyTreeCollection.get(currentPersonId);

        int marriedYear = 0;

        //todo - Test to see if this check for initial start couple can be broken?
        if (initialPersonObject.getFatherID() == 0 & initialPersonObject.getFatherID() == 0) {
            marriedYear = pzc.getMarriageYear();
//        } else {
//
        }

        //Create temp array of spouseIDs
        LinkedHashMap<Integer, Integer> spouseIDArray = new LinkedHashMap<>();
        Person spousePersonObject = null;
        boolean marriedAgain = true;
        int newMarriedYear = 0;

        //Put this in a loop that checks if the spouseDeath date < initial person date, and loops if needed
        while (marriedAgain) {
            spousePersonObject = createSpouse(currentFamilyTreeCollection.get(initialPersonObject.getId()), marriedYear);

            spouseIDArray.put(spousePersonObject.getId(), marriedYear);

            if (spousePersonObject.getDYear() < initialPersonObject.getDYear()) {
                
                //get mourningYears
                newMarriedYear = PersonUtil.calculateYearsTillReMarriage(spousePersonObject.getDYear());

                marriedYear = newMarriedYear;

                currentFamilyTreeCollection.put(spousePersonObject.getId(), spousePersonObject);
            } else {
                marriedAgain = false;
            }
        }

        //end of loop

        initialPersonObject.setSpouseArray(spouseIDArray);

        currentFamilyTreeCollection.put(spousePersonObject.getId(), spousePersonObject);
        currentFamilyTreeCollection.put(initialPersonObject.getId(), initialPersonObject);
    }

    public void createChildren(final Person person, final Person spouse) {

        final int yearMarriageEnds = person.getDYear() < spouse.getDYear() ? person.getDYear() : spouse.getDYear();

        final int numberOfYearsMarried = yearMarriageEnds - person.getSpouseArray().get(spouse.getId());

        int ageOfSpouseWhenMarried;
        int fatherID;
        int motherID;

        if (person.getGender().equals("FEMALE")) {
            ageOfSpouseWhenMarried = Math.abs(person.getBYear()) + person.getSpouseArray().get(spouse.getId());
            fatherID = spouse.getId();
            motherID = person.getId();
        } else {
            ageOfSpouseWhenMarried = Math.abs(spouse.getBYear()) + spouse.getSpouseArray().get(person.getId());
            fatherID = person.getId();
            motherID = spouse.getId();
        }

        int yearsMarried = 0;

        ArrayList<Integer> childIDArray = new ArrayList<>();

        while (yearsMarried <= numberOfYearsMarried) {
            if (rollDice(100) <= PersonUtil.getFertilityPercentage(ageOfSpouseWhenMarried + yearsMarried)) {

                final int birthYear = person.getSpouseArray().get(spouse.getId()) + yearsMarried;

                final Person childPersonObject = createChild(fatherID, motherID, birthYear);

                childIDArray.add(childPersonObject.getId());

                currentFamilyTreeCollection.put(childPersonObject.getId(), childPersonObject);
            }

            yearsMarried++;
        }

        person.setChildredArray(childIDArray);
        spouse.setChildredArray(childIDArray);

        currentFamilyTreeCollection.put(spouse.getId(), spouse);
        currentFamilyTreeCollection.put(person.getId(), person);
    }

    public LinkedHashMap<Integer, Person> returnCurrentFamilyTreeCollection() {
        return currentFamilyTreeCollection;
    }
}
