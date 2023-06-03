package org.prog.lattes.controller;

import java.util.List;
import org.prog.lattes.model.Autor;
import org.prog.lattes.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
public class AutorController {
    
    @Autowired
    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/autorExiste/{nome}")
    public boolean autorExiste(@PathVariable("nome") String nome) {
        return autorService.autorExiste(nome);
    }

    @GetMapping("/nome/{nome}")
    public List<Autor> listAutorPeloNome(@PathVariable("nome") String nome) {
        return autorService.listAutorPeloNome(nome);
    }

}
