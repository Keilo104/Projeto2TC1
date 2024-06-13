package Pages;

import Faker.SuperpoderFakerUtil;
import Model.Superpoder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CadastroSuperpoderPage {
    protected WebDriver driver;

    private final By returnToHomePageLink = By.xpath("/html/body/div/body/div/header/h1/a");
    private final By goToCadastrarPageLink = By.xpath("/html/body/div/body/div/header/nav/ul/li/a");

    private final By nomeDoPoderInput = By.xpath("//*[@id=\"nome_do_poder\"]");
    private final By descricaoInput = By.xpath("//*[@id=\"descricao\"]");
    private final By efeitosColateraisInput = By.xpath("//*[@id=\"efeitos_colaterais\"]");
    private final By notaSelect = By.xpath("//*[@id=\"nota\"]");

    private final By submitButton = By.xpath("/html/body/div/body/div[2]/div/div/form/div[5]/button");

    public CadastroSuperpoderPage(WebDriver driver) {
        this.driver = driver;

        if(!driver.getCurrentUrl().equals("https://site-tc1.vercel.app/cadastro")) {
            throw new IllegalStateException("This is not the Cadastrar Superpoderes Inúteis page," +
                    "current page is: " + driver.getCurrentUrl());
        }
    }
}
