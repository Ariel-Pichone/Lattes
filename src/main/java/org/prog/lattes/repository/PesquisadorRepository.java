package org.prog.lattes.repository;

import java.util.ArrayList;
import java.util.List;

import org.prog.lattes.model.GrafoInstituto;
import org.prog.lattes.model.Instituto;
import org.prog.lattes.model.Pesquisador;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.persistence.criteria.Join;

@Repository
public interface PesquisadorRepository extends JpaRepository<Pesquisador, String>, JpaSpecificationExecutor<Pesquisador> {

    public static Specification<Pesquisador> filtrarPorIdentificador(String identificador) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("identificador")), "%" + identificador + "%");
    }
    
    public static Specification<Pesquisador> filtrarPorNome(String nome) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Pesquisador> filtrarPorInstituto(Long instituto) {
        return (root, query, builder) -> {
            Join<Pesquisador, Instituto> join = root.join("instituto");
            return builder.equal(join.get("id"), instituto);
        };
    }

    Pesquisador findByIdentificador(String identificador);
      
    Boolean existsByIdentificador(String identificador);

    @Query(value = "SELECT p1.pesquisador AS pesquisador1, p2.pesquisador AS pesquisador2, p1.nome AS nomeProducao " +
            "FROM producao p1 " +
            "INNER JOIN producao p2 ON p1.nome = p2.nome " +
            "AND p1.pesquisador != p2.pesquisador;", nativeQuery = true)
    List<Object[]> findGrafoInstituto();

    default List<GrafoInstituto> grafoInstituto() {
        List<Object[]> results = findGrafoInstituto();
        List<GrafoInstituto> graph = new ArrayList<>();

        for (Object[] row : results) {
            GrafoInstituto grafoInstituto = new GrafoInstituto();
            grafoInstituto.setInstituto1((Instituto) findByIdentificador((String) row[0]).getInstituto());
            grafoInstituto.setInstituto2((Instituto) findByIdentificador((String) row[1]).getInstituto());
            grafoInstituto.setNomeProducao((String) row[2]);
            graph.add(grafoInstituto);
        }
        return graph;
    }
}
