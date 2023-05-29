package org.prog.lattes.controller;

import org.prog.lattes.repository.ProducaoRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.prog.lattes.model.Producao;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/producao")
public class ProducaoController {

    private final ProducaoRepository producaoRepository;

    public ProducaoController(ProducaoRepository producaoRepository){
        this.producaoRepository = producaoRepository;
    }

    public void saveAll(List<Producao> producaoList){
        producaoRepository.saveAll(producaoList);
    }

    @GetMapping("/")
    public List<Producao> getProducoes(){
        return producaoRepository.findAll();
    }   
    
    @GetMapping("/ano/{ano}")
    public List<Producao> getProducaoPeloAno(@PathVariable("ano") Integer ano) {
        return producaoRepository.findByAno(ano);
    }

    @GetMapping("/filtro")
    public List<Producao> buscarComFiltroDinamico(
            @RequestParam(required = false) Integer dataInicio,
            @RequestParam(required = false) Integer dataFim,
            @RequestParam(required = false) String instituto,
            @RequestParam(required = false) String pesquisador,
            @RequestParam(required = false) String tipoProducao) {
        
        Specification<Producao> spec = Specification.where(null);

        if (dataInicio != null) {
            spec = spec.and(ProducaoRepository.filtrarPorDataInicio(dataInicio));
        }
        
        if (dataFim != null) {
            spec = spec.and(ProducaoRepository.filtrarPorDataFim(dataFim));
        }

        if (instituto != null) {
            spec = spec.and(ProducaoRepository.filtrarPorInstituto(instituto));
        }

        if (pesquisador != null) {
            spec = spec.and(ProducaoRepository.filtrarPorPesquisador(pesquisador));
        }

        if (tipoProducao != null) {
            spec = spec.and(ProducaoRepository.filtrarPorTipoProducao(tipoProducao));
        }

        return producaoRepository.findAll(spec);
    }

    @GetMapping("/count")
    public long count() {
        return producaoRepository.count();
    }

    @GetMapping("/count/ano/{ano}")
    public long countProducaoPorAno(@PathVariable int ano) {
        return producaoRepository.countProducaoPorAno(ano);
    }

    @GetMapping("/countArtigo")
    public long countArtigo() {
        return producaoRepository.countArtigo();
    }

    @GetMapping("/countLivro")
    public long countLivro() {
        return producaoRepository.countLivro();
    }
}