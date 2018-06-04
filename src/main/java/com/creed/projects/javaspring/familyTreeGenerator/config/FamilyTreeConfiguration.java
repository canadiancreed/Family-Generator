package com.creed.projects.javaspring.familyTreeGenerator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * This class handles all configurable settings for family tree generation. Can be changed in application.properties
 */

@Configuration
public class FamilyTreeConfiguration {

    private Integer chanceIsBachelor;

    @Autowired
    FamilyTreeConfiguration(@Value("${initialize.familyTree.chanceIsBachelor}") Integer chanceIsBachelor) {
                                this.chanceIsBachelor = chanceIsBachelor;
    }

    public Integer getChanceIsBachelor() { return chanceIsBachelor; }
}
