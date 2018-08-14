package com.creed.projects.javaspring.familyTreeGenerator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonZeroConfiguration {

    private final String firstName;
    private final String lastName;
    private final String moniker;
    private final String gender;
    private final Integer birthYear;
    private final Integer marriageYear;
    private final Integer deathYear;
    private final Integer currentYear;

    @Autowired
    PersonZeroConfiguration(@Value("${initialize.personZero.firstName}") String firstName,
                            @Value("${initialize.personZero.lastName}") String lastName,
                            @Value("${initialize.personZero.moniker}") String moniker,
                            @Value("${initialize.personZero.gender}") String gender,
                            @Value("${initialize.personZero.birthYear}") Integer birthYear,
                            @Value("${initialize.personZero.marriageYear}") Integer marriageYear,
                            @Value("${initialize.personZero.deathYear}") Integer deathYear,
                            @Value("${initialize.personZero.currentYear}") Integer currentYear) {
                                    this.firstName = firstName;
                                    this.lastName = lastName;
                                    this.moniker = moniker;
                                    this.gender = gender;
                                    this.birthYear = birthYear;
                                    this.marriageYear = marriageYear;
                                    this.deathYear = deathYear;
                                    this.currentYear = currentYear;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getMoniker() { return moniker; }
    public String getGender() { return gender; }
    public Integer getBirthYear() { return birthYear; }
    public Integer getMarriageYear() { return marriageYear; }
    public Integer getDeathYear() { return deathYear; }
    public Integer getCurrentYear() { return currentYear; }
}
