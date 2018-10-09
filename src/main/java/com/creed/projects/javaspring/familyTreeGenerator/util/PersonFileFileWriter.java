package com.creed.projects.javaspring.familyTreeGenerator.util;

import com.creed.projects.javaspring.familyTreeGenerator.domain.Person;

import java.io.*;

public class PersonFileFileWriter {

    private static BufferedWriter bw = null;
    private static String fileName;

    private PersonFileFileWriter() { }

    public static void openConnection() {
        try {
            bw = new BufferedWriter(new FileWriter(new File(fileName), true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDataToFile(final Person person) {
        try {
            bw.write(person.toStringCSV());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void closeConnection() {
        if (bw != null) {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setFileName(final String fileName) { PersonFileFileWriter.fileName = fileName; }
}
