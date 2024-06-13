package Model;

import Faker.SuperpoderFakerUtil;
import Page.SuperpoderItemPage;

import java.util.Objects;

import static Faker.SuperpoderFakerUtil.getValidEfeitosColaterais;
import static Faker.SuperpoderFakerUtil.getValidNota;

public class Superpoder {
    private String nome;
    private String descricao;
    private String efeitosColaterais;
    private String nota;

    public Superpoder() { }

    public Superpoder(SuperpoderItemPage superpoderItemPage) {
        this.nome = superpoderItemPage.setNomeFromElement();
        this.descricao = superpoderItemPage.setDescricaoFromElement();
        this.efeitosColaterais = superpoderItemPage.setEfeitosColateraisFromElement();
        this.nota = superpoderItemPage.setNotaFromElement();

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEfeitosColaterais() {
        return efeitosColaterais;
    }

    public void setEfeitosColaterais(String efeitosColaterais) {
        this.efeitosColaterais = efeitosColaterais;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
