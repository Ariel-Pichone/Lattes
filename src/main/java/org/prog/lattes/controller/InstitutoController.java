
package org.prog.lattes.controller;

import java.util.List;
import org.prog.lattes.model.Instituto;
import org.prog.lattes.repository.InstitutoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.ws.rs.Path;

@RestController
@CrossOrigin
@RequestMapping("/instituto")
public class InstitutoController {
    
    private final InstitutoRepository institutoRepository;

    public InstitutoController(InstitutoRepository institutoRepository) {
        this.institutoRepository = institutoRepository;
    }

    @GetMapping("/")
    public List<Instituto> getInstitutos() {
        return institutoRepository.findAll();
    }

    @PostMapping("/")
    public void gravar(@RequestBody Instituto instituto) {
        institutoRepository.save(instituto);
    }

    @GetMapping("/nome/{nome}")
    public List<Instituto> getInstitutoPeloNome(@PathVariable("nome") String nome) {
        return institutoRepository.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/acronimo/{acronimo}")
    public List<Instituto> getInstitutoPeloAcronimo(@PathVariable("acronimo") String acronimo) {
        return institutoRepository.findByAcronimoContainingIgnoreCase(acronimo);
    }

    @GetMapping("/pesquisar/{texto}")
    public List<Instituto> GetInstitutoQualquerCampo(@PathVariable("texto") String texto) {
        return institutoRepository.findByNomeContainingIgnoreCaseOrAcronimoContainingIgnoreCase(texto, texto);
    }

    @GetMapping("/{id}")
    public void remover(@PathVariable("id") Long id) throws Exception {
        var i = institutoRepository.findById(id);

        if (i.isPresent()) {
            Instituto instituto = i.get();
            institutoRepository.delete(instituto);
        } else {
            throw new Exception("Id não encontrado");
        }
    }
}