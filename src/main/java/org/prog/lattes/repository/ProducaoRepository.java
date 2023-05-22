package org.prog.lattes.repository;

import java.util.List;

import org.prog.lattes.model.Producao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducaoRepository extends JpaRepository<Producao, Long>, JpaSpecificationExecutor<Producao>{
    
    List<Producao> findByAno(Integer ano);

    long count();

    @Query("SELECT COUNT(p) FROM Producao p WHERE p.tipoProducao = 'ARTIGO'")
    long countArtigo();

    @Query("SELECT COUNT(p) FROM Producao p WHERE p.tipoProducao = 'LIVRO'")
    long countLivro();
}
