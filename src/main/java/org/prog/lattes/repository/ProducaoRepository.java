package org.prog.lattes.repository;

import org.prog.lattes.model.Producao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.OverridesAttribute.List;

@Repository
public interface ProducaoRepository extends JpaRepository<Producao, Long>{
    
}
