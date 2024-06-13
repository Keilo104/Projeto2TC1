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

    public void returnToHomePage() {
        driver.findElement(returnToHomePageLink).click();
    }

    public void goToCadastro() {
        driver.findElement(goToCadastrarPageLink).click();
    }

    public String getNomeDoPoderValidationMessage() {
        return driver.findElement(nomeDoPoderInput).getAttribute("validationMessage");
    }

    public String getDescricaoValidationMessage() {
        return driver.findElement(descricaoInput).getAttribute("validationMessage");
    }

    public String getEfeitosColateraisValidationMessage() {
        return driver.findElement(efeitosColateraisInput).getAttribute("validationMessage");
    }

    public void cadastroSuperpoderFromSuperpoder(Superpoder superpoder) {
        driver.findElement(nomeDoPoderInput).sendKeys(superpoder.getNome());
        driver.findElement(descricaoInput).sendKeys(superpoder.getDescricao());
        driver.findElement(efeitosColateraisInput).sendKeys(superpoder.getEfeitosColaterais());

        Select selectNota = new Select(driver.findElement(notaSelect));
        selectNota.selectByValue(superpoder.getNota());

        driver.findElement(submitButton).click();
    }

    public void cadastroSuperpoderFromFaker() {
        driver.findElement(nomeDoPoderInput).sendKeys(SuperpoderFakerUtil.getValidNome());
        driver.findElement(descricaoInput).sendKeys(SuperpoderFakerUtil.getValidDescricao());
        driver.findElement(efeitosColateraisInput).sendKeys(SuperpoderFakerUtil.getValidEfeitosColaterais());

        Select selectNota = new Select(driver.findElement(notaSelect));
        selectNota.selectByValue(SuperpoderFakerUtil.getValidNota());

        driver.findElement(submitButton).click();
    }
}
