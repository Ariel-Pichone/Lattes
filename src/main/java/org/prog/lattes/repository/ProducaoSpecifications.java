package org.prog.lattes.repository;

import org.prog.lattes.model.Producao;
import org.springframework.data.jpa.domain.Specification;

public class ProducaoSpecifications {
    
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
}