package com.company.domain;

import java.util.LinkedHashMap;

public class FamilyTree {

    private static LinkedHashMap<Integer, Person> familyTree = new LinkedHashMap<>();

    public static void addToFamilyTree(final Person person) {
        familyTree.put(person.getId(), person);
    }

    public static void removeFromFamilyTree(final int personID) {
        familyTree.remove(personID);
    }

    public static LinkedHashMap<Integer, Person> getFamilyTree() {
        return familyTree;
    }
}
