package org.prog.lattes.service;

import java.util.List;
import org.prog.lattes.convert.ReadXML;
import org.prog.lattes.model.Instituto;
import org.prog.lattes.model.Pesquisador;
import org.prog.lattes.repository.PesquisadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PesquisadorService {

    private static ReadXML readXML;
    
    PesquisadorRepository pesquisadorRepository;

    @Autowired
    public void setReadXML(ReadXML readXML) {
        PesquisadorService.readXML = readXML;
    }

    public PesquisadorService(PesquisadorRepository pesquisadorRepository){
        this.pesquisadorRepository = pesquisadorRepository;
    }

    public Page<Pesquisador> buscarComFiltroDinamico(
            String identificador,
            String nome,
            Long instituto,
            Pageable pageable) {
        Specification<Pesquisador> spec = Specification.where(null);
        
        if (identificador != null) {
            spec = spec.and(PesquisadorRepository.filtrarPorIdentificador(identificador));
        }
        
        if (nome != null) {
            spec = spec.and(PesquisadorRepository.filtrarPorNome(nome));
        }
    
        if (instituto != null) {
            spec = spec.and(PesquisadorRepository.filtrarPorInstituto(instituto));
        }

        return pesquisadorRepository.findAll(spec, pageable);
    }

    public long countPesquisador() {
        return pesquisadorRepository.count();
    }

    public void addPesquisador(String identificador, Instituto instituto) throws Exception{
        if(pesquisadorRepository.existsByIdentificador(identificador)){
            throw new Exception("Pesquisador já cadastrado no sistema");
        }else{            
            readXML.start(identificador, instituto);
        }
    }

    public void excluir(String identificador) throws Exception {
        try {
            Pesquisador pesquisador = pesquisadorRepository.findByIdentificador(identificador).get(0);
            
            if (pesquisador != null) {
                pesquisadorRepository.delete(pesquisador);
                System.out.println("Pesquisador excluído com sucesso.");
            } else {
                System.out.println("Pesquisador não encontrado com o ID fornecido.");
            }
        } catch (Exception e) {
            System.out.println("Falha ao excluir o pesquisador. Erro: " + e.getMessage());
        }
    }
    
    public void saveAll(List<Pesquisador> pesquisadorList) {
        pesquisadorRepository.saveAll(pesquisadorList);
    }
}
