package SuperpoderTest;

import Pages.CadastroSuperpoderPage;
import Pages.EditarSuperpoderesPage;
import Pages.ListSuperpoderesPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditarSuperpoderesTest {
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
    @DisplayName("Tests for Editing Superpowers")
    class Editing{

    }

    private void cadastroSuperpoderFromFaker(){
        page.goToCadastro();
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
