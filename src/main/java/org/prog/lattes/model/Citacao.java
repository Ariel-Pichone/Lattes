package org.prog.lattes.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Citacao {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    // Está aceitando cadastrar 2 citações com o mesmo nome
    // unique = true,
    @Column(nullable = false, length = 100)
    private String nomeCitacao;

    @ManyToMany (mappedBy = "citacoes")
    private List<Autor> autores;
    
    public Citacao() {}

    public Long getId() {
        return this.id;
    }
    
    public String getNomeCitacao() {
        return this.nomeCitacao;
    }

    public void setNomeCitacao(String nomeCitacao) {
        this.nomeCitacao = nomeCitacao;
    }

    public List<Autor> getAutores() {
        return this.autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }    
}
