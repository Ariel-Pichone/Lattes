package org.prog.lattes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;


@Entity
public class Pesquisador extends AbstractModel{
    
    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "uf", length = 5)
    private String ufNascimento;

    @Column(name = "identificador", length = 30)
    private String identificador;
        
    @ManyToOne
    private Instituto instituto;

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

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return identificador;
    }
    
}
