package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import com.creed.projects.javaspring.familyTreeGenerator.config.FamilyTreeConfiguration;
import com.creed.projects.javaspring.familyTreeGenerator.config.PersonZeroConfiguration;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

import static com.creed.projects.javaspring.familyTreeGenerator.util.PersonUtil.*;

/**
 * This class builds and creates the entire family tree
 */

public class FamilyTreeBuilder {

    private Integer currentYear;
    private Integer stopAtYear;

    private ArrayList<Integer> futureChildrenToProcessArray = new ArrayList<>();
    private LinkedHashMap<Integer, Person> currentFamilyTreeCollection = new LinkedHashMap<>();
    private LinkedHashMap<Integer, Person> processFamilyTreeMembers = new LinkedHashMap<>();

    private Integer currentGeneration = 0;

    //todo- Autowiriing this directory won't work for some reason
    private final PersonZeroConfiguration pzc;
    private final FamilyTreeConfiguration ftc;

    private static final Logger LOGGER = LoggerFactory.getLogger(FamilyTreeBuilder.class);

    public FamilyTreeBuilder(final PersonZeroConfiguration pzc, final FamilyTreeConfiguration ftc) {

        this.pzc = pzc;
        this.ftc = ftc;

        //Create family for group zero
        final int initialPersonId = createPersonZero();

        addSpouses(initialPersonId);

        final LinkedHashMap<Integer, Integer> initialPersonMarriedArray = currentFamilyTreeCollection.get(initialPersonId).getSpouseArray();

        initialPersonMarriedArray.forEach((initialSpouseId, initialMarriedYear) -> {
            createChildren(currentFamilyTreeCollection.get(initialPersonId), currentFamilyTreeCollection.get(initialSpouseId));
        });

        //Put the initial family in this array to process
        processFamilyTreeMembers = new LinkedHashMap<>(currentFamilyTreeCollection);

        boolean continueProcessing = true;

        //todo - this is temporary for testing
        stopAtYear = 0;

        //Now create family tree branches
        while (continueProcessing) {
            final ArrayList<Integer> currentChildToProcessArray = new ArrayList<>(futureChildrenToProcessArray);

            futureChildrenToProcessArray.clear();

            currentChildToProcessArray.forEach((childID) -> {
                final Person childObject = currentFamilyTreeCollection.get(childID);

                boolean willMarry = true;

                //Now to determine if they'll get married and have kids or not
                if (ftc.getChanceIsBachelor() > rollDice(100)) {
                    willMarry = false;
                }

                if ((childObject.getDYear() - childObject.getBYear()) < 14) {
                    willMarry = false;
                }

                if (willMarry) {
                    addSpouses(childID);

                    final LinkedHashMap<Integer, Integer> personMarriedArray = currentFamilyTreeCollection.get(childID).getSpouseArray();

                    personMarriedArray.forEach((spouseId, marriedYear) -> {
                        if (ftc.getChanceIsInfertile() <= rollDice(100)) {
                            createChildren(currentFamilyTreeCollection.get(childID), currentFamilyTreeCollection.get(spouseId));
                        }
                    });
                }
            });

            //Check if both are not empty. If so, we need to check the stop year. If stop year is met, set to false.
            if (!currentChildToProcessArray.isEmpty() && !futureChildrenToProcessArray.isEmpty()) {
                if (currentGeneration == 10) { continueProcessing = false; }
            } else {
                continueProcessing = false;
            }

            //todo - here for testing
            //stopAtYear++;

            //Compare what's in two different arrays. Any ids that exists in both are removed from current
            //The person objects are then written to file.
            //The person array is cleared
            //The current ID arrays is then copied
            writeUnusedPersonObjectsToFile();

            if (currentGeneration == 10) { continueProcessing = false; }
        }
    }

    /**
     *  Creates person zero, the root of this family tree
     */
    public int createPersonZero() {
        Person initialPersonObject = new Person();

        Gender gender = pzc.getGender().equals(Gender.MALE.toString()) ? Gender.FEMALE : Gender.MALE;

        initialPersonObject.setId(PersonUtil.generateRandomID());
        initialPersonObject.setFName(pzc.getFirstName());
        initialPersonObject.setLName(pzc.getLastName());
        initialPersonObject.setMoniker(pzc.getMoniker());
        initialPersonObject.setGender(gender);
        initialPersonObject.setBYear(pzc.getBirthYear());
        initialPersonObject.setDYear(pzc.getDeathYear());

        currentFamilyTreeCollection.put(initialPersonObject.getId(), initialPersonObject);

        currentYear = pzc.getBirthYear();
        stopAtYear = pzc.getCurrentYear();

        return initialPersonObject.getId();
    }

