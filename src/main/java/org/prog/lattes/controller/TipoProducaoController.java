package org.prog.lattes.controller;

import org.prog.lattes.model.TipoProducao;
import org.prog.lattes.service.TipoProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/tipoProducao")
public class TipoProducaoController {
    
    @Autowired
    private TipoProducaoService tipoProducaoService;

    public TipoProducaoController(TipoProducaoService tipoProducaoService) {
        this.tipoProducaoService = tipoProducaoService;
    }

    @GetMapping
    public Page<String> pageTipoProducao(Pageable pageable) {
        return tipoProducaoService.pageTipoProducao(pageable);
    }
}