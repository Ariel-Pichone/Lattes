
package org.prog.lattes.controller;

import java.util.List;
import org.prog.lattes.model.Instituto;
import org.prog.lattes.service.InstitutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instituto")
public class InstitutoController {
    
    @Autowired
    private InstitutoService institutoService;

    public InstitutoController(InstitutoService institutoService) {
        this.institutoService = institutoService;
    }

    @GetMapping("/")
    public List<Instituto> listInstituto(){
        return institutoService.listInstituto();
    }
    
    @GetMapping
    public Page<Instituto> pageInstituto(Pageable pageable) {
        return institutoService.pageInstituto(pageable);
    }

    // @GetMapping
    // public List<Instituto> listInstitutos(Pageable pageable) {
    //     return institutoService.findAll(pageable).getContent();
    // }

    @GetMapping("/nome/{nome}")
    public List<Instituto> listInstitutoPeloNome(@PathVariable("nome") String nome) {
        return institutoService.listInstitutoPeloNome(nome);
    }

    @GetMapping("/acronimo/{acronimo}")
    public List<Instituto> listInstitutoPeloAcronimo(@PathVariable("acronimo") String acronimo) {
        return institutoService.listInstitutoPeloAcronimo(acronimo);
    }

    @GetMapping("/pesquisar/{texto}")
    public List<Instituto> listInstitutoQualquerCampo(@PathVariable("texto") String texto) {
        return institutoService.listInstitutoQualquerCampo(texto);
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