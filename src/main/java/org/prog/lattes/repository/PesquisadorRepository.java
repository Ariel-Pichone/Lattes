package org.prog.lattes.repository;

import java.util.List;
import org.prog.lattes.model.Pesquisador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesquisadorRepository extends JpaRepository<Pesquisador, String> {

    List<Pesquisador> findByIdentificador(String identificador);
    
    List<Pesquisador> findByNomeContainingIgnoreCase(String nome);
    
    Boolean existsByIdentificador(String identificador);
}
