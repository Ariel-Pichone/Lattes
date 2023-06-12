package org.prog.lattes.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.jvnet.hk2.annotations.Service;
import org.prog.lattes.model.TipoProducao;
import org.prog.lattes.model.TipoVertice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Service
@Component
public class TipoProducaoService {
    
    public List<String> listTipoProducao(){
        List<String> tiposProducoes = Arrays.stream(TipoProducao.values()).map(TipoProducao::getNome).collect(Collectors.toList());

        Collections.sort(tiposProducoes);
        
        return tiposProducoes;
    }
}