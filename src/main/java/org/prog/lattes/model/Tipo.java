package org.prog.lattes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public enum Tipo {
    ARTIGO(1, "Artigo"), 
    LIVRO(2, "Livro");

    @Id
    private int id;
    private String nome;

    Tipo(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
}
