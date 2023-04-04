package org.prog.lattes.service;

import java.io.Console;
import java.util.List;

import org.prog.lattes.convert.ReadXML;
import org.prog.lattes.model.Instituto;
import org.prog.lattes.model.Pesquisador;
import org.prog.lattes.repository.PesquisadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pesquisador")
@Service
public class PesquisadorService {

    @Autowired
    private static ReadXML readXML = new ReadXML();

	public PesquisadorService(ReadXML readXML){ //Construtor, Injeção de dependencia 
	    PesquisadorService.readXML = readXML;
	}

    @Autowired
    PesquisadorRepository pesquisadorRepository;

    @GetMapping("/")
    public List<Pesquisador> getPesquisadores(){
        return pesquisadorRepository.findAll();
    }


    @GetMapping("/add/{identificador}/instituto/{instituto}")
    public void addPesquisador(@PathVariable("identificador") String identificador, @PathVariable("instituto") Instituto instituto){
        readXML.start(identificador, instituto);
    }

    @GetMapping("/{identificador}")
    public List<Pesquisador> getPesquisador(@PathVariable("identificador") String identificador){
        return pesquisadorRepository.findByIdentificador(identificador);
    }

    @GetMapping("/excluir/{id}")
    public void remover(@PathVariable("id") Long id) throws Exception{
        var i = pesquisadorRepository.findById(id);

        if (i.isPresent()) {
            Pesquisador pesquisador = i.get();
            pesquisadorRepository.delete(pesquisador);
        } else {
            throw new Exception("Id não encontrado");
        }
    }

    public void saveAll(List<Pesquisador> pesquisadorList) {
        pesquisadorRepository.saveAll(pesquisadorList);
    }


}
