package org.prog.lattes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Instituto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String acronimo;
    
    public Instituto() {}

    public Instituto(String nome, String acronimo) {
        this.nome = nome;
        this.acronimo = acronimo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getAcronimo() {
        return acronimo;
    }
    
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}