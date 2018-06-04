package com.creed.projects.javaspring.familyTreeGenerator.service;

import com.creed.projects.javaspring.familyTreeGenerator.common.Gender;
import org.springframework.stereotype.Service;

@Service
public class NameGeneratorService {

    private NameGeneratorService() { }

    public static String generateFirstName(final Gender gender) {
        return "";
    }

    public static String generalLastName(final Gender gender) {
        return "";
    }
}