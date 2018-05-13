package com.company.domain;

import com.company.util.Gender;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Person {
    private int id;
    private Gender gender;
    private String fName;
    private String lName;
    private String moniker;
    private int bYear;
    private int dYear;
    private int fatherID;
    private int motherID;
    private ArrayList<LinkedHashMap<Integer, Integer>> spouseArray;

    public Person() {
        this.id = -1;
        this.gender = null;
        this.fName = "";
        this.lName = "";
        this.moniker = "";
        this.bYear = 0;
        this.dYear = 0;
        this.fatherID = 0;
        this.motherID = 0;
        this.spouseArray = new ArrayList<LinkedHashMap<Integer, Integer>>();
    }

    public Person(final int id, final Gender gender, final String fName, final String lName, final String moniker,
                  final int bYear, final int dYear, final int fatherID, final int motherID,
                  final ArrayList<LinkedHashMap<Integer, Integer>> spouseArray) {
        this.id = id;
        this.gender = gender;
        this.fName = fName;
        this.lName = lName;
        this.moniker = moniker;
        this.bYear = bYear;
        this.dYear = dYear;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseArray = spouseArray;
    }

    public int getId() { return id; }
    public Gender getGender() { return gender; }
    public String getFName() { return fName; }
    public String getLName() { return lName; }
    public String getMoniker() { return moniker; }
    public int getBYear() { return bYear; }
    public int getDYear() { return dYear; }
    public int getFatherID() { return fatherID; }
    public int getMotherID() { return motherID; }
    public ArrayList<LinkedHashMap<Integer, Integer>> getSpouseArray() { return spouseArray; }

    public void setId(final int id) { this.id = id; }
    public void setGender(final Gender gender) { this.gender = gender; }
    public void setFName(final String fName) { this.fName = fName; }
    public void setLName(final String lName) { this.lName = lName; }
    public void setMoniker(final String moniker) { this.moniker = moniker; }
    public void setBYear(final int bYear) { this.bYear = bYear; }
    public void setDYear(final int dYear) { this.dYear = dYear; }
    public void setFatherID(final int fatherID) { this.fatherID = fatherID; }
    public void setMotherID(final int motherID) { this.motherID = motherID; }
    public void setSpouseArray(final ArrayList<LinkedHashMap<Integer, Integer>> spouseArray) { this.spouseArray = spouseArray; }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("id=").append(id);
        sb.append(", gender=").append(gender);
        sb.append(", fName='").append(fName).append('\'');
        sb.append(", lName='").append(lName).append('\'');
        sb.append(", moniker='").append(moniker).append('\'');
        sb.append(", bYear=").append(bYear);
        sb.append(", dYear=").append(dYear);
        sb.append(", fatherID=").append(fatherID);
        sb.append(", motherID=").append(motherID);
        sb.append(", spouseArray=").append(spouseArray);
        sb.append('}');
        return sb.toString();
    }
}
