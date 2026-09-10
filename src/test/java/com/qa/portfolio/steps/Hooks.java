package com.qa.portfolio.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;

public class Hooks {

    @Before
    public void setup(Scenario scenario) {
        System.out.println("==================================================");
        System.out.println("🚀 [BEFORE HOOK] Starting Scenario: " + scenario.getName());
        System.out.println("==================================================");

        // Globális RestAssured konfiguráció beállítása
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // 2. CÍMKÉZETT (TAGGED) BEFORE HOOK - Ez CSAK a @security taggel ellátott szcenáriók előtt fut le!
    @Before("@security")
    public void securitySetup() {
        System.out.println("🔐 [SECURITY HOOK] Initiating Umbrella Mainframe Security Handshake...");
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("==================================================");
        if (scenario.isFailed()) {
            System.err.println("❌ [AFTER HOOK] Scenario FAILED: " + scenario.getName());
            // Itt lehetne pl. képernyőképet menteni vagy API válasz logot kimenteni
        } else {
            System.out.println("✅ [AFTER HOOK] Scenario PASSED: " + scenario.getName());
        }
        System.out.println("==================================================\n");

        // Takarítás: Töröljük a globális beállításokat a következő teszt előtt
        RestAssured.reset();
    }
}