package Test;

import Model.Superpoder;
import Page.CadastroSuperpoderPage;
import Page.ListSuperpoderesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CadastroSuperpoderesTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    private final String PAGE_URL = "https://site-tc1.vercel.app/cadastro";
    private CadastroSuperpoderPage createPage;

    @BeforeEach
    void setup() {
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
        driver.get(PAGE_URL);

        createPage = new CadastroSuperpoderPage(driver);
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
            createPage.returnToHomePage();

            assertEquals("https://site-tc1.vercel.app/", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should path back to cadastro when clicking cadastrar link")
        void shouldPathToCadastro() {
            createPage.goToCadastro();

            assertEquals("https://site-tc1.vercel.app/cadastro", driver.getCurrentUrl());
        }
    }

    @Nested
    @DisplayName("Tests for creating superpoderes")
    class CreateSuperpoderes {

        @Test
        @DisplayName("Should return to home screen after creating a new superpoder")
        void shouldSuccessfullyReturnToHomeAfterCreatingASuperpower() {
            try {
                final SoftAssertions softly = new SoftAssertions();

                Superpoder superpoder = Superpoder.FromFaker();
                createPage.cadastroSuperpoderFromSuperpoder(superpoder);

                webDriverWait.until(ExpectedConditions.alertIsPresent());
                String alertMessage = driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();

                softly.assertThat(alertMessage).isEqualTo("Poder criado com sucesso!");
                softly.assertThat(driver.getCurrentUrl()).isEqualTo("https://site-tc1.vercel.app/");

                softly.assertAll();
            } catch (TimeoutException ignored) {
                Assertions.fail("Superpoder wasn't created");
            }
        }

        @Test
        @DisplayName("Should be able to successfully create a superpower from faker and check if it's been properly created")
        void shouldSuccessfullyCreateSuperpowerFromFakerAndCheck() {
            try {
                final SoftAssertions softly = new SoftAssertions();

                Superpoder superpoder = Superpoder.FromFaker();
                createPage.cadastroSuperpoderFromSuperpoder(superpoder);

                webDriverWait.until(ExpectedConditions.alertIsPresent());
                String alertMessage = driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();

                createPage.returnToHomePage();

                ListSuperpoderesPage listSuperpoderesPage = new ListSuperpoderesPage(driver);
                List<Superpoder> superpoderesList = listSuperpoderesPage.getSuperpoderes();

                softly.assertThat(alertMessage).isEqualTo("Poder criado com sucesso!");
                softly.assertThat(superpoderesList).contains(superpoder);

                softly.assertAll();
            } catch (TimeoutException ignored) {
                Assertions.fail("Superpoder wasn't created");
            }
        }

        @Test
        @DisplayName("Should not be able to create superpoder if nome input is blank")
        void shouldNotCreateSuperpoderIfNomeIsBlank() {
            try {
                Superpoder superpoder = Superpoder.FromFaker();
                superpoder.setNome("");

                createPage.cadastroSuperpoderFromSuperpoder(superpoder);

                assertNotEquals("", createPage.getNomeDoPoderValidationMessage());

            } catch (UnhandledAlertException ignored) {
                Assertions.fail("Superpoder was created (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to create superpoder if descrição input is blank")
        void shouldNotCreateSuperpoderIfDescricaoIsBlank() {
            try {
                Superpoder superpoder = Superpoder.FromFaker();
                superpoder.setDescricao("");

                createPage.cadastroSuperpoderFromSuperpoder(superpoder);

                assertNotEquals("", createPage.getDescricaoValidationMessage());

            } catch (UnhandledAlertException ignored) {
                Assertions.fail("Superpoder was created (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to create superpoder if efeitos colaterais input is blank")
        void shouldNotCreateSuperpoderIfEfeitosColateraisIsBlank() {
            try {
                Superpoder superpoder = Superpoder.FromFaker();
                superpoder.setEfeitosColaterais("");

                createPage.cadastroSuperpoderFromSuperpoder(superpoder);

                assertNotEquals("", createPage.getEfeitosColateraisValidationMessage());

            } catch (UnhandledAlertException ignored) {
                Assertions.fail("Superpoder was created (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to create superpoder if nome input is an empty space")
        void shouldNotCreateSuperpoderIfNomeIsEmptySpace() {
            try {
                Superpoder superpoder = Superpoder.FromFaker();
                superpoder.setNome(" ");

                createPage.cadastroSuperpoderFromSuperpoder(superpoder);

                assertNotEquals("", createPage.getNomeDoPoderValidationMessage());

            } catch (UnhandledAlertException ignored) {
                Assertions.fail("Superpoder was created (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to create superpoder if descrição input is an empty space")
        void shouldNotCreateSuperpoderIfDescricaoIsEmptySpace() {
            try {
                Superpoder superpoder = Superpoder.FromFaker();
                superpoder.setDescricao(" ");

                createPage.cadastroSuperpoderFromSuperpoder(superpoder);

                assertNotEquals("", createPage.getDescricaoValidationMessage());

            } catch (UnhandledAlertException ignored) {
                Assertions.fail("Superpoder was created (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to create superpoder if efeitos colaterais input is an empty space")
        void shouldNotCreateSuperpoderIfEfeitosColateraisIsEmptyPath() {
            try {
                Superpoder superpoder = Superpoder.FromFaker();
                superpoder.setEfeitosColaterais(" ");

                createPage.cadastroSuperpoderFromSuperpoder(superpoder);

                assertNotEquals("", createPage.getEfeitosColateraisValidationMessage());

            } catch (UnhandledAlertException ignored) {
                Assertions.fail("Superpoder was created (alert was triggered)");
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

                softly.assertThat(createPage.returnToHomePageLinkIsDisplayed()).isTrue();
                softly.assertThat(createPage.returnToHomePageLinkIsEnabled()).isTrue();

                softly.assertAll();
            }

            @Test
            @DisplayName("Should cadastro page link be visible and clickable even when screen is horizontally small")
            void shouldCadastroPageLinkBeClickableWhenScreenIsSmall() {
                SoftAssertions softly = new SoftAssertions();

                driver.manage().window().setSize(new Dimension(500, 600));

                softly.assertThat(createPage.goToCadastroPageLinkLinkIsDisplayed()).isTrue();
                softly.assertThat(createPage.goToCadastroPageLinkLinkIsEnabled()).isTrue();

                softly.assertAll();
            }
        }
    }
}
