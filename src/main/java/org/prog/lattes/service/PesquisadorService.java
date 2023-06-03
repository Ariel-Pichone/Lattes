package org.prog.lattes.service;

import java.util.List;
import org.prog.lattes.convert.ReadXML;
import org.prog.lattes.model.Instituto;
import org.prog.lattes.model.Pesquisador;
import org.prog.lattes.repository.PesquisadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PesquisadorService {

    private static ReadXML readXML;

    @Autowired
    PesquisadorRepository pesquisadorRepository;

    @Autowired
    public void setReadXML(ReadXML readXML) {
        PesquisadorService.readXML = readXML;
    }

    public PesquisadorService(ReadXML readXML){ //Construtor, Injeção de dependencia 
	    PesquisadorService.readXML = readXML;
	}

    @Autowired
    public PesquisadorService(PesquisadorRepository pesquisadorRepository){
        this.pesquisadorRepository = pesquisadorRepository;
    }

    public List<Pesquisador> listPesquisador(){
        return pesquisadorRepository.findAll();
    }

    public Page<Pesquisador> pagePesquisador(Pageable pageable){
        return this.pesquisadorRepository.findAll(pageable);
    }  

    public List<Pesquisador> listPesquisadorPeloIdentificador(String identificador){
        return pesquisadorRepository.findByIdentificador(identificador);
    }

    public List<Pesquisador> listPesquisadorPeloNome(String nome){
        return pesquisadorRepository.findByNomeContainingIgnoreCase(nome);
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
