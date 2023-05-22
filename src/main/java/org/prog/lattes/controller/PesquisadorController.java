package org.prog.lattes.controller;

import org.prog.lattes.repository.PesquisadorRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/pesquisador")
public class PesquisadorController {
    
    private final PesquisadorRepository pesquisadorRepository;

    public PesquisadorController(PesquisadorRepository pesquisadorRepository) {
        this.pesquisadorRepository = pesquisadorRepository;
    }

    @GetMapping("/count")
    public long count() {
        return pesquisadorRepository.count();
    }
}
