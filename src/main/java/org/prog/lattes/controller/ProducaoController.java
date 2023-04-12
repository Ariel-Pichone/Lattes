package org.prog.lattes.controller;

import org.prog.lattes.repository.ProducaoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.prog.lattes.model.Producao;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.OverridesAttribute.List;

@RestController
@CrossOrigin
@RequestMapping("/producao")
public class ProducaoController {

    private final ProducaoRepository producaoRepository;

    public ProducaoController(ProducaoRepository producaoRepository){
        this.producaoRepository = producaoRepository;
    }

    // @GetMapping("/")
    // public List<Producao> getProducoes(){
    //     return 
    // }


    
}
