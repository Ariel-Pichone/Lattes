package org.prog.lattes.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Producao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", length = 10485760)
    private String nome;

    private String ano;

    @Enumerated(EnumType.STRING)
    private Tipo tipoProducao;

    @ManyToMany
    @JoinTable(name = "pesquisador_producao",
         joinColumns = @JoinColumn(name = "producao_id", referencedColumnName = "id"),
         inverseJoinColumns = @JoinColumn(name = "pesquisador_id", referencedColumnName = "id"))
    private List<Pesquisador> pesquisadores;

    public Producao(){
        pesquisadores = new ArrayList<>();
    }

    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    public void setTipoProducao(Tipo tipoProducao) {
        this.tipoProducao = tipoProducao;
    }

    public String getAno() {
        return ano;
    }
    
    public void setAno(String ano) {
        this.ano = ano;
    }

    public Tipo getTipoProducao() {
        return tipoProducao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarPesquisador(List<Pesquisador> novosPesquisadores){
        pesquisadores.addAll(novosPesquisadores);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return nome;
    }
}   
