package com.company.domain;

import com.company.util.Gender;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FamilyTreeTest {

    private Person personObject;

    @Before
    public void setUp() throws Exception {
        String data = "1," + Gender.MALE.toString() + ",First Name,Last Name,Moniker,1000,1100,100,200";
        String[] tokens = data.split(",");

        personObject = new Person();

        personObject.setId(Integer.valueOf(tokens[0]));
//        personObject.setGender(Long.valueOf(tokens[0]));
        personObject.setFName(String.valueOf(tokens[2]));
        personObject.setLName(String.valueOf(tokens[3]));
        personObject.setMoniker(String.valueOf(tokens[4]));
        personObject.setBYear(Integer.valueOf(tokens[5]));
        personObject.setDYear(Integer.valueOf(tokens[6]));
        personObject.setFatherID(Integer.valueOf(tokens[7]));
        personObject.setMotherID(Integer.valueOf(tokens[8]));
//        personObject.setSpouseArray();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToFamilyTree() {
        FamilyTree.addToFamilyTree(personObject);
        Assert.assertEquals(1, FamilyTree.getFamilyTree().size());
    }

    @Test
    public void removeFromFamilyTree() {
        FamilyTree.removeFromFamilyTree(1);
        Assert.assertEquals(0, FamilyTree.getFamilyTree().size());
    }
}