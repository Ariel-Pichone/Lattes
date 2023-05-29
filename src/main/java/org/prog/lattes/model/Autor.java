package org.prog.lattes.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    // Está aceitando cadastrar 2 autores com o mesmo nome
    // unique = true,
    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToMany (mappedBy = "autores")
    private List<Producao> producoes;

    @JsonIgnore
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "autor_citacao",
        joinColumns = @JoinColumn(name = "autor_id"),
        inverseJoinColumns = @JoinColumn(name = "citacao_id"))
    private List<Citacao> citacoes;

    public Autor() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Citacao> getCitacoes() {
        return this.citacoes;
    }

    public void setCitacoes(List<Citacao> citacoes) {
        this.citacoes = citacoes;
    }

    public List<Producao> getProducoes() {
        return this.producoes;
    }

    public void setProducoes(List<Producao> producoes) {
        this.producoes = producoes;
    }

    @Override
    public String toString() {
        return nome;
    }
}
