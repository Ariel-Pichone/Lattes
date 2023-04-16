package org.prog.lattes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public enum Tipo {
    ARTIGO("Artigo"), 
    LIVRO("Livro");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    Tipo(String nome){
        this.nome = nome;
    }
}
