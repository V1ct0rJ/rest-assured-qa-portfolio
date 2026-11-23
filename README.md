# ☂️ Umbrella Corp – Enterprise REST Assured QA Test Automation Framework

![Java Level](https://img.shields.io/badge/Java%20Level-17-orange?style=for-the-badge&logo=openjdk)
![CI/CD](https://img.shields.io/badge/CI%2FCD-Pipeline-blue?style=for-the-badge&logo=githubactions)
![Framework](https://img.shields.io/badge/Framework-REST%20Assured%20%7C%20Cucumber%20BDD-0A66C2?style=for-the-badge)
![Testing Scope](https://img.shields.io/badge/Scope-API%20%7C%20Security%20%7C%20A11y%20%7C%20JMeter%20%7C%20k6-success?style=for-the-badge)

Vállalati szintű, BDD-alapú REST API és minőségbiztosítási automatizálási keretrendszer **Java 17**, **REST Assured**, **Cucumber**, **JUnit 5**, **OWASP ZAP**, **Axe-core**, **Apache JMeter** és **k6** alapon. A keretrendszer az „Umbrella Corporation” fiktív mainframe rendszerének funkcionális, biztonsági, akadálymentességi és terheléses tesztelésére készült.

---

## 🏛️ Építőkockák és Architektúra (Key Features)

- **BDD Architecture (Cucumber & Gherkin):** Üzleti szempontból is érthető, leíró jellegű `.feature` fájlok újrahasznosítható step definition-ökkel és `TestContext` állapotkezeléssel.
- **Dynamic Data Factory (JavaFaker / TestDataFactory):** Automatikusan generált szintetikus tesztadatok a tesztfüggetlenség garantálásához.
- **Security & Vulnerability Scanning (OWASP ZAP):** Automatizált DAST alapú biztonsági ellenőrzések a pipeline-ban.
- **Accessibility Testing (Axe-core):** WCAG 2.1 AA kompatibilitási és kontraszt-ellenőrzések.
- **Performance & Load Testing (JMeter & k6):** Integrált `.jmx` teszttervek, `jmeter-maven-plugin`, valamint JavaScript alapú `umbrella-load-test.js` (k6) teljesítményszkriptek.
- **CI/CD Integration & Reporting:** Automatizált GitHub Actions workflow-k (`maven-tests.yml`, `qa-pipeline.yml`), Allure, Surefire és Cucumber riport generálással.

---

## 📁 Projekt Struktúra (Maven Layout)

A tesztkeretrendszer a Maven konvenciókat követve az alábbi csomagszerkezetbe szerveződik (a `target/` és `allure-results/` futtatás során keletkező kimeneti könyvtárak, amelyeket a `.gitignore` kizár):

```text
rest-assured-qa-portfolio/
├── .github/
│   └── workflows/
│       ├── maven-tests.yml         # Fő Maven CI/CD Pipeline
│       └── qa-pipeline.yml         # Biztonsági és integrált teszt workflow
├── allure-results/                 # Allure futtatási eredmények (futás után keletkezik)
├── jmeter/
│   └── load-test.jmx               # Apache JMeter terhelésteszt terv
├── target/                         # Build kimenetek, Surefire, Cucumber & Allure riportok (futás után keletkezik)
├── src/
│   └── test/
│       ├── java/com/qa/portfolio/
│       │   ├── base/               # TestBase globális konfigurációk
│       │   ├── client/             # UmbrellaApiClient wrapper
│       │   ├── context/            # TestContext (állapotmegosztás)
│       │   ├── factory/            # SubjectDataFactory & TestDataFactory
│       │   ├── hooks/              # Cucumber Hooks
│       │   ├── model/              # POJO objektumok (SubjectDto, User)
│       │   ├── steps/              # GenericApiSteps & SubjectStepDefs
│       │   └── tests/              # JUnit 5 futtatók (Auth, Schema, A11y, Security, JMeter)
│       └── resources/
│           ├── features/           # BDD Gherkin fájlok (.feature)
│           └── schemas/            # JSON Schema validációs fájlok (user-schema.json)
├── umbrella-load-test.js           # k6 Teljesítményteszt szkript (JavaScript)
├── pom.xml                         # Maven függőségek és plugin-ok
└── README.md                       # Projekt dokumentáció