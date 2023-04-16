package org.prog.lattes.service;

import java.io.Console;
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

    @GetMapping("/{identificador}")
    public List<Pesquisador> getPesquisador(@PathVariable("identificador") String identificador){
        return pesquisadorRepository.findByIdentificador(identificador);
    }

    @GetMapping("/excluir/{identificador}")
    public void remover(@PathVariable("identificador") Long identificador) throws Exception{
        var i = pesquisadorRepository.findById(identificador);

        if (i.isPresent()) {
            Pesquisador pesquisador = i.get();
            pesquisadorRepository.delete(pesquisador);
        } else {
            throw new Exception("Identificador não encontrado");
        }
    }

    public void saveAll(List<Pesquisador> pesquisadorList) {
        pesquisadorRepository.saveAll(pesquisadorList);
    }
   

}
