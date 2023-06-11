package org.prog.lattes.controller;

import org.prog.lattes.service.ProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.prog.lattes.model.Producao;
import org.prog.lattes.model.TotalProducoesAno;
import org.prog.lattes.model.TotalProducoesTipo;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/producao")
public class ProducaoController {

    @Autowired
    private ProducaoService producaoService;

    public ProducaoController(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    // @GetMapping
    // public Page<Producao> pageProducao(Pageable pageable) {
    //     return producaoService.pageProducao(pageable);
    // }

    @GetMapping("/")
    public Page<Producao> pageProducaoPeloAno(@RequestParam(required = true) Integer ano, Pageable pageable) {
        return producaoService.pageProducaoPeloAno(ano, pageable);
    }

    @GetMapping
    public Page<Producao> buscarComFiltroDinamico(
            @RequestParam(required = false) Integer dataInicio,
            @RequestParam(required = false) Integer dataFim,
            @RequestParam(required = false) String instituto,
            @RequestParam(required = false) String pesquisador,
            @RequestParam(required = false) String tipoProducao, 
            Pageable pageable) {
        return producaoService.buscarComFiltroDinamico(dataInicio, dataFim, instituto, pesquisador, tipoProducao, pageable);
    }

    @GetMapping("/countTotalProducoesPorAno")
    public List<TotalProducoesAno> countTotalProducoesPorAno(
            @RequestParam(required = false) Integer dataInicio,
            @RequestParam(required = false) Integer dataFim,
            @RequestParam(required = false) String instituto,
            @RequestParam(required = false) String pesquisador,
            @RequestParam(required = false) String tipoProducao){
        return producaoService.countTotalProducoesPorAno(dataInicio, dataFim, instituto, pesquisador, tipoProducao);
    }

     @GetMapping("/count")
    public long countProducao() {
        return producaoService.countProducao();
    }
    
    @GetMapping("/countTotalProducoesPorTipo")
    public List<TotalProducoesTipo> countTotalProducoesPorTipo() {
        return producaoService.countTotalProducoesPorTipo();
    }
}