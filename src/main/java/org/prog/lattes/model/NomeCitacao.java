package org.prog.lattes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class NomeCitacao {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pesquisador pesquisador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
