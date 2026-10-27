package com.qa.portfolio.factory;

import com.github.javafaker.Faker;
import com.qa.portfolio.model.SubjectDto;

public class SubjectDataFactory {

    private static final Faker faker = new Faker();

    /**
     * Generál egy teljesen dinamikus, nem-determinisztikus SubjectDto objektumot.
     */
    public static SubjectDto createRandomSubject() {
        String randomName = "Subject-" + faker.name().firstName() + "-" + faker.number().randomNumber(3, true);
        String randomStatus = faker.options().option("CONTAINED", "TERMINATED", "MUTATING");
        int randomDangerLevel = faker.number().numberBetween(1, 10);

        return new SubjectDto(randomName, randomStatus, randomDangerLevel);
    }
}