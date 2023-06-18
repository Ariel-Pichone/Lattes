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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return nome;
    }
}
