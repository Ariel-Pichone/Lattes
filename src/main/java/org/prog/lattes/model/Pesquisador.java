package org.prog.lattes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return identificador;
    }
     
  
    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }   

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((ufNascimento == null) ? 0 : ufNascimento.hashCode());
        result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
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
        if (instituto == null) {
            if (other.instituto != null)
                return false;
        } else if (!instituto.equals(other.instituto))
            return false;
        return true;
    }
}