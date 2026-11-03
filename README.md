# ☂️ Umbrella Corp - Enterprise REST Assured QA Test Automation Framework

![Java Version](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)
![Build Status](https://img.shields.io/badge/CI%2FCD-GitHub%2520Actions%2520Passing-brightgreen?style=for-the-badge&logo=githubactions)
![Framework](https://img.shields.io/badge/Framework-REST%20Assured%20%7C%20Cucumber%20BDD-blue?style=for-the-badge)
![Security & Performance](https://img.shields.io/badge/Testing-Security%20%7C%20A11y%20%7C%20JMeter%20%7C%20k6-success?style=for-the-badge)

Vállalati szintű, BDD-alapú REST API és minőségbiztosítási automatizálási keretrendszer **Java 17+**, **REST Assured**, **Cucumber**, **JUnit 5**, **OWASP ZAP**, **Axe-core**, **Apache JMeter** és **k6** alapon[cite: 1, 2, 3]. A keretrendszer az "Umbrella Corporation" fiktív mainframe rendszerének átfogó funkcionális, biztonsági, akadálymentességi és terheléses tesztelésére készült.

---

## 🏛️ Építőkockák és Architektúra (Key Features)

- **BDD Architecture (Cucumber & Gherkin):** Üzleti szempontból is érthető, leíró jellegű `.feature` fájlok újrahasznosítható step definition-ökkel[cite: 1] és `TestContext` állapotkezeléssel[cite: 2].
- **Dynamic Data Factory (JavaFaker / TestDataFactory):** Automatikusan generált szintetikus tesztadatok a tesztfüggetlenség garantálásához[cite: 1, 2].
- **Security & Vulnerability Scanning (OWASP ZAP):** Automatizált DAST alapú biztonsági ellenőrzések a pipeline-ban[cite: 1, 2].
- **Accessibility Testing (Axe-core):** WCAG 2.1 AA kompatibilitási és kontraszt-ellenőrzések[cite: 1, 2].
- **Performance & Load Testing (JMeter & k6):** Integrált `.jmx` teszttervek, `jmeter-maven-plugin`[cite: 1, 2], valamint modern JavaScript alapú `umbrella-load-test.js` (k6) teljesítményszkriptek[cite: 3].
- **CI/CD Integration & Reporting:** Teljesen automatizált GitHub Actions workflow (`qa-pipeline.yml`, `maven-tests.yml`), Allure HTML, Surefire és Cucumber riport generálással[cite: 1, 2, 3].

---

## 📁 Projekt Struktúra (Maven Layout)

A tesztkeretrendszer a Maven konvenciókat követve az alábbi csomagszerkezetbe szerveződik[cite: 1, 2, 3]:

```text
rest-assured-qa-portfolio/
├── .github/
│   └── workflows/
│       ├── maven-tests.yml         # Fő Maven CI/CD Pipeline[cite: 2]
│       └── qa-pipeline.yml         # Biztonsági és integrált teszt workflow[cite: 2]
├── allure-results/                 # Allure futtatási eredmények[cite: 2, 3]
├── jmeter/
│   └── load-test.jmx               # Apache JMeter terhelésteszt terv[cite: 2, 3]
├── target/                         # Build kimenetek, Surefire, Cucumber & Allure riportok[cite: 3]
├── src/
│   └── test/
│       ├── java/com/qa/portfolio/
│       │   ├── base/               # TestBase globális konfigurációk[cite: 2]
│       │   ├── client/             # UmbrellaApiClient wrapper[cite: 2]
│       │   ├── context/            # TestContext (állapotmegosztás)[cite: 2]
│       │   ├── factory/            # SubjectDataFactory & TestDataFactory[cite: 2]
│       │   ├── hooks/              # Cucumber Hooks[cite: 2]
│       │   ├── model/              # POJO objektumok (SubjectDto, User)[cite: 2]
│       │   ├── steps/              # GenericApiSteps & SubjectStepDefs[cite: 2]
│       │   └── tests/              # JUnit 5 futtatók (Auth, Schema, A11y, Security, JMeter)[cite: 2]
│       └── resources/
│           ├── features/           # BDD Gherkin fájlok (.feature)[cite: 2]
│           └── schemas/            # JSON Schema validációs fájlok (user-schema.json)[cite: 2]
├── umbrella-load-test.js           # k6 Teljesítményteszt szkript (JavaScript)[cite: 3]
├── pom.xml                         # Maven függőségek és plugin-ok[cite: 1, 2, 3]
└── README.md                       # Projekt dokumentáció[cite: 1, 2, 3]