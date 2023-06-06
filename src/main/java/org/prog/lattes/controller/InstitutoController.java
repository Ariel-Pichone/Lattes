package org.prog.lattes.controller;

import org.prog.lattes.model.Instituto;
import org.prog.lattes.service.InstitutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/instituto")
public class InstitutoController {
    
    @Autowired
    private InstitutoService institutoService;

    public InstitutoController(InstitutoService institutoService) {
        this.institutoService = institutoService;
    }

    @GetMapping
    public Page<Instituto> buscarComFiltroDinamico(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String acronimo,
            Pageable pageable) {        
        return institutoService.buscarComFiltroDinamico(nome, acronimo, pageable);
    }

    @GetMapping("/nomeacronimo")
    public Page<Instituto> buscarPorNomeEAcronimo(
            @RequestParam(required = false) String string,
            Pageable pageable) {        
        return institutoService.buscarPorNomeOuAcronimo(string, pageable);
    }

    @GetMapping("/count")
    public long countInstituto() {
        return institutoService.countInstituto();
    }
    
    @PostMapping("/")
    public void gravar(@RequestBody Instituto instituto) {
        institutoService.gravar(instituto);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable("id") Long id) throws Exception {
        institutoService.remover(id);
    }
}