package com.qa.portfolio.factory;

import com.github.javafaker.Faker;
import com.qa.portfolio.model.SubjectDto;
import java.util.Locale;

public class TestDataFactory {

    private static final Faker faker = new Faker(new Locale("en-US"));

    /**
     * Dinamikus Subject POJO generálása egyedi névvel, státusszal és veszélyességi szinttel
     */
    public static SubjectDto createRandomSubject() {
        String uniqueName = "SUBJECT_" + faker.educator().course().replaceAll("[^a-zA-Z0-9]", "") + "_" + faker.number().digits(4);
        String randomStatus = faker.options().option("ACTIVE", "CONTAINED", "TERMINATED");
        int randomDangerLevel = faker.number().numberBetween(1, 10);

        return new SubjectDto(uniqueName, randomStatus, randomDangerLevel);
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }
}