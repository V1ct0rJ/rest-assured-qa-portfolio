package com.qa.portfolio.tests; // Megadja a csomagot, amiben az osztály él (a com/qa/portfolio/tests mappát jelöli)[cite: 1]

import com.deque.html.axecore.results.Results; // Importálja az Axe-core eredményeket tároló osztályt
import com.deque.html.axecore.results.Rule; // Importálja az egyedi hiba/szabály (Rule) típust
import com.deque.html.axecore.selenium.AxeBuilder; // Importálja az AxeBuilder eszközt, ami összeköti az A11y motort a Seleniummal
import org.junit.jupiter.api.AfterEach; // JUnit 5 annotáció: minden egyes teszt után lefutó metódushoz
import org.junit.jupiter.api.BeforeEach; // JUnit 5 annotáció: minden egyes teszt előtt lefutó metódushoz
import org.junit.jupiter.api.Test; // JUnit 5 annotáció: ez jelöli a futtatható tesztesetet
import org.openqa.selenium.WebDriver; // A Selenium WebDriver interfésze a böngésző vezérléséhez
import org.openqa.selenium.chrome.ChromeDriver; // Konkrét Chrome böngésző driver példányosító osztály
import org.openqa.selenium.chrome.ChromeOptions; // A Chrome indítási beállításait (pl. headless) kezelő osztály

import java.time.Duration; // Időtartamok megadására szolgáló Java osztály (pl. várakozáshoz)
import java.util.List; // Java lista gyűjtemény a hibák listázásához

import static org.junit.jupiter.api.Assertions.assertEquals; // Asszertció (feltételvizsgálat) az elvárt és kapott eredmény összehasonlítására

public class AccessibilityTest { // Deklarálja az akadálymentességi teszt osztályt

    private WebDriver driver; // Létrehoz egy privát WebDriver változót a böngésző vezérléséhez

    @BeforeEach // Ez a metódus lefut minden egyes @Test metódus *előtt* (környezet előkészítése)
    public void setUp() {
        ChromeOptions options = new ChromeOptions(); // Létrehozza a Chrome beállítások objektumát
        options.addArguments("--headless"); // Beállítja a háttérben futtatást (nincs grafikus ablak, fontos CI/CD-hez)
        options.addArguments("--no-sandbox"); // Kikapcsolja a homokozót (Linux / Docker kompatibilitáshoz szükséges)
        options.addArguments("--disable-dev-shm-usage"); // Megakadályozza a memóriatúlcsordulási hibákat Linux környezetben

        driver = new ChromeDriver(options); // Példányosítja a Chrome dribert a megadott beállításokkal
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Beállít 10 mp várakozási időt az elemek betöltésére
    }

    @Test // Jelöli, hogy ez egy önálló, futtatható egységteszt
    public void testWebsiteAccessibilityWithAxe() {
        driver.get("https://jsonplaceholder.typicode.com/"); // 1. Megnyitja a tesztelendő weboldalt a böngészőben

        AxeBuilder axeBuilder = new AxeBuilder(); // 2. Létrehozza az akadálymentességi elemző építőjét
        Results results = axeBuilder.analyze(driver); // Lefuttatja az Axe vizsgálatot a Selenium driveren keresztül, az eredmény a Results-ba kerül

        List<Rule> violations = results.getViolations(); // 3. Kigyűjti a Results objektumból az összes megszegett szabályt (hibát) egy listába

        System.out.println("=== A11y (Akadálymentességi) Teszt Riport ==="); // Kiírja a konzolra a riport fejlécét
        System.out.println("Talált hibák száma: " + violations.size()); // Kiírja a hibák darabszámát

        for (Rule violation : violations) { // Végigmegy ciklusban az összes talált hibán
            System.out.println("- Hiba ID: " + violation.getId()); // Kiírja a hiba egyedi azonosítóját (pl. kontraszt hiba)
            System.out.println("  Leírás: " + violation.getDescription()); // Kiírja a hiba emberi nyelvű leírását
            System.out.println("  Hatás (Impact): " + violation.getImpact()); // Kiírja a hiba súlyosságát (minor, serious, stb.)
        }

        // Megszámoljuk, hány súlyos vagy kritikus hiba van
        long severeViolations = violations.stream()
                .filter(v -> "critical".equalsIgnoreCase(v.getImpact()))
                .count();

        System.out.println("Kritikus akadálymentességi hibák száma: " + severeViolations);

        // Csak a kritikus  hibákra adjuk el a buildet
        assertEquals(0, severeViolations, "Az oldalon kritikus akadálymentességi hibák találhatók!");
    }

    @AfterEach // Ez a metódus lefut minden egyes @Test metódus *után* (takarítás)
    public void tearDown() {
        if (driver != null) { // Ha a driver létezik és elindult...
            driver.quit(); // Bezárja a böngészőt és felszabadítja az erőforrásokat a memóriából
        }
    }
}