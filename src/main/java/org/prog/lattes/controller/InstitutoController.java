package org.prog.lattes.controller;
import java.util.List;

import org.prog.lattes.model.Instituto;
import org.prog.lattes.repository.InstitutoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/instituto")
public class InstitutoController {
    private final InstitutoRepository institutoRepository;

    public InstitutoController(InstitutoRepository institutoRepository) {
        this.institutoRepository = institutoRepository;
    }

    @GetMapping("/")
    public List<Instituto> getInstitutos(){
        return institutoRepository.findAll();
    }

    @PostMapping("/")
    public void gravar(@RequestBody Instituto instituto){
        institutoRepository.save(instituto);
    }

    @GetMapping("/{id}")
    public void remover(@PathVariable("id") Long id) throws Exception{
        var i = institutoRepository.findById(id);

        if (i.isPresent()) {
            Instituto instituto = i.get();
            institutoRepository.delete(instituto);
        } else {
            throw new Exception("Id não encontrado");
        }
    }

}
