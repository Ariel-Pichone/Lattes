package org.prog.lattes.repository;

import java.util.List;
import org.prog.lattes.model.Pesquisador;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PesquisadorRepository extends JpaRepository<Pesquisador, String>, JpaSpecificationExecutor<Pesquisador> {

    public static Specification<Pesquisador> filtrarPorIdentificador(String identificador) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("identificador")), "%" + identificador + "%");
    }
    
    public static Specification<Pesquisador> filtrarPorNome(String nome) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    @Query(value = "SELECT * FROM Pesquisador p WHERE p.instituto_id = :instituto", nativeQuery = true)
    List<Pesquisador> listPesquisadorPorInstituto(Long instituto);

    List<Pesquisador> findByIdentificador(String identificador);
      
    Boolean existsByIdentificador(String identificador);
}
