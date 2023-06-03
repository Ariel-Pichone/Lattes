package org.prog.lattes.service;


import java.util.List;

import org.prog.lattes.model.Autor;
import org.prog.lattes.repository.AutorRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AutorService {
    
    public AutorRepository autorRepository;
    
    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public boolean autorExiste(String nome) {
        return autorRepository.autorExiste(nome);
    }

    public List<Autor> listAutorPeloNome(String nome) {
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }





}
