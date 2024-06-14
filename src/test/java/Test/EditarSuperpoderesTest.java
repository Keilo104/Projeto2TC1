package Test;

import Faker.SuperpoderFakerUtil;
import Model.Superpoder;
import Page.CadastroSuperpoderPage;
import Page.EditarSuperpoderesPage;
import Page.ListSuperpoderesPage;
import Page.SuperpoderItemPage;
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

                editPage.setNomeInputText("");

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

                editPage.setDescricaoInputText("");

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

                editPage.setEfeitosColateraisInputText("");

                assertNotEquals("", editPage.getEfeitosColateraisValidationMessage());
            } catch (UnhandledAlertException ignored) {

                Assertions.fail("Superpoder was edited (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to edit superpoder if nome input is a empty space")
        void shouldNotEditSuperpoderIfNomeIsEmptySpace() {
            try {
                setupGoToEdit();

                editPage.setNomeInputText(" ");

                assertNotEquals("", editPage.getNomeDoPoderValidationMessage());
            } catch (UnhandledAlertException ignored) {

                Assertions.fail("Superpoder was edited (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to edit superpoder if descricao input is a empty space")
        void shouldNotEditSuperpoderIfDescricaoIsEmptySpace() {
            try {
                setupGoToEdit();

                editPage.setDescricaoInputText(" ");

                assertNotEquals("", editPage.getDescricaoValidationMessage());
            } catch (UnhandledAlertException ignored) {

                Assertions.fail("Superpoder was edited (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should not be able to edit superpoder if efeitoColateral input is a empty space")
        void shouldNotEditSuperpoderIfEfeitoColateralIsEmptySpace() {
            try {
                setupGoToEdit();

                editPage.setEfeitosColateraisInputText(" ");

                assertNotEquals("", editPage.getEfeitosColateraisValidationMessage());
            } catch (UnhandledAlertException ignored) {

                Assertions.fail("Superpoder was edited (alert was triggered)");
            }
        }

        @Test
        @DisplayName("Should superpoderes list have edited superpoder when name is edited")
        void shouldSuperpoderesListHaveEditedSuperpoderWhenNameEdited() {
            try {
                SoftAssertions softly = new SoftAssertions();

                cadastroSuperpoderFromFaker();

                List<SuperpoderItemPage> superpoderesItemPage = listPage.getSuperpoderesItemPage();
                Superpoder toBeEdited = new Superpoder(superpoderesItemPage.getFirst());
                Superpoder postEdited = new Superpoder(superpoderesItemPage.getFirst());

                superpoderesItemPage.getFirst().goToEditPage();
                editPage = new EditarSuperpoderesPage(driver);

                String editedName = SuperpoderFakerUtil.getValidNome();
                postEdited.setNome(editedName);
                editPage.setNomeInputText(editedName);
                editPage.sendEdit();

                webDriverWait.until(ExpectedConditions.alertIsPresent());
                String alertMessage = driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();

                List<Superpoder> superpoderes = listPage.getSuperpoderes();

                softly.assertThat(alertMessage).isEqualTo("Poder atualizado com sucesso!");
                softly.assertThat(superpoderes).contains(postEdited);
                softly.assertThat(superpoderes).doesNotContain(toBeEdited);

                softly.assertAll();

            } catch (TimeoutException ignored) {
                Assertions.fail("Superpoder wasn't Edited");
            }
        }

        @Test
        @DisplayName("Should superpoderes list have edited superpoder when descricao is edited")
        void shouldSuperpoderesListHaveEditedSuperpoderWhenDescricaoEdited() {
            try {
                SoftAssertions softly = new SoftAssertions();

                cadastroSuperpoderFromFaker();

                List<SuperpoderItemPage> superpoderesItemPage = listPage.getSuperpoderesItemPage();
                Superpoder toBeEdited = new Superpoder(superpoderesItemPage.getFirst());
                Superpoder postEdited = new Superpoder(superpoderesItemPage.getFirst());

                superpoderesItemPage.getFirst().goToEditPage();
                editPage = new EditarSuperpoderesPage(driver);

                String editedDesc = SuperpoderFakerUtil.getValidDescricao();
                postEdited.setDescricao(editedDesc);
                editPage.setDescricaoInputText(editedDesc);
                editPage.sendEdit();

                webDriverWait.until(ExpectedConditions.alertIsPresent());
                String alertMessage = driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();

                List<Superpoder> superpoderes = listPage.getSuperpoderes();

                softly.assertThat(alertMessage).isEqualTo("Poder atualizado com sucesso!");
                softly.assertThat(superpoderes).contains(postEdited);
                softly.assertThat(superpoderes).doesNotContain(toBeEdited);

                softly.assertAll();

            } catch (TimeoutException ignored) {
                Assertions.fail("Superpoder wasn't Edited");
            }
        }

        @Test
        @DisplayName("Should superpoderes list have edited superpoder when efeitoColateral is edited")
        void shouldSuperpoderesListHaveEditedSuperpoderWhenEfeitoColateralEdited() {
            try {
                SoftAssertions softly = new SoftAssertions();

                cadastroSuperpoderFromFaker();

                List<SuperpoderItemPage> superpoderesItemPage = listPage.getSuperpoderesItemPage();
                Superpoder toBeEdited = new Superpoder(superpoderesItemPage.getFirst());
                Superpoder postEdited = new Superpoder(superpoderesItemPage.getFirst());

                superpoderesItemPage.getFirst().goToEditPage();
                editPage = new EditarSuperpoderesPage(driver);

                String editedEfeitoColateral = SuperpoderFakerUtil.getValidEfeitosColaterais();
                postEdited.setEfeitosColaterais(editedEfeitoColateral);
                editPage.setEfeitosColateraisInputText(editedEfeitoColateral);
                editPage.sendEdit();

                webDriverWait.until(ExpectedConditions.alertIsPresent());
                String alertMessage = driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();

                List<Superpoder> superpoderes = listPage.getSuperpoderes();

                softly.assertThat(alertMessage).isEqualTo("Poder atualizado com sucesso!");
                softly.assertThat(superpoderes).contains(postEdited);
                softly.assertThat(superpoderes).doesNotContain(toBeEdited);

                softly.assertAll();

            } catch (TimeoutException ignored) {
                Assertions.fail("Superpoder wasn't Edited");
            }
        }

        @Test
        @DisplayName("Should superpoderes list have edited superpoder when nota is edited")
        void shouldSuperpoderesListHaveEditedSuperpoderWhenNotaEdited() {
            try {
                SoftAssertions softly = new SoftAssertions();

                cadastroSuperpoderFromFaker();

                List<SuperpoderItemPage> superpoderesItemPage = listPage.getSuperpoderesItemPage();
                Superpoder toBeEdited = new Superpoder(superpoderesItemPage.getFirst());
                Superpoder postEdited = new Superpoder(superpoderesItemPage.getFirst());

                superpoderesItemPage.getFirst().goToEditPage();
                editPage = new EditarSuperpoderesPage(driver);

                String editedNota = SuperpoderFakerUtil.getValidNota();
                postEdited.setNota(editedNota);
                editPage.setNotaSelectValue(editedNota);
                editPage.sendEdit();

                webDriverWait.until(ExpectedConditions.alertIsPresent());
                String alertMessage = driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();

                List<Superpoder> superpoderes = listPage.getSuperpoderes();

                softly.assertThat(alertMessage).isEqualTo("Poder atualizado com sucesso!");
                softly.assertThat(superpoderes).contains(postEdited);
                softly.assertThat(superpoderes).doesNotContain(toBeEdited);

                softly.assertAll();

            } catch (TimeoutException ignored) {
                Assertions.fail("Superpoder wasn't Edited");
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
