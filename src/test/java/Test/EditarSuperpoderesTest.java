package Test;

import Page.CadastroSuperpoderPage;
import Page.EditarSuperpoderesPage;
import Page.ListSuperpoderesPage;
import Page.SuperpoderItemPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EditarSuperpoderesTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    private final String PAGE_URL = "https://site-tc1.vercel.app/";
    private ListSuperpoderesPage listPage;
    private EditarSuperpoderesPage editPage;

    @BeforeEach
    void setup() {
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
        driver.get(PAGE_URL);

        listPage = new ListSuperpoderesPage(driver);
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
            setupGoToEdit();
            editPage.returnToHomePage();

            assertEquals("https://site-tc1.vercel.app/", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should path to cadastro when clicking cadastrar link")
        void shouldPathToCadastro() {
            setupGoToEdit();
            editPage.goToCadastro();

            assertEquals("https://site-tc1.vercel.app/cadastro", driver.getCurrentUrl());
        }
    }

    @Nested
    @DisplayName("Tests for Editing Superpowers")
    class EditSuperpoderes {

        @Test
        @DisplayName("Should not be able to edit superpoder if nome input is blank")
        void shouldNotEditSuperpoderIfNomeIsBlank() {
            try {
                setupGoToEdit();

                editPage.clearNomeInput();

                assertNotEquals("", editPage.getNomeDoPoderValidationMessage());
            } catch (UnhandledAlertException ignored) {

                Assertions.fail("Superpoder was edited (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to edit superpoder if descricao input is blank")
        void shouldNotEditSuperpoderIfDescricaoIsBlank() {
            try {
                setupGoToEdit();

                editPage.clearDescricaoInput();

                assertNotEquals("", editPage.getDescricaoValidationMessage());
            } catch (UnhandledAlertException ignored) {

                Assertions.fail("Superpoder was edited (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to edit superpoder if efeitoColateral input is blank")
        void shouldNotEditSuperpoderIfEfeitoColateralIsBlank() {
            try {
                setupGoToEdit();

                editPage.clearEfeitosColateraisInput();

                assertNotEquals("", editPage.getEfeitosColateraisValidationMessage());
            } catch (UnhandledAlertException ignored) {

                Assertions.fail("Superpoder was edited (alert was triggered)");
            }
        }

    }

    @Nested
    @DisplayName("Tests for UI")
    class UI {

        @Test
        @DisplayName("Should home page link be visible and clickable even when screen is horizontally small")
        void shouldHomePageLinkBeClickableWhenScreenIsSmall() {
            SoftAssertions softly = new SoftAssertions();

            setupGoToEdit();

            driver.manage().window().setSize(new Dimension(500, 600));

            softly.assertThat(listPage.returnToHomePageLinkIsDisplayed()).isTrue();
            softly.assertThat(listPage.returnToHomePageLinkIsEnabled()).isTrue();

            softly.assertAll();
        }

        @Test
        @DisplayName("Should cadastro page link be visible and clickable even when screen is horizontally small")
        void shouldCadastroPageLinkBeClickableWhenScreenIsSmall() {
            SoftAssertions softly = new SoftAssertions();

            setupGoToEdit();

            driver.manage().window().setSize(new Dimension(500, 600));

            softly.assertThat(listPage.goToCadastroPageLinkLinkIsDisplayed()).isTrue();
            softly.assertThat(listPage.goToCadastroPageLinkLinkIsEnabled()).isTrue();

            softly.assertAll();
        }
    }

    private void cadastroSuperpoderFromFaker(){
        listPage.goToCadastro();
        CadastroSuperpoderPage pageCadastro = new CadastroSuperpoderPage(driver);
        pageCadastro.cadastroSuperpoderFromFaker();

        driver.switchTo().alert().accept();
        pageCadastro.returnToHomePage();
    }

    private void cadastroSuperpoderesFromFaker(int quantidade){
        for (int i = 0; i < quantidade; i++) {
            cadastroSuperpoderFromFaker();
        }
    }

    private void setupGoToEdit(){
        cadastroSuperpoderFromFaker();

        List<SuperpoderItemPage> superpoderes = listPage.getSuperpoderesItemPage();
        superpoderes.getFirst().goToEditPage();

        this.editPage = new EditarSuperpoderesPage(driver);
    }
}
