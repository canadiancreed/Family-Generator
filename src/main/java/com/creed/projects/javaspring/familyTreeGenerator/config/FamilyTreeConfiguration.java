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
    private Integer chanceIsInfertile;

    @Autowired
    FamilyTreeConfiguration(@Value("4") Integer chanceIsBachelor,
                            @Value("15") Integer chanceIsInfertile) {
                                this.chanceIsBachelor = chanceIsBachelor;
                                this.chanceIsInfertile = chanceIsInfertile;}

    public Integer getChanceIsBachelor() { return chanceIsBachelor; }
    public Integer getChanceIsInfertile() { return chanceIsInfertile; }
}
