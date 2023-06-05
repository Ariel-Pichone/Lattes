package org.prog.lattes.model;

public class TotalProducoesAno {
    Integer anoProducao;
    Integer artigo;
    Integer livro;
    Long totalProducao;

    public TotalProducoesAno() {
    }

    public TotalProducoesAno(Integer anoProducao, Integer artigo, Integer livro, Long totalProducao) {
        this.anoProducao = anoProducao;
        this.artigo = artigo;
        this.livro = livro;
        this.totalProducao = totalProducao;
    }

    public Integer getAnoProducao() {
        return this.anoProducao;
    }

    public void setAnoProducao(Integer anoProducao) {
        this.anoProducao = anoProducao;
    }

    public Integer getArtigo() {
        return this.artigo;
    }

    public void setArtigo(Integer artigo) {
        this.artigo = artigo;
    }

    public Integer getLivro() {
        return this.livro;
    }

    public void setLivro(Integer livro) {
        this.livro = livro;
    }

    public Long getTotalProducao() {
        return this.totalProducao;
    }

    public void setTotalProducao(Long totalProducao) {
        this.totalProducao = totalProducao;
    }
}
