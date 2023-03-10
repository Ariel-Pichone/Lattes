package org.prog.lattes.repository;

import org.prog.lattes.model.Instituto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutoRepository extends JpaRepository<Instituto, Long>{
    
}
