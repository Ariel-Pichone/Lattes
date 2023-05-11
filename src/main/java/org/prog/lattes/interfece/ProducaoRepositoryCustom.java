package org.prog.lattes.interfece;

import java.util.List;
import org.prog.lattes.model.Producao;

public interface ProducaoRepositoryCustom {
    
    List<Producao> findProducaoByAnoInicioAndAnoFimAndInstitutoAndPesquisadorAndTipoProducao(
        Integer anoInicio,
        Integer anoFim,
        String instituto,
        String pesquisador,
        String tipoProducao
    );
}
