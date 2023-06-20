package org.prog.lattes.repository;

import java.util.ArrayList;
import java.util.List;
import org.prog.lattes.model.GrafoPesquisador;
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
    
    public static Specification<Producao> filtrarPorAnoInicio(Integer anoInicio) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("ano"), anoInicio);
    }
    
    public static Specification<Producao> filtrarPorAnoFim(Integer anoFim) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("ano"), anoFim);
    }

    public static Specification<Producao> filtrarPorInstituto(String instituto) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("pesquisador").get("instituto").get("nome")),
            "%" + instituto.toLowerCase() + "%");
    }

    public static Specification<Producao> filtrarPorPesquisador(String pesquisador) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("pesquisador").get("nome")), 
            "%" + pesquisador.toLowerCase() + "%");
    }

    public static Specification<Producao> filtrarPorTipoProducao(String tipoProducao) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("tipoProducao")), "%" + tipoProducao + "%");
    }

    public static Specification<Producao> filtrarPorAno(Integer ano) {
        return (root, query, builder) -> builder.equal(root.get("ano"), ano);
    }

    public Page<Producao> findByAno(Integer ano, Pageable pageable);

    @Query(value = "SELECT p.tipo_producao AS tipoProducao, COUNT(p.*) AS totalProducao "
         + "FROM producao AS p GROUP BY p.tipo_producao ORDER BY p.tipo_producao", nativeQuery = true)
    public List<TotalProducoesTipo> countTotalProducoesPorTipo();

    @Query(value = "SELECT p1.pesquisador AS pesquisador1, p2.pesquisador AS pesquisador2, p1.nome AS nomeProducao " +
            "FROM producao p1 " +
            "INNER JOIN producao p2 ON p1.nome = p2.nome " +
            "AND p1.pesquisador != p2.pesquisador;", nativeQuery = true)
    List<Object[]> findGrafoPesquisador();

    default List<GrafoPesquisador> grafoPesquisador() {
        List<Object[]> results = findGrafoPesquisador();
        List<GrafoPesquisador> graph = new ArrayList<>();

        for (Object[] row : results) {
            GrafoPesquisador grafoPesquisador = new GrafoPesquisador();
            grafoPesquisador.setPesquisador1((String) row[0]);
            grafoPesquisador.setPesquisador2((String) row[1]);
            grafoPesquisador.setNomeProducao((String) row[2]);
            graph.add(grafoPesquisador);
        }
        return graph;
    }

    // default List<GrafoInstituto> grafoInstituto() {
    //     List<Object[]> results = findGrafoPesquisador();
    //     List<GrafoInstituto> graph = new ArrayList<>();

    //     for (Object[] row : results) {
    //         GrafoInstituto grafoInstituto = new GrafoInstituto();
    //         grafoInstituto.setInstituto1((Instituto) row[0].instituto);
    //         grafoInstituto.setInstituto2((Instituto) row[1].instituto);
    //         grafoInstituto.setNomeProducao((String) row[2]);
    //         graph.add(grafoInstituto);
    //     }
    //     return graph;
    // }
}