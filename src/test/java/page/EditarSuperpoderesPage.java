package page;

import model.Superpoder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class EditarSuperpoderesPage {
    protected final WebDriver driver;

    private final By returnToHomePageLink = By.xpath("/html/body/div/body/div/header/h1/a");
    private final By goToCadastrarPageLink = By.xpath("/html/body/div/body/div/header/nav/ul/li/a");

    private final By nomeDoPoderInput = By.xpath("//*[@id=\"nome_do_poder\"]");
    private final By descricaoInput = By.xpath("//*[@id=\"descricao\"]");
    private final By efeitosColateraisInput = By.xpath("//*[@id=\"efeitos_colaterais\"]");
    private final By notaSelect = By.xpath("//*[@id=\"nota\"]");

    private final By submitButton = By.xpath("/html/body/div/body/div[2]/div/div/form/div[5]/button");

    public EditarSuperpoderesPage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getCurrentUrl().startsWith("https://site-tc1.vercel.app/editar/")){
            throw new IllegalStateException("This is not the editar Superpoder page," +
                    "current page is: "+ driver.getCurrentUrl());
        }
    }

    public void setNomeInputText(String s) {
        this.driver.findElement(nomeDoPoderInput).clear();
        this.driver.findElement(nomeDoPoderInput).sendKeys(s);
    }

    public void setDescricaoInputText(String s) {
        this.driver.findElement(descricaoInput).clear();
        this.driver.findElement(descricaoInput).sendKeys(s);
    }

    public void setEfeitosColateraisInputText(String s) {
        this.driver.findElement(efeitosColateraisInput).clear();
        this.driver.findElement(efeitosColateraisInput).sendKeys(s);
    }

    public void setNotaSelectValue(String s) {
        Select selectNota = new Select(driver.findElement(notaSelect));
        selectNota.selectByValue(s);
    }

    public void setValuesFromSuperpoder(Superpoder superpoder) {
        setNomeInputText(superpoder.getNome());
        setDescricaoInputText(superpoder.getDescricao());
        setEfeitosColateraisInputText(superpoder.getEfeitosColaterais());
        setNotaSelectValue(superpoder.getNota());
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

    public void sendEdit() {
        driver.findElement(submitButton).click();
    }

}
