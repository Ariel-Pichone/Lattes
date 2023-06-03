package org.prog.lattes.model;

public enum Tipo {
    ARTIGO("Artigo"),
    CAPITULO_LIVRO("Capitulo de Livro"), 
    LIVRO("Livro");

    private String nome;
    
    public String getNome() {
        return nome;
    }
    
    Tipo(String nome){
        this.nome = nome;
    }
}
