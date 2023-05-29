package org.prog.lattes.repository;

import java.util.List;
import org.prog.lattes.model.Producao;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
        return (root, query, builder) -> builder.equal(builder.lower(root.get("tipoProducao")), tipoProducao.toLowerCase());
    }
    
    List<Producao> findByAno(Integer ano);

    long count();

    @Query("SELECT COUNT(p) FROM Producao p WHERE p.ano = :ano")
    long countProducaoPorAno(@Param("ano") int ano);
    
    @Query(value = "SELECT p.ano AS anoProducao, COUNT(p.*) AS totalProducao "
         + "FROM producao AS p GROUP BY p.ano ORDER BY p.ano", nativeQuery = true)
    List<TotalProducoes> countTotalProducoesPorAno();

    @Query("SELECT COUNT(p) FROM Producao p WHERE p.tipoProducao = 'ARTIGO'")
    long countArtigo();

    @Query("SELECT COUNT(p) FROM Producao p WHERE p.tipoProducao = 'LIVRO'")
    long countLivro();
}
