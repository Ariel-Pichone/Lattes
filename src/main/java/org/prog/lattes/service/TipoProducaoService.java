package org.prog.lattes.service;

import java.util.Arrays;
import java.util.List;
import org.jvnet.hk2.annotations.Service;
import org.prog.lattes.model.TipoProducao;
import org.springframework.stereotype.Component;

@Service
@Component
public class TipoProducaoService {
    
    public List<TipoProducao> listTipoProducao(){
        return Arrays.asList(TipoProducao.values());
    }
}