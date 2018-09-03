package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class FamilyTreeBuilderTest {

    private Person femaleMarriedWithKids;
    private Person maleMarriedWithKids;

    private LinkedHashMap<Integer, Person> currentFamilyTreeCollection;

    @Before
    public void setUp() throws Exception {

        currentFamilyTreeCollection = new LinkedHashMap<>();

        femaleMarriedWithKids.setId(1);
        femaleMarriedWithKids.setGender(Gender.FEMALE);
        femaleMarriedWithKids.setFName("Female");
        femaleMarriedWithKids.setLName("Married With Kids");
        femaleMarriedWithKids.setMoniker("");
        femaleMarriedWithKids.setBYear(100);
        femaleMarriedWithKids.setDYear(150);
        femaleMarriedWithKids.setFatherID(0);
        femaleMarriedWithKids.setMotherID(0);
        femaleMarriedWithKids.setSpouseArray(new LinkedHashMap<Integer, Integer>());
        femaleMarriedWithKids.setChildredArray(new ArrayList<Integer>());

        maleMarriedWithKids.setId(2);
        maleMarriedWithKids.setGender(Gender.MALE);
        maleMarriedWithKids.setFName("Male");
        maleMarriedWithKids.setLName("Married With Kids");
        maleMarriedWithKids.setMoniker("");
        maleMarriedWithKids.setBYear(100);
        maleMarriedWithKids.setDYear(150);
        maleMarriedWithKids.setFatherID(0);
        maleMarriedWithKids.setMotherID(0);
        maleMarriedWithKids.setSpouseArray(new LinkedHashMap<Integer, Integer>());
        maleMarriedWithKids.setChildredArray(new ArrayList<Integer>());

        currentFamilyTreeCollection.put(1, femaleMarriedWithKids);
        currentFamilyTreeCollection.put(2, maleMarriedWithKids);
    }

    @Test
    public void createPersonZero() {
    }

    @Test
    public void addSpouses() {
    }

    @Test
    public void executeAllChildrenTests() {
        createFemaleSpouseMarriedAtTwentyFamily();
        createFemaleSpouseMarriedAtThirtyFamily();
        createFemaleSpouseMarriedAtFourtyFamily();
        createFemaleSpouseMarriedAtFiftyFamily();
        createMaleSpouseMarriedAtTwentyFamily();
        createMaleSpouseMarriedAtThirtyFamily();
        createMaleSpouseMarriedAtFourtyFamily();
        createMaleSpouseMarriedAtFiftyFamily();
    }

    public void createFemaleSpouseMarriedAtTwentyFamily() {
//        final FamilyTreeBuilder familyTreeBuilderObject = new FamilyTreeBuilder();
//
//        familyTreeBuilderObject.addSpouses(currentFamilyTreeCollection.get(2).getId());
    }

    public void createFemaleSpouseMarriedAtThirtyFamily() {
    }

    public void createFemaleSpouseMarriedAtFourtyFamily() {
    }

    public void createFemaleSpouseMarriedAtFiftyFamily() {
    }

    public void createMaleSpouseMarriedAtTwentyFamily() {
    }

    public void createMaleSpouseMarriedAtThirtyFamily() {
    }

    public void createMaleSpouseMarriedAtFourtyFamily() {
    }

    public void createMaleSpouseMarriedAtFiftyFamily() {
    }

    @Test
    public void createChildren() {
    }

    @Test
    public void returnCurrentFamilyTreeCollection() {
    }
}