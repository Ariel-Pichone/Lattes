package org.prog.lattes.repository;

import java.util.List;
import org.prog.lattes.model.Instituto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutoRepository extends JpaRepository<Instituto, Long> {
    
    List<Instituto> findByNomeContainingIgnoreCase(String nome);

    List<Instituto> findByAcronimoContainingIgnoreCase(String acronimo);

    List<Instituto> findByNomeContainingIgnoreCaseOrAcronimoContainingIgnoreCase(String nome, String acronimo);

    long count();
}