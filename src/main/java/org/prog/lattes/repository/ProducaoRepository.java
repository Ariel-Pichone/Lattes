package org.prog.lattes.repository;

import java.util.List;
import org.prog.lattes.model.Producao;
import org.prog.lattes.model.TotalProducoesTipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducaoRepository extends JpaRepository<Producao, Long>, JpaSpecificationExecutor<Producao>{
    
    public static Specification<Producao> filtrarPorDataInicio(Integer dataInicio) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("ano"), dataInicio);
    }
    
    public static Specification<Producao> filtrarPorDataFim(Integer dataFim) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("ano"), dataFim);
    }

    public static Specification<Producao> filtrarPorInstituto(String instituto) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("pesquisadores").get("instituto").get("nome")),
            "%" + instituto.toLowerCase() + "%");
    }

    public static Specification<Producao> filtrarPorPesquisador(String pesquisador) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("pesquisadores").get("nome")), 
            "%" + pesquisador.toLowerCase() + "%");
    }

    public static Specification<Producao> filtrarPorTipoProducao(String tipoProducao) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("tipoProducao")), "%" + tipoProducao.toLowerCase() + "%");
    }
    
    Page<Producao> findByAno(Integer ano, Pageable pageable);

    @Query(value = "SELECT p.tipo_producao AS tipoProducao, COUNT(p.*) AS totalProducao "
         + "FROM producao AS p GROUP BY p.tipo_producao ORDER BY p.tipo_producao", nativeQuery = true)
    List<TotalProducoesTipo> countTotalProducoesPorTipo();

    // public static List<GrafoPesquisador> grafoPesquisador() {
    //     String jpql = "SELECT DISTINCT p.id, pr.id, pr.nome FROM Producao p " +
    //             "JOIN p.pesquisadores pr";

    //     List<GrafoPesquisador> resultados;

    //     try {
    //         TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
    //         List<Object[]> rows = query.getResultList();

    //         resultados = new ArrayList<>();

    //         for (Object[] row : rows) {
    //             Long idPesquisador1 = (Long) row[0];
    //             Long idPesquisador2 = (Long) row[1];
    //             String nomeProducao = (String) row[2];

    //             GrafoPesquisador grafoPesquisador = new GrafoPesquisador();
    //             grafoPesquisador.setIdPesquisador1(idPesquisador1);
    //             grafoPesquisador.setIdPesquisador2(idPesquisador2);
    //             grafoPesquisador.setNomeProducao(nomeProducao);

    //             resultados.add(grafoPesquisador);
    //         }

    //         return resultados;
    //     } catch (NoResultException e) {
    //         // Trate o caso em que nenhum resultado é encontrado, se necessário
    //         return Collections.emptyList();
    //     }
    // }
}