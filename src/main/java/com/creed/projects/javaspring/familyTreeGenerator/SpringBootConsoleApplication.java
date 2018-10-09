package com.creed.projects.javaspring.familyTreeGenerator;

import com.creed.projects.javaspring.familyTreeGenerator.config.FamilyTreeConfiguration;
import com.creed.projects.javaspring.familyTreeGenerator.config.PersonZeroConfiguration;
import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;
import com.creed.projects.javaspring.familyTreeGenerator.util.FamilyTreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    private PersonZeroConfiguration pzc;

    @Autowired
    private FamilyTreeConfiguration ftc;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    /**
     * The run method will execute the application, running the buildFamilyTree method and returning the result.
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        Integer applicationStatusCode = 0;
        FamilyTreeBuilder ftb = new FamilyTreeBuilder(pzc, ftc);
//
//        //Check if death year is greater then current year
//        //If it is, take IDs for children and build the next generation
//        //Otherwise write to file and end program
//
//        for (Object value : ftb.returnCurrentFamilyTreeCollection().values()) {
//            System.out.println(value.toString());
//        }
//        for (Person value : ftb.returnCurrentFamilyTreeCollection().values()) {
//            System.out.println(value.toStringCSV());
//        }
//        for (Person value : ftb.returnCurrentFamilyTreeCollection().values()) {
//            System.out.println(value.toStringAgeChildrenArray());
//        }



        exit(applicationStatusCode);
    }
}