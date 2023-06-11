package org.prog.lattes.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.jvnet.hk2.annotations.Service;
import org.prog.lattes.model.TipoProducao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Service
@Component
public class TipoProducaoService {
    
    public Page<String> pageTipoProducao(Pageable pageable){
        //Usado para ordenar a pagina pelo nome da produção de forma crescente
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("nome"));
        
        List<String> tipoProducoes = Arrays.stream(TipoProducao.values())
                                        .map(TipoProducao::getNome)
                                        .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), tipoProducoes.size());

        List<String> tipoProducoesPaginados = tipoProducoes.subList(start, end);
        Page<String> page = new PageImpl<>(tipoProducoesPaginados, pageRequest, tipoProducoes.size());

        return page;
    }
}