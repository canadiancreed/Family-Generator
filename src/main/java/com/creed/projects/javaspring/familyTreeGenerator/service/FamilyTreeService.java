package com.creed.projects.javaspring.familyTreeGenerator.service;

import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;

import java.util.LinkedHashMap;

public class FamilyTreeService {

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