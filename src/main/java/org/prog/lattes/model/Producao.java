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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private TipoProducao tipoProducao;
    
    @ManyToMany (mappedBy = "producoes")
    private List<Pesquisador> pesquisadores;

    @JsonIgnore
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "producao_autor",
        joinColumns = @JoinColumn(name = "producao_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    public void addPesquisador(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    public void addAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return nome;
    }
} 