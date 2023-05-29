package org.prog.lattes.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

@Entity
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(columnDefinition = "text", length = 10485760)
    private String nome;
    
    @Column(nullable = false)
    private Integer ano;

    @Enumerated(EnumType.STRING)
    private Tipo tipoProducao;
    
    @ManyToMany (mappedBy = "producoes")
    private List<Pesquisador> pesquisadores;

    @JsonIgnore
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "producao_autor",
        joinColumns = @JoinColumn(name = "producao_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    public Producao(){}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Tipo getTipoProducao() {
        return tipoProducao;
    }

    public void setTipoProducao(Tipo tipoProducao) {
        this.tipoProducao = tipoProducao;
    }

    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void addPesquisador(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }
    
    public void setPesquisadores(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    public List<Autor> getAutores() {
        return this.autores;
    }

    public void addAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return nome;
    }
} 