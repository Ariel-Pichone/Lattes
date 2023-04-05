/*package org.prog.lattes.controller;

import java.util.List;
import org.prog.lattes.model.Pesquisador;
import org.prog.lattes.repository.PesquisadorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.ws.rs.Path;

@RestController
@RequestMapping("/pesquisador")
public class PesquisadorController {
    
    private final PesquisadorRepository pesquisadorRepository;

    public PesquisadorController(PesquisadorRepository pesquisadorRepository) {
        this.pesquisadorRepository = pesquisadorRepository;
    }

    @GetMapping("/")
    public List<Pesquisador> getPesquisadores() {
        return pesquisadorRepository.findAll();
    }

    @PostMapping("/")
    public void gravar(@RequestBody Pesquisador pesquisador) {
        pesquisadorRepository.save(pesquisador);
    }

    @GetMapping("/nome/{nome}")
    public List<Pesquisador> getPesquisadorPeloNome(@PathVariable("nome") String nome) {
        return pesquisadorRepository.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/email/{email}")
    public List<Pesquisador> getPesquisadorPeloEmail(@PathVariable("email") String email) {
        return pesquisadorRepository.findByEmailContainingIgnoreCase(email);
    }

    @GetMapping("/pesquisar/{texto}")
    public List<Pesquisador> GetInstitutoQualquerCampo(@PathVariable("texto") String texto) {
        return pesquisadorRepository.findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(texto, texto);
    }

    @GetMapping("/{id}")
    public void remover(@PathVariable("id") Long id) throws Exception {
        var i = pesquisadorRepository.findById(id);

        if (i.isPresent()) {
            Pesquisador pesquisador = i.get();
            pesquisadorRepository.delete(pesquisador);
        } else {
            throw new Exception("Id não encontrado");
        }
    }
}
*/