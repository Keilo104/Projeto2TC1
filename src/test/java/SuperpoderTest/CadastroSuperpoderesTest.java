package SuperpoderTest;

import Model.Superpoder;
import Pages.CadastroSuperpoderPage;
import Pages.ListSuperpoderesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CadastroSuperpoderesTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    private final String PAGE_URL = "https://site-tc1.vercel.app/cadastro";
    private CadastroSuperpoderPage page;

    @BeforeEach
    void setup() {
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
        driver.get(PAGE_URL);

        page = new CadastroSuperpoderPage(driver);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void destroy() {
        driver.quit();
    }

    @Nested
    @DisplayName("Tests for pathing to other places")
    class Pathing {

        @Test
        @DisplayName("Should path to home when clicking home link")
        void shouldPathToHome() {
            page.returnToHomePage();

            assertEquals("https://site-tc1.vercel.app/", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should path back to cadastro when clicking cadastrar link")
        void shouldPathToCadastro() {
            page.goToCadastro();

            assertEquals("https://site-tc1.vercel.app/cadastro", driver.getCurrentUrl());
        }
    }

    @Nested
    @DisplayName("Tests for creating superpoderes")
    class CreateSuperpoderes {
        @Test
        @DisplayName("Should be able to successfully create a superpower from faker and check if it's been properly created")
        void shouldSuccessfullyCreateSuperpowerFromFakerAndCheck() {
            final SoftAssertions softly = new SoftAssertions();

            Superpoder superpoder = new Superpoder();

            superpoder.setNome("Poder inútil 1");
            superpoder.setDescricao("Faz coisas inúteis");
            superpoder.setEfeitosColaterais("Péssimos efeitos");
            superpoder.setNota("3");

            page.createSuperpoderFromSuperpoder(superpoder);

            webDriverWait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            page.returnToHomePage();

            ListSuperpoderesPage listSuperpoderesPage = new ListSuperpoderesPage(driver);
            List<Superpoder> superpoderesList = listSuperpoderesPage.getSuperpoderes();

            softly.assertThat(alertMessage).isEqualTo("Poder criado com sucesso!");
            softly.assertThat(superpoderesList).contains(superpoder);

            softly.assertAll();
        }
    }
}
