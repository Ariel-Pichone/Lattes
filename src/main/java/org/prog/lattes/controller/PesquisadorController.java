package org.prog.lattes.controller;

import java.util.List;
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

    @GetMapping("/")
    public List<Pesquisador> listPesquisador(){
        return pesquisadorService.listPesquisador();
    }

    @GetMapping
    public Page<Pesquisador> pagePesquisador(Pageable pageable) {
        return pesquisadorService.pagePesquisador(pageable);
    }

    @GetMapping("/identificador/{identificador}")
    public List<Pesquisador> listPesquisadorPeloIdentificador(@PathVariable("identificador") String identificador){
        return pesquisadorService.listPesquisadorPeloIdentificador(identificador);
    }

    @GetMapping("/nome/{nome}")
    public List<Pesquisador> listPesquisadorPeloNome(@PathVariable("nome") String nome){
        return pesquisadorService.listPesquisadorPeloNome(nome);
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