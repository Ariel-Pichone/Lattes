package org.prog.lattes.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Tipo tipoProducao;

    @ManyToMany
    @JoinTable(name = "pesquisador_produção",
         joinColumns = @JoinColumn(name = "producao_id"),
         inverseJoinColumns = @JoinColumn(name = "pesquisador_id"))
    private List<Pesquisador> pesquisadores = new ArrayList<>();


    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    public void setTipoProducao(Tipo tipoProducao) {
        this.tipoProducao = tipoProducao;
    }

    public Tipo getTipoProducao() {
        return tipoProducao;
    }
}   
