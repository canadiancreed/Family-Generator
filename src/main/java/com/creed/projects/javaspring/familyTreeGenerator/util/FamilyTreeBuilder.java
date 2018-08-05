package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import com.creed.projects.javaspring.familyTreeGenerator.config.FamilyTreeConfiguration;
import com.creed.projects.javaspring.familyTreeGenerator.config.PersonZeroConfiguration;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Component
public class FamilyTreeBuilder {

    private int currentYear;
    private int stopAtYear;

    private int currentPersonId;
    private int spousePersonId;
    private ArrayList<Integer> processChildrenIds = new ArrayList<>();
    private ArrayList<Integer> futureChildrenIDs = new ArrayList<>();

    private LinkedHashMap<Integer, Person> currentFamilyTreeCollection = new LinkedHashMap<>();

    //todo- Autowiriing this directory won't work for some reason
    private final PersonZeroConfiguration pzc;
    private final FamilyTreeConfiguration ftc;

    public FamilyTreeBuilder(final PersonZeroConfiguration pzc, final FamilyTreeConfiguration ftc) {
        this.pzc = pzc;
        this.ftc = ftc;

        createFamily(true);

        //Is there any children IDs to process?
        //If so, loop through them and see if they should have spouse and children created
        //If so, create and populate childrenID with their children
//        while (currentChildrenIDCount() > 0 && reachedEndYear()) {

//        for (int i = 0; i < 1; i++) {
            createFamily(false);
//        }
    }

    private void init() {
        this.currentPersonId = 0;
        this.spousePersonId = 0;
    }

    public void createFamily(final boolean initialFamily) {

        if (!initialFamily) {
            for (int i = 0; i < processChildrenIds.size(); i++) {
                //Saves multiple searching of the array
                init();

                final Person currentPersonObject = currentFamilyTreeCollection.get(processChildrenIds.get(i));

                currentPersonId = currentPersonObject.getId();

                //If this person isn't a bachelor and is older then 11
                if (!isBachelor() && (currentPersonObject.getDYear() - currentPersonObject.getBYear()) > 11) {
                    //Having kids when they're dead young. Needs a check
                    //addSpouses(currentPersonObject.getBYear());
//                    final Integer marriageYear = PersonUtil.getMarriageYear(currentPersonObject.getGender());
//
//                    if (marriageYear < currentPersonObject.getDYear()) {
                        addSpouses(0);
                        createChildren();
//                    }


                }

                processChildrenIds.remove(i);
            }

            //Update with new generation of children
            if (currentChildrenIDCount() == 0) {
                processChildrenIds = new ArrayList<>(futureChildrenIDs);
                futureChildrenIDs.clear();
            }
        } else {
            //Create root family
            createPersonZero();
            addSpouses(0);
            createChildren();

            processChildrenIds = new ArrayList<>(futureChildrenIDs);
            futureChildrenIDs.clear();
        }
    }

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

    public void addSpouses(final Integer marriageYear) {
        final Person initialPersonObject = currentFamilyTreeCollection.get(currentPersonId);

        LinkedHashMap<Integer, Integer> spousePersonObjectSpouseArray = new LinkedHashMap<>();

        Integer spouseMarriedYear = 0;

        if (marriageYear == 0) {
            spouseMarriedYear = pzc.getMarriageYear();
        } else {
            spouseMarriedYear = marriageYear;
        }

        //Create a new spouse instance.

        final Person spousePersonObject = PersonUtil.createSpouse(initialPersonObject, spouseMarriedYear);

        final LinkedHashMap<Integer, Integer> tempSpousePersonSpouseArray = spousePersonObject.getSpouseArray();

        spousePersonObjectSpouseArray.put(spousePersonObject.getId(), tempSpousePersonSpouseArray.get(initialPersonObject.getId()));
        initialPersonObject.setSpouseArray(spousePersonObjectSpouseArray);

        spousePersonId = spousePersonObject.getId();

        currentFamilyTreeCollection.put(spousePersonObject.getId(), spousePersonObject);

        if (currentYear < spousePersonObject.getBYear()) {
            currentYear = spousePersonObject.getBYear();
        }
    }

    public void createChildren() {
        final Person initialPersonObject = currentFamilyTreeCollection.get(currentPersonId);
        final Person spousePersonObject = currentFamilyTreeCollection.get(spousePersonId);

        Person femaleSpouseObject = null;
        Person maleSpouseObject = null;

        if (initialPersonObject.getGender().equals("FEMALE")) {
            femaleSpouseObject = initialPersonObject;
            maleSpouseObject = spousePersonObject;
        } else {
            maleSpouseObject = initialPersonObject;
            femaleSpouseObject = spousePersonObject;
        }

        final ArrayList<Person> childrenArray = PersonUtil.createChildren(maleSpouseObject, femaleSpouseObject);
        final ArrayList<Integer> currentChildrenIDList = new ArrayList<>();

        for (int i = 0; i < childrenArray.size(); i++) {
            currentChildrenIDList.add(childrenArray.get(i).getId());

            currentFamilyTreeCollection.put(childrenArray.get(i).getId(), childrenArray.get(i));
        }

        initialPersonObject.setChildredArray(currentChildrenIDList);
        spousePersonObject.setChildredArray(currentChildrenIDList);

        futureChildrenIDs.addAll(currentChildrenIDList);

        currentFamilyTreeCollection.put(currentPersonId, initialPersonObject);
        currentFamilyTreeCollection.put(spousePersonId, spousePersonObject);
    }

    public LinkedHashMap<Integer, Person> returnCurrentFamilyTreeCollection() {
        return currentFamilyTreeCollection;
    }

    public int currentChildrenIDCount() {
        return processChildrenIds.size();
    }

    public boolean isBachelor() {
        return PersonUtil.rollDice(100) <= ftc.getChanceIsBachelor();
    }

    public boolean reachedEndYear() {
        return currentYear <= stopAtYear;
    }
}
