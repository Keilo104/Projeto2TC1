package Test;

import Model.Superpoder;
import Page.CadastroSuperpoderPage;
import Page.ListSuperpoderesPage;
import Page.SuperpoderItemPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListSuperpoderesTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    private final String PAGE_URL = "https://site-tc1.vercel.app/";
    private ListSuperpoderesPage listPage;

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
        @DisplayName("Should path back to home when clicking home link")
        void shouldPathToHome() {
            listPage.returnToHomePage();

            assertEquals("https://site-tc1.vercel.app/", driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should path to cadastro when clicking cadastrar link")
        void shouldPathToCadastro() {
            listPage.goToCadastro();

            assertEquals("https://site-tc1.vercel.app/cadastro", driver.getCurrentUrl());
        }
    }

    @Nested
    @DisplayName("Tests for deleting superpoderes")
    class DeleteSuperpoderes {

        @Test
        @DisplayName("Should superpoderes list be empty after deleting the only superpoder")
        void shouldSuperpoderesListBeEmptyAfterDeletingTheOnlySuperpoder() {
            try {
                SoftAssertions softly = new SoftAssertions();

                cadastroSuperpoderFromFaker();

                List<SuperpoderItemPage> superpoderes = listPage.getSuperpoderesItemPage();
                superpoderes.getFirst().deleteSuperpoder();

                webDriverWait.until(ExpectedConditions.alertIsPresent());
                String alertMessage = driver.switchTo().alert().getText();
                driver.switchTo().alert().accept();

                superpoderes = listPage.getSuperpoderesItemPage();

                softly.assertThat(alertMessage).isEqualTo("Poder excluído com sucesso!");
                softly.assertThat(superpoderes).isEmpty();

                softly.assertAll();

            } catch (TimeoutException ignored) {
                Assertions.fail("Superpoder wasn't deleted");
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

            driver.manage().window().setSize(new Dimension(500, 600));

            softly.assertThat(listPage.returnToHomePageLinkIsDisplayed()).isTrue();
            softly.assertThat(listPage.returnToHomePageLinkIsEnabled()).isTrue();

            softly.assertAll();
        }

        @Test
        @DisplayName("Should cadastro page link be visible and clickable even when screen is horizontally small")
        void shouldCadastroPageLinkBeClickableWhenScreenIsSmall() {
            SoftAssertions softly = new SoftAssertions();

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
}
