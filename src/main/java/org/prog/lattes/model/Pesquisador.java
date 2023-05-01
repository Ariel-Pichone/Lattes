package org.prog.lattes.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Pesquisador{    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nome;
    
    @Column(name = "uf", length = 5)
    private String ufNascimento;
    
    private String identificador;
    
    @ManyToMany(mappedBy = "pesquisadores")
    private List<Producao> producoes;    

    @ManyToOne
    private Instituto instituto;

    public Pesquisador(){
         producoes = new ArrayList<>();
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
    
    public void setUfNascimento(String ufNascimento) {
        this.ufNascimento = ufNascimento;
    }
    
    public String getIdentificador() {
        return identificador;
    }
    
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    public Instituto getInstituto() {
        return instituto;
    }
    
    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }   
    
    public List<Producao> getProducoes() {
        return producoes;
    }
    
    public void setProducoes(List<Producao> producoes) {
        this.producoes = producoes;
    }

    public void adicionarProducao(List<Producao> newElements){
        producoes.addAll(newElements);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((ufNascimento == null) ? 0 : ufNascimento.hashCode());
        result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
        result = prime * result + ((producoes == null) ? 0 : producoes.hashCode());
        result = prime * result + ((instituto == null) ? 0 : instituto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pesquisador other = (Pesquisador) obj;
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
        if (ufNascimento == null) {
            if (other.ufNascimento != null)
                return false;
        } else if (!ufNascimento.equals(other.ufNascimento))
            return false;
        if (identificador == null) {
            if (other.identificador != null)
                return false;
        } else if (!identificador.equals(other.identificador))
            return false;
        if (producoes == null) {
            if (other.producoes != null)
                return false;
        } else if (!producoes.equals(other.producoes))
            return false;
        if (instituto == null) {
            if (other.instituto != null)
                return false;
        } else if (!instituto.equals(other.instituto))
            return false;
        return true;
    }
}