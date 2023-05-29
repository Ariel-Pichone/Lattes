package org.prog.lattes.repository;

import java.util.List;

import org.prog.lattes.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutorRepository extends JpaRepository<Autor, Long>{
    
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Autor a WHERE a.nome = :nome")
    boolean autorExiste(@Param("nome") String nome);

    List<Autor> findByNomeContainingIgnoreCase(String nome);
}
