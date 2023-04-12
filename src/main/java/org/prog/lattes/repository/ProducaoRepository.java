package org.prog.lattes.repository;

import org.prog.lattes.model.Producao;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.OverridesAttribute.List;

public interface ProducaoRepository extends JpaRepository<Producao, Long>{
    
}
