# ☂️ Umbrella Corp - REST Assured QA Test Automation Framework

![Java Version](https://img.shields.io/badge/Java-17%2B-orange?style=for-the-badge&logo=openjdk)
![Build Status](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions%20Passing-brightgreen?style=for-the-badge&logo=githubactions)
![Framework](https://img.shields.io/badge/Framework-REST%20Assured%20%7C%20Cucumber%20BDD-blue?style=for-the-badge)
![Testing](https://img.shields.io/badge/Tests-30%2F30%20PASSED-success?style=for-the-badge)

Vállalati szintű, BDD-alapú REST API tesztautomatizálási keretrendszer **Java 17+**, **REST Assured**, **Cucumber**, **JUnit 5**, **JavaFaker** és **Jackson** alapon. A keretrendszer az "Umbrella Corporation" fiktív mainframe REST API-jának átfogó funkcionális, negatív, JSON séma-, SLA/teljesítmény- és adatvezérelt tesztelésére készült.

---

## 🏛️ Építőkockák és Architektúra (Key Features)

- **BDD Architecture (Cucumber & Gherkin):** Üzleti szempontból is érthető, leíró jellegű `.feature` fájlok újrahasznosítható step definition-ökkel.
- **Dynamic Data Factory (JavaFaker):** Automatikusan generált szintetikus tesztadatok (mutációs státuszok, bio-rekordok) a tesztfüggetlenség garantálásához.
- **Performance & SLA Validation:** Beépített válaszidő-ellenőrzés Hamcrest matcherekkel (pl. válaszidő szigorúan `< 2000 ms`).
- **Parallel Execution:** Többszálú tesztvégrehajtás `junit-platform.properties` konfigurációval a CI/CD futási idő optimalizálására.
- **Robust Error Handling & Security Scenarios:** Hiányzó fejlécek, érvénytelen elérési utak (404/500) és biztonsági handshake validáció.
- **CI/CD Integration:** Teljesen automatizált GitHub Actions workflow, ami minden Push és Pull Request esetén lefut.

---

## 📁 Projekt Struktúra (Maven Layout)

A tesztkeretrendszer a Maven konvenciókat követve a `src/test/java` strukturált csomagjaiba szerveződik:

```text
rest-assured-qa-portfolio/
├── .github/
│   └── workflows/
│       └── maven.yml               # GitHub Actions CI/CD Pipeline
├── src/
│   └── test/
│       ├── java/com/qa/portfolio/
│       │   ├── client/             # REST Assured kliens wrapper (apiClient)
│       │   ├── config/             # REST Assured globális konfigurációk (TestBase)
│       │   ├── factory/            # Dinamikus tesztadat-generáló (SubjectDataFactory)
│       │   ├── models/             # POJO objektumok (User, Subject)
│       │   ├── steps/              # Cucumber Step Definitions & Hooks
│       │   └── tests/              # JUnit 5 tesztosztályok és Cucumber Runner
│       └── resources/
│           ├── features/           # Cucumber BDD szcenáriók (.feature)
│           ├── schemas/            # JSON Schema validációs fájlok (.json)
│           └── junit-platform.properties # Párhuzamos futtatás beállításai
├── pom.xml                         # Maven függőségek és plugin-ok
└── README.md                       # Projekt dokumentáció