package org.prog.lattes.repository;

import org.prog.lattes.model.Producao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducaoRepository extends JpaRepository<Producao, Long>, JpaSpecificationExecutor<Producao>{
    
}
