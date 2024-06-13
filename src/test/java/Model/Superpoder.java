package Model;

import Faker.SuperpoderFakerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

import static Faker.SuperpoderFakerUtil.getValidEfeitosColaterais;
import static Faker.SuperpoderFakerUtil.getValidNota;

public class Superpoder {
    private final By nomeDoPoderElement = By.className("post-title");
    private final By descricaoElement = By.className("post-excerpt");
    private final By efeitosColateraisElement = By.xpath("//p[2]");
    private final By notaSelectElement = By.className("stars");

    private final By editButton = By.xpath("//div/button[1]");
    private final By deleteButton = By.xpath("//div/button[2]");

    private String nome;
    private String descricao;
    private String efeitosColaterais;
    private String nota;

    public Superpoder() { }

    public Superpoder(WebElement e) {
        this.setNomeFromElement(e);
        this.setDescricaoFromElement(e);
        this.setEfeitosColateraisFromElement(e);
        this.setNotaFromElement(e);
    }

    public static Superpoder FromFaker(){
        Superpoder superpoder =  new Superpoder();

        superpoder.nome = SuperpoderFakerUtil.getValidNome();
        superpoder.descricao = SuperpoderFakerUtil.getValidDescricao();
        superpoder.efeitosColaterais = getValidEfeitosColaterais();
        superpoder.nota = getValidNota();

        return superpoder;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private void setNomeFromElement(WebElement e) {
        this.nome = e.findElement(nomeDoPoderElement).getText();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private void setDescricaoFromElement(WebElement e) {
        this.descricao = e.findElement(descricaoElement).getText();
    }

    public String getEfeitosColaterais() {
        return efeitosColaterais;
    }

    public void setEfeitosColaterais(String efeitosColaterais) {
        this.efeitosColaterais = efeitosColaterais;
    }

    private void setEfeitosColateraisFromElement(WebElement e) {
        String efeitosColaterais = e.findElement(efeitosColateraisElement).getText();

        this.efeitosColaterais = efeitosColaterais.substring(20);
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    private void setNotaFromElement(WebElement e) {
        String stars = e.findElement(notaSelectElement).getText();

        this.nota = Integer.toString(stars.length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Superpoder that = (Superpoder) o;
        return Objects.equals(nome, that.nome) && Objects.equals(descricao, that.descricao) && Objects.equals(efeitosColaterais, that.efeitosColaterais) && Objects.equals(nota, that.nota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao, efeitosColaterais, nota);
    }

    @Override
    public String toString() {
        return "Superpoder{" +
                "nota='" + nota + '\'' +
                ", efeitosColaterais='" + efeitosColaterais + '\'' +
                ", descricao='" + descricao + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
