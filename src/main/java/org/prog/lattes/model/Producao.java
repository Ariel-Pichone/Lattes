package org.prog.lattes.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Override
    public String toString() {
        return nome;
    }
} 