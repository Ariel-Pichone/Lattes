package org.prog.lattes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.prog.lattes.model.Instituto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LattesApplication {

	/*public void carregarDados(){
		
		
		List<Instituto> Institutos = new ArrayList<>();

		Institutos.add(new Instituto("xxx", "yyy"));


		readXML.start(identificador, instituto);

		
		/*nomeInstituto.add("Faculdade Professor Miguel Ângelo da Silva Santos");
		nomeInstituto.add("Universidade Federal do Rio de Janeiro");
		nomeInstituto.add("Universidade Federal Fluminense");
		nomeInstituto.add("Faculdade Católica Salesiana");
		nomeInstituto.add("Universidade Estácio de Sá");
		nomeInstituto.add("Teste");

		acronimoInstituto.add("FEMASS");
		acronimoInstituto.add("UFRJ");
		acronimoInstituto.add("UFF");
		acronimoInstituto.add("SALESIANA");
		acronimoInstituto.add("ESTÁCIO");
		acronimoInstituto.add("TESTE");
		
		for (List<String> instituto : ) {
			
		}





		Instituto i = new Instituto("xxx", "yyy");
		
		

		i.setNome("Faculdade Professor Miguel Ângelo da Silva Santos");
		i.setAcronimo("FEMASS");


	}
*/
	public static void main(String[] args) {
		SpringApplication.run(LattesApplication.class, args);

		//carregarDados();
	}
}
