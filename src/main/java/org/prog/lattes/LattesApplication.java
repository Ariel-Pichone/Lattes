package org.prog.lattes;

import org.prog.lattes.convert.ReadXML;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LattesApplication {

	private static ReadXML readXML = new ReadXML();

	public LattesApplication(ReadXML readXML){ //Construtor, Injeção de dependencia 
		LattesApplication.readXML = readXML;
	}

	public static void main(String[] args) {
		SpringApplication.run(LattesApplication.class, args);
		readXML.start();
	}

}
