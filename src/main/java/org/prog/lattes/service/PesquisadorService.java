package org.prog.lattes.service;

import java.util.List;
import org.prog.lattes.convert.ReadXML;
import org.prog.lattes.model.Instituto;
import org.prog.lattes.model.Pesquisador;
import org.prog.lattes.repository.PesquisadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/pesquisador")
@Service
public class PesquisadorService {

    @Autowired
    private static ReadXML readXML = new ReadXML();

    @Autowired
    PesquisadorRepository pesquisadorRepository;

	public PesquisadorService(ReadXML readXML){ //Construtor, Injeção de dependencia 
	    PesquisadorService.readXML = readXML;
	}

    @GetMapping("/")
    public List<Pesquisador> getPesquisadores(){
        return pesquisadorRepository.findAll();
    }

    @GetMapping("/add/{identificador}/instituto/{instituto}")
    public void addPesquisador(@PathVariable("identificador") String identificador, @PathVariable("instituto") Instituto instituto) throws Exception{        

        if(pesquisadorRepository.existsByIdentificador(identificador)){
            throw new Exception("Pesquisador já cadastrado no sistema");
        }else{
            readXML.start(identificador, instituto);
        }
    }

    @GetMapping("/identificador/{identificador}")
    public List<Pesquisador> getPesquisador(@PathVariable("identificador") String identificador){
        return pesquisadorRepository.findByIdentificador(identificador);
    }

    @GetMapping("/nome/{nome}")
    public List<Pesquisador> getPesquisadorNome(@PathVariable("nome") String nome){
        return pesquisadorRepository.findByNomeContainingIgnoreCase(nome);
    }

    @GetMapping("/excluir/{identificador}")
    public void excluir(@PathVariable("identificador") String identificador) throws Exception {
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
