package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class PersonUtilTest {

    private Person maleChild;
    private Person maleMarriedNoKids;
    private Person maleMarriedWithKids;
    private Person femaleChild;
    private Person femaleMarriedNoKids;
    private Person femaleMarriedWithKids;

    @Before
    public void setUp() throws Exception {

        maleChild = new Person();
        maleMarriedNoKids = new Person();
        maleMarriedWithKids = new Person();
        femaleChild = new Person();
        femaleMarriedNoKids = new Person();
        femaleMarriedWithKids = new Person();

        maleChild.setId(1);
        maleChild.setGender(Gender.MALE);
        maleChild.setFName("Male");
        maleChild.setLName("Child");
        maleChild.setMoniker("");
        maleChild.setBYear(100);
        maleChild.setDYear(110);
        maleChild.setFatherID(0);
        maleChild.setMotherID(0);
        maleChild.setSpouseArray(new LinkedHashMap<Integer, Integer>());
        maleChild.setChildredArray(new ArrayList<Integer>());

        maleMarriedNoKids.setId(1);
        maleMarriedNoKids.setGender(Gender.MALE);
        maleMarriedNoKids.setFName("Male");
        maleMarriedNoKids.setLName("Married No Kids");
        maleMarriedNoKids.setMoniker("");
        maleMarriedNoKids.setBYear(100);
        maleMarriedNoKids.setDYear(150);
        maleMarriedNoKids.setFatherID(0);
        maleMarriedNoKids.setMotherID(0);
        maleMarriedNoKids.setSpouseArray(new LinkedHashMap<Integer, Integer>());
        maleMarriedNoKids.setChildredArray(new ArrayList<Integer>());

        maleMarriedWithKids.setId(1);
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

        femaleChild.setId(1);
        femaleChild.setGender(Gender.FEMALE);
        femaleChild.setFName("Female");
        femaleChild.setLName("Child");
        femaleChild.setMoniker("");
        femaleChild.setBYear(100);
        femaleChild.setDYear(110);
        femaleChild.setFatherID(0);
        femaleChild.setMotherID(0);
        femaleChild.setSpouseArray(new LinkedHashMap<Integer, Integer>());
        femaleChild.setChildredArray(new ArrayList<Integer>());

        femaleMarriedNoKids.setId(1);
        femaleMarriedNoKids.setGender(Gender.FEMALE);
        femaleMarriedNoKids.setFName("Female");
        femaleMarriedNoKids.setLName("Married No Kids");
        femaleMarriedNoKids.setMoniker("");
        femaleMarriedNoKids.setBYear(100);
        femaleMarriedNoKids.setDYear(150);
        femaleMarriedNoKids.setFatherID(0);
        femaleMarriedNoKids.setMotherID(0);
        femaleMarriedNoKids.setSpouseArray(new LinkedHashMap<Integer, Integer>());
        femaleMarriedNoKids.setChildredArray(new ArrayList<Integer>());

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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void executeAllPersonUtilTests() {
        executeAllCreateSpouseTests();
        executeAllCreateChildrenTests();
        executeAllGenerateBirthYearTests();
    }

    @Test
    public void executeAllCreateSpouseTests() {
        for (int i = 0; i < 100; i++) {
            createFemaleSpouseMarriedAtTwenty();
            createFemaleSpouseMarriedAtThirty();
            createFemaleSpouseMarriedAtFourty();
            createFemaleSpouseMarriedAtFifty();
            createMaleSpouseMarriedAtTwenty();
            createMaleSpouseMarriedAtThirty();
            createMaleSpouseMarriedAtFourty();
            createMaleSpouseMarriedAtFifty();
        }
    }

    @Test
    public void createFemaleSpouseMarriedAtTwenty() {
        final int marriedYear = 120;

        final Person createdSpouse = PersonUtil.createSpouse(femaleMarriedNoKids, marriedYear);

        final int birthYear = createdSpouse.getBYear();
        final int deathYear = createdSpouse.getDYear();
        final int spouseCount = createdSpouse.getSpouseArray().size();

        assertTrue(Integer.class.isInstance(createdSpouse.getId()));
        assertEquals("", createdSpouse.getFName());
        assertEquals("", createdSpouse.getLName());
        assertEquals("", createdSpouse.getMoniker());
        assertEquals(Gender.MALE, createdSpouse.getGender());
        assertTrue(84 <= birthYear && birthYear <= 107);
        assertTrue(120 <= deathYear && deathYear <= 188);
        assertEquals(0, createdSpouse.getFatherID());
        assertEquals(0, createdSpouse.getMotherID());
        assertTrue(spouseCount >= 1);
        assertEquals(0, createdSpouse.getChildredArray().size());
    }

    @Test
    public void createFemaleSpouseMarriedAtThirty() {
        final int marriedYear = 130;

        final Person createdSpouse = PersonUtil.createSpouse(femaleMarriedNoKids, marriedYear);

        final int birthYear = createdSpouse.getBYear();
        final int deathYear = createdSpouse.getDYear();
        final int spouseCount = createdSpouse.getSpouseArray().size();

        assertTrue(Integer.class.isInstance(createdSpouse.getId()));
        assertEquals("", createdSpouse.getFName());
        assertEquals("", createdSpouse.getLName());
        assertEquals("", createdSpouse.getMoniker());
        assertEquals(Gender.MALE, createdSpouse.getGender());
        assertTrue(94 <= birthYear && birthYear <= 117);
        assertTrue(130 <= deathYear && deathYear <= 198);
        assertEquals(0, createdSpouse.getFatherID());
        assertEquals(0, createdSpouse.getMotherID());
        assertTrue(spouseCount >= 1);
        assertEquals(0, createdSpouse.getChildredArray().size());
    }

    @Test
    public void createFemaleSpouseMarriedAtFourty() {
        final int marriedYear = 140;

        final Person createdSpouse = PersonUtil.createSpouse(femaleMarriedNoKids, marriedYear);

        final int birthYear = createdSpouse.getBYear();
        final int deathYear = createdSpouse.getDYear();
        final int spouseCount = createdSpouse.getSpouseArray().size();

        assertTrue(Integer.class.isInstance(createdSpouse.getId()));
        assertEquals("", createdSpouse.getFName());
        assertEquals("", createdSpouse.getLName());
        assertEquals("", createdSpouse.getMoniker());
        assertEquals(Gender.MALE, createdSpouse.getGender());
        assertTrue(104 <= birthYear && birthYear <= 127);
        assertTrue(140 <= deathYear && deathYear <= 208);
        assertEquals(0, createdSpouse.getFatherID());
        assertEquals(0, createdSpouse.getMotherID());
        assertTrue(spouseCount >= 1);
        assertEquals(0, createdSpouse.getChildredArray().size());
    }

    @Test
    public void createFemaleSpouseMarriedAtFifty() {
        final int marriedYear = 150;

        final Person createdSpouse = PersonUtil.createSpouse(femaleMarriedNoKids, marriedYear);

        final int birthYear = createdSpouse.getBYear();
        final int deathYear = createdSpouse.getDYear();
        final int spouseCount = createdSpouse.getSpouseArray().size();

        assertTrue(Integer.class.isInstance(createdSpouse.getId()));
        assertEquals("", createdSpouse.getFName());
        assertEquals("", createdSpouse.getLName());
        assertEquals("", createdSpouse.getMoniker());
        assertEquals(Gender.MALE, createdSpouse.getGender());
        assertTrue(114 <= birthYear && birthYear <= 137);
        assertTrue(150 <= deathYear && deathYear <= 218);
        assertEquals(0, createdSpouse.getFatherID());
        assertEquals(0, createdSpouse.getMotherID());
        assertTrue(spouseCount >= 1);
        assertEquals(0, createdSpouse.getChildredArray().size());
    }

    @Test
    public void createMaleSpouseMarriedAtTwenty() {
        final int marriedYear = 120;

        final Person createdSpouse = PersonUtil.createSpouse(maleMarriedNoKids, marriedYear);

        final int birthYear = createdSpouse.getBYear();
        final int deathYear = createdSpouse.getDYear();
        final int spouseCount = createdSpouse.getSpouseArray().size();

        assertTrue(Integer.class.isInstance(createdSpouse.getId()));
        assertEquals("", createdSpouse.getFName());
        assertEquals("", createdSpouse.getLName());
        assertEquals("", createdSpouse.getMoniker());
        assertEquals(Gender.FEMALE, createdSpouse.getGender());
        assertTrue(99 <= birthYear && birthYear <= 107);
        assertTrue(120 <= deathYear && deathYear <= 188);
        assertEquals(0, createdSpouse.getFatherID());
        assertEquals(0, createdSpouse.getMotherID());
        assertTrue(spouseCount >= 1);
        assertEquals(0, createdSpouse.getChildredArray().size());
    }

    @Test
    public void createMaleSpouseMarriedAtThirty() {
        final int marriedYear = 130;

        final Person createdSpouse = PersonUtil.createSpouse(maleMarriedNoKids, marriedYear);

        final int birthYear = createdSpouse.getBYear();
        final int deathYear = createdSpouse.getDYear();
        final int spouseCount = createdSpouse.getSpouseArray().size();

        assertTrue(Integer.class.isInstance(createdSpouse.getId()));
        assertEquals("", createdSpouse.getFName());
        assertEquals("", createdSpouse.getLName());
        assertEquals("", createdSpouse.getMoniker());
        assertEquals(Gender.FEMALE, createdSpouse.getGender());
        assertTrue(109 <= birthYear && birthYear <= 117);
        assertTrue(130 <= deathYear && deathYear <= 198);
        assertEquals(0, createdSpouse.getFatherID());
        assertEquals(0, createdSpouse.getMotherID());
        assertTrue(spouseCount >= 1);
        assertEquals(0, createdSpouse.getChildredArray().size());
    }

    @Test
    public void createMaleSpouseMarriedAtFourty() {
        final int marriedYear = 140;

        final Person createdSpouse = PersonUtil.createSpouse(maleMarriedNoKids, marriedYear);

        final int birthYear = createdSpouse.getBYear();
        final int deathYear = createdSpouse.getDYear();
        final int spouseCount = createdSpouse.getSpouseArray().size();

        assertTrue(Integer.class.isInstance(createdSpouse.getId()));
        assertEquals("", createdSpouse.getFName());
        assertEquals("", createdSpouse.getLName());
        assertEquals("", createdSpouse.getMoniker());
        assertEquals(Gender.FEMALE, createdSpouse.getGender());
        assertTrue(119 <= birthYear && birthYear <= 127);
        assertTrue(140 <= deathYear && deathYear <= 208);
        assertEquals(0, createdSpouse.getFatherID());
        assertEquals(0, createdSpouse.getMotherID());
        assertTrue(spouseCount >= 1);
        assertEquals(0, createdSpouse.getChildredArray().size());
    }

    @Test
    public void createMaleSpouseMarriedAtFifty() {
        final int marriedYear = 150;

        final Person createdSpouse = PersonUtil.createSpouse(maleMarriedNoKids, marriedYear);

        final int birthYear = createdSpouse.getBYear();
        final int deathYear = createdSpouse.getDYear();
        final int spouseCount = createdSpouse.getSpouseArray().size();

        assertTrue(Integer.class.isInstance(createdSpouse.getId()));
        assertEquals("", createdSpouse.getFName());
        assertEquals("", createdSpouse.getLName());
        assertEquals("", createdSpouse.getMoniker());
        assertEquals(Gender.FEMALE, createdSpouse.getGender());
        assertTrue(129 <= birthYear && birthYear <= 137);
        assertTrue(150 <= deathYear && deathYear <= 218);
        assertEquals(0, createdSpouse.getFatherID());
        assertEquals(0, createdSpouse.getMotherID());
        assertTrue(spouseCount >= 1);
        assertEquals(0, createdSpouse.getChildredArray().size());
    }

    @Test
    public void executeAllCreateChildrenTests() {
        for (int i = 0; i < 100; i++) {
            createFemaleMarriedAtTwenty();
            createFemaleMarriedAtThirty();
            createFemaleMarriedAtFourty();
            createFemaleMarriedAtFifty();
            createMaleMarriedAtTwenty();
            createMaleMarriedAtThirty();
            createMaleMarriedAtFourty();
            createMaleMarriedAtFifty();
        }
    }

    @Test
    public void createFemaleMarriedAtTwenty() {
        final int marriedYear = 120;
        final int birthYear = 121;

        final Person createdSpouse = PersonUtil.createSpouse(femaleMarriedWithKids, marriedYear);

        final Person createChild = PersonUtil.createChild(createdSpouse.getId(), femaleMarriedWithKids.getId(), birthYear);

        final int deathYear = createChild.getDYear();

        assertTrue(Integer.class.isInstance(createChild.getId()));
        assertEquals("", createChild.getFName());
        assertEquals("", createChild.getLName());
        assertEquals("", createChild.getMoniker());
        //Doesn't matter if it's Male or Female
        assertEquals(birthYear, createChild.getBYear());
        assertTrue(birthYear <= deathYear && deathYear <= birthYear + 80);
        assertEquals(createdSpouse.getId(), createChild.getFatherID());
        assertEquals(femaleMarriedWithKids.getId(), createChild.getMotherID());
        assertEquals(0, createChild.getSpouseArray().size());
        assertEquals(0, createChild.getChildredArray().size());
    }

    @Test
    public void createFemaleMarriedAtThirty() {
        final int marriedYear = 130;
        final int birthYear = 131;

        final Person createdSpouse = PersonUtil.createSpouse(femaleMarriedWithKids, marriedYear);

        final Person createChild = PersonUtil.createChild(createdSpouse.getId(), femaleMarriedWithKids.getId(), birthYear);

        final int deathYear = createChild.getDYear();

        assertTrue(Integer.class.isInstance(createChild.getId()));
        assertEquals("", createChild.getFName());
        assertEquals("", createChild.getLName());
        assertEquals("", createChild.getMoniker());
        //Doesn't matter if it's Male or Female
        assertEquals(birthYear, createChild.getBYear());
        assertTrue(birthYear <= deathYear && deathYear <= birthYear + 80);
        assertEquals(createdSpouse.getId(), createChild.getFatherID());
        assertEquals(femaleMarriedWithKids.getId(), createChild.getMotherID());
        assertEquals(0, createChild.getSpouseArray().size());
        assertEquals(0, createChild.getChildredArray().size());
    }

    @Test
    public void createFemaleMarriedAtFourty() {
        final int marriedYear = 140;
        final int birthYear = 141;

        final Person createdSpouse = PersonUtil.createSpouse(femaleMarriedWithKids, marriedYear);

        final Person createChild = PersonUtil.createChild(createdSpouse.getId(), femaleMarriedWithKids.getId(), birthYear);

        final int deathYear = createChild.getDYear();

        assertTrue(Integer.class.isInstance(createChild.getId()));
        assertEquals("", createChild.getFName());
        assertEquals("", createChild.getLName());
        assertEquals("", createChild.getMoniker());
        //Doesn't matter if it's Male or Female
        assertEquals(birthYear, createChild.getBYear());
        assertTrue(birthYear <= deathYear && deathYear <= birthYear + 80);
        assertEquals(createdSpouse.getId(), createChild.getFatherID());
        assertEquals(femaleMarriedWithKids.getId(), createChild.getMotherID());
        assertEquals(0, createChild.getSpouseArray().size());
        assertEquals(0, createChild.getChildredArray().size());
    }

    @Test
    public void createFemaleMarriedAtFifty() {
        final int marriedYear = 150;
        final int birthYear = 151;

        final Person createdSpouse = PersonUtil.createSpouse(femaleMarriedWithKids, marriedYear);

        final Person createChild = PersonUtil.createChild(createdSpouse.getId(), femaleMarriedWithKids.getId(), birthYear);

        final int deathYear = createChild.getDYear();

        assertTrue(Integer.class.isInstance(createChild.getId()));
        assertEquals("", createChild.getFName());
        assertEquals("", createChild.getLName());
        assertEquals("", createChild.getMoniker());
        //Doesn't matter if it's Male or Female
        assertEquals(birthYear, createChild.getBYear());
        assertTrue(birthYear <= deathYear && deathYear <= birthYear + 80);
        assertEquals(createdSpouse.getId(), createChild.getFatherID());
        assertEquals(femaleMarriedWithKids.getId(), createChild.getMotherID());
        assertEquals(0, createChild.getSpouseArray().size());
        assertEquals(0, createChild.getChildredArray().size());
    }

    @Test
    public void createMaleMarriedAtTwenty() {
        final int marriedYear = 120;
        final int birthYear = 121;

        final Person createdSpouse = PersonUtil.createSpouse(maleMarriedWithKids, marriedYear);

        final Person createChild = PersonUtil.createChild(createdSpouse.getId(), maleMarriedWithKids.getId(), birthYear);

        final int deathYear = createChild.getDYear();

        assertTrue(Integer.class.isInstance(createChild.getId()));
        assertEquals("", createChild.getFName());
        assertEquals("", createChild.getLName());
        assertEquals("", createChild.getMoniker());
        //Doesn't matter if it's Male or Female
        assertEquals(birthYear, createChild.getBYear());
        assertTrue(birthYear <= deathYear && deathYear <= birthYear + 80);
        assertEquals(maleMarriedWithKids.getId(), createChild.getMotherID());
        assertEquals(createdSpouse.getId(), createChild.getFatherID());
        assertEquals(0, createChild.getSpouseArray().size());
        assertEquals(0, createChild.getChildredArray().size());
    }

    @Test
    public void createMaleMarriedAtThirty() {
        final int marriedYear = 130;
        final int birthYear = 131;

        final Person createdSpouse = PersonUtil.createSpouse(maleMarriedWithKids, marriedYear);

        final Person createChild = PersonUtil.createChild(createdSpouse.getId(), maleMarriedWithKids.getId(), birthYear);

        final int deathYear = createChild.getDYear();

        assertTrue(Integer.class.isInstance(createChild.getId()));
        assertEquals("", createChild.getFName());
        assertEquals("", createChild.getLName());
        assertEquals("", createChild.getMoniker());
        //Doesn't matter if it's Male or Female
        assertEquals(birthYear, createChild.getBYear());
        assertTrue(birthYear <= deathYear && deathYear <= birthYear + 80);
        assertEquals(maleMarriedWithKids.getId(), createChild.getMotherID());
        assertEquals(createdSpouse.getId(), createChild.getFatherID());
        assertEquals(0, createChild.getSpouseArray().size());
        assertEquals(0, createChild.getChildredArray().size());
    }

    @Test
    public void createMaleMarriedAtFourty() {
        final int marriedYear = 140;
        final int birthYear = 141;

        final Person createdSpouse = PersonUtil.createSpouse(maleMarriedWithKids, marriedYear);

        final Person createChild = PersonUtil.createChild(createdSpouse.getId(), maleMarriedWithKids.getId(), birthYear);

        final int deathYear = createChild.getDYear();

        assertTrue(Integer.class.isInstance(createChild.getId()));
        assertEquals("", createChild.getFName());
        assertEquals("", createChild.getLName());
        assertEquals("", createChild.getMoniker());
        //Doesn't matter if it's Male or Female
        assertEquals(birthYear, createChild.getBYear());
        assertTrue(birthYear <= deathYear && deathYear <= birthYear + 80);
        assertEquals(maleMarriedWithKids.getId(), createChild.getMotherID());
        assertEquals(createdSpouse.getId(), createChild.getFatherID());
        assertEquals(0, createChild.getSpouseArray().size());
        assertEquals(0, createChild.getChildredArray().size());
    }

    @Test
    public void createMaleMarriedAtFifty() {
        final int marriedYear = 150;
        final int birthYear = 151;

        final Person createdSpouse = PersonUtil.createSpouse(maleMarriedWithKids, marriedYear);

        final Person createChild = PersonUtil.createChild(createdSpouse.getId(), maleMarriedWithKids.getId(), birthYear);

        final int deathYear = createChild.getDYear();

        assertTrue(Integer.class.isInstance(createChild.getId()));
        assertEquals("", createChild.getFName());
        assertEquals("", createChild.getLName());
        assertEquals("", createChild.getMoniker());
        //Doesn't matter if it's Male or Female
        assertEquals(birthYear, createChild.getBYear());
        assertTrue(birthYear <= deathYear && deathYear <= birthYear + 80);
        assertEquals(maleMarriedWithKids.getId(), createChild.getMotherID());
        assertEquals(createdSpouse.getId(), createChild.getFatherID());
        assertEquals(0, createChild.getSpouseArray().size());
        assertEquals(0, createChild.getChildredArray().size());
    }

    /**
     * Loops all tests 100 times to ensure all premutations are met.
     */
    @Test
    public void executeAllGenerateBirthYearTests() {
        for (int i = 0; i < 100; i++) {
            generateBirthYearFemaleMarriedAtTwenty();
            generateBirthYearFemaleMarriedAtThirty();
            generateBirthYearFemaleMarriedAtFourty();
            generateBirthYearFemaleMarriedAtFifty();
            generateBirthYearMaleMarriedAtTwenty();
            generateBirthYearMaleMarriedAtThirty();
            generateBirthYearMaleMarriedAtFourty();
            generateBirthYearMaleMarriedAtFifty();
        }
    }

    @Test
    public void generateBirthYearFemaleMarriedAtTwenty() {
        final int marriedYear = 120;
        final Gender gender = Gender.FEMALE;

        final Integer birthYear = PersonUtil.generateBirthYear(marriedYear, gender);

        assertTrue(99 <= birthYear && birthYear <= 107);
    }

    @Test
    public void generateBirthYearFemaleMarriedAtThirty() {
        final int marriedYear = 130;
        final Gender gender = Gender.FEMALE;

        final Integer birthYear = PersonUtil.generateBirthYear(marriedYear, gender);

        assertTrue(109 <= birthYear && birthYear <= 117);
    }

    @Test
    public void generateBirthYearFemaleMarriedAtFourty() {
        final int marriedYear = 140;
        final Gender gender = Gender.FEMALE;

        final Integer birthYear = PersonUtil.generateBirthYear(marriedYear, gender);

        assertTrue(119 <= birthYear && birthYear <= 127);
    }

    @Test
    public void generateBirthYearFemaleMarriedAtFifty() {
        final int marriedYear = 150;
        final Gender gender = Gender.FEMALE;

        final Integer birthYear = PersonUtil.generateBirthYear(marriedYear, gender);

        assertTrue(129 <= birthYear && birthYear <= 137);
    }

    @Test
    public void generateBirthYearMaleMarriedAtTwenty() {
        final int marriedYear = 120;
        final Gender gender = Gender.MALE;

        final Integer birthYear = PersonUtil.generateBirthYear(marriedYear, gender);

        assertTrue(84 <= birthYear && birthYear <= 107);
    }

    @Test
    public void generateBirthYearMaleMarriedAtThirty() {
        final int marriedYear = 130;
        final Gender gender = Gender.MALE;

        final Integer birthYear = PersonUtil.generateBirthYear(marriedYear, gender);

        assertTrue(94 <= birthYear && birthYear <= 117);
    }

    @Test
    public void generateBirthYearMaleMarriedAtFourty() {
        final int marriedYear = 140;
        final Gender gender = Gender.MALE;

        final Integer birthYear = PersonUtil.generateBirthYear(marriedYear, gender);

        assertTrue(104 <= birthYear && birthYear <= 127);
    }

    @Test
    public void generateBirthYearMaleMarriedAtFifty() {
        final int marriedYear = 150;
        final Gender gender = Gender.MALE;

        final Integer birthYear = PersonUtil.generateBirthYear(marriedYear, gender);

        assertTrue(114 <= birthYear && birthYear <= 137);
    }

    @Test
    public void generateDeathAge() {
    }

    @Test
    public void useMarriedDataToGetDeathYear() {
    }

    @Test
    public void rollDice() {
    }

    @Test
    public void generateRandomID() {
    }

    @Test
    public void getOppositeGender() {
    }

    @Test
    public void calculateYearsTillReMarriage() {
        final int result = PersonUtil.calculateYearsTillReMarriage(0);

        assertTrue(1 <= result && result <= 4);
    }

    @Test
    public void getFertilityPercentage() {
    }

    @Test
    public void getRandomGender() {
    }
}