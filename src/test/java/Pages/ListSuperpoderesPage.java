package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListSuperpoderesPage {
    protected WebDriver driver;

    private final By returnToHomePageLink = By.xpath("/html/body/div/body/div/header/h1/a");
    private final By goToCadastrarPageLink = By.xpath("/html/body/div/body/div/header/nav/ul/li/a");

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

    public void goToCadastrar() {
        driver.findElement(goToCadastrarPageLink).click();
    }
}
