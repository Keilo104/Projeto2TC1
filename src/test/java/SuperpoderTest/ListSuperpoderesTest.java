package SuperpoderTest;

import Pages.ListSuperpoderesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListSuperpoderesTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    private final String PAGE_URL = "https://site-tc1.vercel.app/";
    private ListSuperpoderesPage page;

    @BeforeEach
    void setup() {
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
        driver.get(PAGE_URL);

        page = new ListSuperpoderesPage(driver);
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
        @DisplayName("Should path back to home when clicking home link")
        void shouldPathToHome() {
            page.returnToHomePage();

            assertEquals("https://site-tc1.vercel.app/", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should path to cadastro when clicking cadastrar link")
        void shouldPathToCadastro() {
            page.goToCadastro();

            assertEquals("https://site-tc1.vercel.app/cadastro", driver.getCurrentUrl());
        }
    }
    @Nested
    @DisplayName("Tests for UI")
    class UI {

        @Test
        @DisplayName("Should home page link be visible and clickable even when screen is horizontally small")
        void shouldHomePageLinkBeClickableWhenScreenIsSmall() {
            SoftAssertions softly = new SoftAssertions();

            driver.manage().window().setSize(new Dimension(500, 600));

            softly.assertThat(page.returnToHomePageLinkIsDisplayed()).isTrue();
            softly.assertThat(page.returnToHomePageLinkIsEnabled()).isTrue();

            softly.assertAll();
        }

        @Test
        @DisplayName("Should cadastro page link be visible and clickable even when screen is horizontally small")
        void shouldCadastroPageLinkBeClickableWhenScreenIsSmall() {
            SoftAssertions softly = new SoftAssertions();

            driver.manage().window().setSize(new Dimension(500, 600));

            softly.assertThat(page.goToCadastroPageLinkLinkIsDisplayed()).isTrue();
            softly.assertThat(page.goToCadastroPageLinkLinkIsEnabled()).isTrue();

            softly.assertAll();
        }
    }
}
