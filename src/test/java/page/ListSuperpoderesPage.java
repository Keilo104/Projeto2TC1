package page;

import model.Superpoder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ListSuperpoderesPage {
    protected final WebDriver driver;

    private final By returnToHomePageLink = By.xpath("/html/body/div/body/div/header/h1/a");
    private final By goToCadastroPageLink = By.xpath("/html/body/div/body/div/header/nav/ul/li/a");

    private final By allSuperpoderes = By.className("post");

    public ListSuperpoderesPage(WebDriver driver) {
        this.driver = driver;

        if(!driver.getCurrentUrl().equals("https://site-tc1.vercel.app/")) {
            throw new IllegalStateException("This is not the List Superpoderes Inúteis page," +
                    "current page is: " + driver.getCurrentUrl());
        }
    }

    public void returnToHomePage() {
        driver.findElement(returnToHomePageLink).click();
    }

    public void goToCadastro() {
        driver.findElement(goToCadastroPageLink).click();
    }

    public boolean returnToHomePageLinkIsDisplayed() {
        return driver.findElement(returnToHomePageLink).isDisplayed();
    }

    public boolean returnToHomePageLinkIsEnabled() {
        return driver.findElement(returnToHomePageLink).isEnabled();
    }

    public boolean goToCadastroPageLinkLinkIsDisplayed() {
        return driver.findElement(goToCadastroPageLink).isDisplayed();
    }

    public boolean goToCadastroPageLinkLinkIsEnabled() {
        return driver.findElement(goToCadastroPageLink).isEnabled();
    }

    public List<Superpoder> getSuperpoderes() {
        return driver.findElements(allSuperpoderes)
                .stream()
                .map(e->{
                    SuperpoderItemPage superpoderItemPage = new SuperpoderItemPage(e);
                    return new Superpoder(superpoderItemPage);
                })
                .toList();
    }

    public List<SuperpoderItemPage> getSuperpoderesItemPage() {
        return driver.findElements(allSuperpoderes)
                .stream()
                .map(SuperpoderItemPage::new)
                .toList();
    }
}
