package org.prog.lattes.controller;

import java.util.List;

import org.prog.lattes.model.Autor;
import org.prog.lattes.repository.AutorRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/autor")
public class AutorController {
    
    private static AutorRepository autorRepository;

    public AutorController(AutorRepository autorRepository) {
        AutorController.autorRepository = autorRepository;
    }

    @GetMapping("/autorExiste/{nome}")
    public static boolean autorExiste(@PathVariable("nome") String nome) {
        return autorRepository.autorExiste(nome);
    }

    @GetMapping("/nome/{nome}")
    public List<Autor> getAutorPeloNome(@PathVariable("nome") String nome) {
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }
}
