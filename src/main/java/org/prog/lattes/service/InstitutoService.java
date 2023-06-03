package org.prog.lattes.service;

import java.util.List;
import org.prog.lattes.model.Instituto;
import org.prog.lattes.repository.InstitutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class InstitutoService {
    
    public InstitutoRepository institutoRepository;
    
    public InstitutoService(InstitutoRepository institutoRepository){
        this.institutoRepository = institutoRepository;
    }
    
    public List<Instituto> listInstituto(){
        return this.institutoRepository.findAll();
    }

    public Page<Instituto> pageInstituto(Pageable pageable){
        // Sort sort = Sort.by("nome").ascending();
        // pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return this.institutoRepository.findAll(pageable);
    }

    public List<Instituto> listInstitutoPeloNome(String nome) {
        return institutoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Instituto> listInstitutoPeloAcronimo(String acronimo) {
        return institutoRepository.findByAcronimoContainingIgnoreCase(acronimo);
    }

    public List<Instituto> listInstitutoQualquerCampo(String texto) {
        return institutoRepository.findByNomeContainingIgnoreCaseOrAcronimoContainingIgnoreCase(texto, texto);
    }

    public long countInstituto() {
        return institutoRepository.count();
    }

    public void gravar(Instituto instituto) {
        institutoRepository.save(instituto);
    }

    public void remover(Long id) throws Exception {
        var i = institutoRepository.findById(id);

        if (i.isPresent()) {
            Instituto instituto = i.get();
            institutoRepository.delete(instituto);
        } else {
            throw new Exception("Id não encontrado");
        }
    }
}
