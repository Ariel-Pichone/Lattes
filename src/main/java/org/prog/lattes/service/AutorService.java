package org.prog.lattes.service;

import java.util.List;
import org.prog.lattes.model.Autor;
import org.prog.lattes.model.Producao;
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

    public Autor buscarAutorNoBanco(String nome) {
        List<Autor> autoresCadastrados = autorRepository.findAll();

        for (Autor autorCadastrado : autoresCadastrados) {
            if (autorCadastrado.getNome().equals(nome)) {
                return autorCadastrado;
            }
        }
        return null;
    }

    public Autor buscarAutorNaLista(String nome, List<Producao> producoesList) {
        if(producoesList == null) return null;
        
        for (Producao producaoCadastrada : producoesList) {
            List<Autor> autoresList = producaoCadastrada.getAutores();

            if(autoresList == null) return null;
            
            for (Autor autorCadastrado : autoresList) {
                if (autorCadastrado.getNome().equals(nome)) {
                    return autorCadastrado;
                }
            }
        }
        return null;
    }

    public List<Autor> listAutorPeloNome(String nome) {
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }
}