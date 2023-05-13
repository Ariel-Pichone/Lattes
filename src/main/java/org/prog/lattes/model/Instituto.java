package org.prog.lattes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Instituto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String acronimo;
    
    public Instituto() {
    }

    public Instituto(String nome, String acronimo) {
        this.nome = nome;
        this.acronimo = acronimo;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getAcronimo() {
        return acronimo;
    }
    
    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((acronimo == null) ? 0 : acronimo.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Instituto other = (Instituto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (acronimo == null) {
            if (other.acronimo != null)
                return false;
        } else if (!acronimo.equals(other.acronimo))
            return false;
        return true;
    }   
}