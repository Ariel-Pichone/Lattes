package org.prog.lattes.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Pesquisador{    
    
    @Id
    @Column(unique = true, nullable = false)
    private String identificador;

    @Column(unique = true, nullable = false, length = 100)
    private String nome;
    
    @Column(name = "uf", nullable = true, length = 5)
    private String ufNascimento;
    
    @ManyToOne
    private Instituto instituto;

    // um pesquisador tem muitas produções, mas uma produção pertence a apenas um pesquisador 
    @JsonIgnore
    @ManyToMany (cascade = CascadeType.ALL)//Se excluir o pesquisador vai escluir também as produções relacionadas a ele
    @JoinTable(name = "pesquisador_producao",
         joinColumns = @JoinColumn(name = "pesquisador_identificador"),
         inverseJoinColumns = @JoinColumn(name = "producao_id"))
    private List<Producao> producoes;

    public Pesquisador(){}

    public void setUfNascimento(String ufNascimento) {
        this.ufNascimento = ufNascimento;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUfNascimento() {
        return ufNascimento;
    }

    public List<Producao> getProducoes() {
        return producoes;
    }


    public void addProducao(List<Producao> producoes) {
        this.producoes = producoes;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    public Instituto getInstituto() {
        return instituto;
    }

    @Override
    public String toString() {
        return nome;
    }
}