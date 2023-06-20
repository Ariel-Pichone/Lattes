package org.prog.lattes.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToMany (mappedBy = "autores")
    private List<Producao> producoes;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "autor_citacao",
        joinColumns = @JoinColumn(name = "autor_id"),
        inverseJoinColumns = @JoinColumn(name = "citacao_id"))
    private List<Citacao> citacoes;

    public void addProducao(Producao producao) {
        if (producoes == null) {
            producoes = new ArrayList<>();
        }
        producoes.add(producao);
    }

    public void addCitacao(Citacao citacao) {
        if (citacoes == null) {
            citacoes = new ArrayList<>();
        }
        citacoes.add(citacao);
    }

    @Override
    public String toString() {
        return nome;
    }
}