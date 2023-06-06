package org.prog.lattes.repository;

import org.prog.lattes.model.Instituto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.Predicate;

@Repository
public interface InstitutoRepository extends JpaRepository<Instituto, Long>, JpaSpecificationExecutor<Instituto> {
    
    public static Specification<Instituto> filtrarPorNome(String nome) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }
    
    public static Specification<Instituto> filtrarPorAcronimo(String acronimo) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("acronimo")), "%" + acronimo.toLowerCase() + "%");
    }

    public static Specification<Instituto> filtrarPorNomeOuAcronimo(String nome, String acronimo) {
        return (root, query, builder) -> {
            Predicate nomePredicate = (Predicate) builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
            Predicate acronimoPredicate = (Predicate) builder.like(builder.lower(root.get("acronimo")), "%" + acronimo.toLowerCase() + "%");
            
            return builder.and(nomePredicate, acronimoPredicate);
        };
    }
}