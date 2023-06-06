package org.prog.lattes.controller;

import org.prog.lattes.model.Instituto;
import org.prog.lattes.model.Pesquisador;
import org.prog.lattes.service.PesquisadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/pesquisador")
public class PesquisadorController {

    @Autowired
    private PesquisadorService pesquisadorService;

    public PesquisadorController(PesquisadorService pesquisadorService) {
        this.pesquisadorService = pesquisadorService;
    }

    @GetMapping
    public Page<Pesquisador> buscarComFiltroDinamico(
            @RequestParam(required = false) String identificador,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Long instituto,
            Pageable pageable) {        
        return pesquisadorService.buscarComFiltroDinamico(identificador, nome, instituto, pageable);
    }

    @GetMapping("/count")
    public long countPesquisador() {
        return pesquisadorService.countPesquisador();
    }

    @PostMapping("/add/{identificador}/instituto/{instituto}")
    public void addPesquisador(@PathVariable("identificador") String identificador, @PathVariable("instituto") Instituto instituto) throws Exception {
        pesquisadorService.addPesquisador(identificador, instituto);
    }

    @DeleteMapping("/excluir/{identificador}")
    public void excluir(@PathVariable("identificador") String identificador) throws Exception {
        pesquisadorService.excluir(identificador);
    }
}