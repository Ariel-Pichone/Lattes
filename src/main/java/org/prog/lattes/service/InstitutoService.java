package org.prog.lattes.service;

import java.util.Collections;
import java.util.List;

import org.prog.lattes.model.Instituto;
import org.prog.lattes.model.Pesquisador;
import org.prog.lattes.repository.InstitutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    
    public Specification<Instituto> querySpecification(String nome, String acronimo, String nomeAcronimo){
        Specification<Instituto> spec = Specification.where(null);
        
        if (nome != null) {
            spec = spec.and(InstitutoRepository.filtrarPorNome(nome));
        }
        
        if (acronimo != null) {
            spec = spec.and(InstitutoRepository.filtrarPorAcronimo(acronimo));
        }

        if (nomeAcronimo != null) {
            spec = spec.or(InstitutoRepository.filtrarPorNome(nomeAcronimo));
            spec = spec.or(InstitutoRepository.filtrarPorAcronimo(nomeAcronimo));
        }

        return spec;
    }

    public Page<Instituto> buscarComFiltroDinamico(String nome, String acronimo, String nomeAcronimo, Pageable pageable) {
        //Usado para ordenar a pagina pelo nome do pesquisador de forma crescente
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("nome"));

        Specification<Instituto> spec = querySpecification(nome, acronimo, nomeAcronimo);

        return institutoRepository.findAll(spec, pageRequest);
    }

    public List<Instituto> listBuscarComFiltroDinamico(String nome, String acronimo, String nomeAcronimo) {
        Specification<Instituto> spec = querySpecification(nome, acronimo, nomeAcronimo);

        List<Instituto> listInstituto = institutoRepository.findAll(spec);

        Collections.sort(listInstituto, (p1, p2) -> p1.getNome().compareTo(p2.getNome()));
        
        return listInstituto;
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