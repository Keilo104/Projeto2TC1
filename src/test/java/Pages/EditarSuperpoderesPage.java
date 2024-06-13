package Pages;

import Faker.SuperpoderFakerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class EditarSuperpoderesPage {
    protected WebDriver driver;

    private final By returnToHomePageLink = By.xpath("/html/body/div/body/div/header/h1/a");
    private final By goToCadastrarPageLink = By.xpath("/html/body/div/body/div/header/nav/ul/li/a");

    private final By nomeDoPoderInput = By.xpath("//*[@id=\"nome_do_poder\"]");
    private final By descricaoInput = By.xpath("//*[@id=\"descricao\"]");
    private final By efeitosColateraisInput = By.xpath("//*[@id=\"efeitos_colaterais\"]");
    private final By notaSelect = By.xpath("//*[@id=\"nota\"]");

    private final By submitButton = By.xpath("/html/body/div/body/div[2]/div/div/form/div[5]/button");

    public EditarSuperpoderesPage(WebDriver driver){
        this.driver = driver;

        if (!driver.getCurrentUrl().startsWith("https://site-tc1.vercel.app/editar/")){
            throw new IllegalStateException("This is not the editar Superpoder page," +
                    "current page is: "+ driver.getCurrentUrl());
        }
    }

    public void clearNomeInput(){
        this.driver.findElement(nomeDoPoderInput).clear();
    }
    public void clearDescricaoInput(){
        this.driver.findElement(descricaoInput).clear();
    }
    public void clearEfeitosColateraisInput(){
        this.driver.findElement(efeitosColateraisInput).clear();
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

    public void returnToHomePage() {
        driver.findElement(returnToHomePageLink).click();
    }

    public void goToCadastro() {
        driver.findElement(goToCadastrarPageLink).click();
    }

}