    public void addSpouses(final int personID) {
        final Person initialPersonObject = currentFamilyTreeCollection.get(personID);

        int marriedYear = 0;

        //todo - Test to see if this check for initial start couple can be broken?
        if (initialPersonObject.getFatherID() == 0 & initialPersonObject.getFatherID() == 0) {
            marriedYear = pzc.getMarriageYear();
        } else {
            //get random age between 14-26, then add to birthYear
            int marriedAge = rollDice(4) + rollDice(4) + rollDice(4) + rollDice(4) + 10;
            marriedYear = marriedAge + initialPersonObject.getBYear();
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

        final ArrayList<Integer> currentChildredIDArray = new ArrayList<>();

        final int yearMarriageEnds = person.getDYear() < spouse.getDYear() ? person.getDYear() : spouse.getDYear();

        final int numberOfYearsMarried = yearMarriageEnds - person.getSpouseArray().get(spouse.getId());

        int ageOfSpouseWhenMarried;
        int fatherID;
        int motherID;

        if (person.getBYear() < 0 || spouse.getBYear() < 0) {
            if (person.getGender().toString().equals("FEMALE")) {
                ageOfSpouseWhenMarried = Math.abs(person.getBYear()) + person.getSpouseArray().get(spouse.getId());
                fatherID = spouse.getId();
                motherID = person.getId();
            } else {
                ageOfSpouseWhenMarried = Math.abs(spouse.getBYear()) + spouse.getSpouseArray().get(person.getId());
                fatherID = person.getId();
                motherID = spouse.getId();
            }
        } else {
            if (person.getGender().toString().equals("FEMALE")) {
                ageOfSpouseWhenMarried = person.getSpouseArray().get(spouse.getId()) - person.getBYear();
                fatherID = spouse.getId();
                motherID = person.getId();
            } else {
                ageOfSpouseWhenMarried = spouse.getSpouseArray().get(person.getId()) - spouse.getBYear();
                fatherID = person.getId();
                motherID = spouse.getId();
            }
        }

        int yearsMarried = 0;

        ArrayList<Integer> childIDArray = new ArrayList<>();

        while (yearsMarried <= numberOfYearsMarried) {
            if (rollDice(100) <= PersonUtil.getFertilityPercentage(ageOfSpouseWhenMarried + yearsMarried)) {

                final int birthYear = person.getSpouseArray().get(spouse.getId()) + yearsMarried;

                final Person childPersonObject = createChild(fatherID, motherID, birthYear);

                childIDArray.add(childPersonObject.getId());

                currentFamilyTreeCollection.put(childPersonObject.getId(), childPersonObject);

                currentChildredIDArray.add(childPersonObject.getId());
            }

            yearsMarried++;
        }

        person.setChildredArray(childIDArray);
        spouse.setChildredArray(childIDArray);

        currentFamilyTreeCollection.put(spouse.getId(), spouse);
        currentFamilyTreeCollection.put(person.getId(), person);

        futureChildrenToProcessArray.addAll(currentChildredIDArray);
    }

    public LinkedHashMap<Integer, Person> returnCurrentFamilyTreeCollection() {
        return currentFamilyTreeCollection;
    }

    private void writeUnusedPersonObjectsToFile() {

        //Get the keys from the currentFamilyTreeCollection, and extract the keys that dont match
        final ArrayList<Integer> currentFamilyTreeCollectionKeySet = new ArrayList<Integer>(currentFamilyTreeCollection.keySet());

        currentFamilyTreeCollectionKeySet.removeAll(new HashSet<>(futureChildrenToProcessArray));

        PersonFileFileWriter.setFileName("test.csv");

        PersonFileFileWriter.openConnection();

        LOGGER.info("Saving generation " + currentGeneration + ".");

        for (Integer ftcKey : currentFamilyTreeCollectionKeySet) {
            PersonFileFileWriter.writeDataToFile(currentFamilyTreeCollection.get(ftcKey));
            currentFamilyTreeCollection.remove(ftcKey);
        }

        PersonFileFileWriter.closeConnection();

        currentFamilyTreeCollectionKeySet.clear();

        LOGGER.info("Generation " + currentGeneration + " saved.");

        currentGeneration++;
    }
}
