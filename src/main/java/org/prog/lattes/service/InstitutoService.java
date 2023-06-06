package org.prog.lattes.service;

import org.prog.lattes.model.Instituto;
import org.prog.lattes.repository.InstitutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class InstitutoService {
    
    public InstitutoRepository institutoRepository;
    
    public InstitutoService(InstitutoRepository institutoRepository){
        this.institutoRepository = institutoRepository;
    }
    
    public Page<Instituto> buscarComFiltroDinamico(String nome, String acronimo, Pageable pageable) {
        Specification<Instituto> spec = Specification.where(null);
        
        if (nome != null) {
            spec = spec.and(InstitutoRepository.filtrarPorNome(nome));
        }
        
        if (acronimo != null) {
            spec = spec.and(InstitutoRepository.filtrarPorAcronimo(acronimo));
        }
    
        return institutoRepository.findAll(spec, pageable);
    }

    public Page<Instituto> buscarPorNomeOuAcronimo(String string, Pageable pageable) {
        Specification<Instituto> spec = Specification.where(null);
        
        if (string != null) {
            spec = spec.or(InstitutoRepository.filtrarPorNome(string));
            spec = spec.or(InstitutoRepository.filtrarPorAcronimo(string));
        }

        return institutoRepository.findAll(spec, pageable);
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