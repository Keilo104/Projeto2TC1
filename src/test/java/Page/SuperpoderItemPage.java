package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SuperpoderItemPage {

    private final By nomeDoPoderElement = By.className("post-title");
    private final By descricaoElement = By.className("post-excerpt");
    private final By efeitosColateraisElement = By.xpath("//p[2]");
    private final By notaSelectElement = By.className("stars");

    private final By editButton = By.xpath("//div/button[1]");
    private final By deleteButton = By.xpath("//div/button[2]");

    protected WebElement element;

    public SuperpoderItemPage(WebElement element) {
        this.element = element;
    }

    public void goToEditPage() {
        element.findElement(editButton).click();
    }

    public void deleteSuperpoder() {
        element.findElement(deleteButton).click();
    }

    public String setNomeFromElement() {
        return element.findElement(nomeDoPoderElement).getText();
    }

    public String setDescricaoFromElement() {
        return element.findElement(descricaoElement).getText();
    }

    public String setEfeitosColateraisFromElement() {
        String efeitosColaterais = element.findElement(efeitosColateraisElement).getText();

        return efeitosColaterais.substring(20);
    }

    public String setNotaFromElement() {
        String stars = element.findElement(notaSelectElement).getText();

        return Integer.toString(stars.length());
    }
}
