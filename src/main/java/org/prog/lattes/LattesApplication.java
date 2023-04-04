package org.prog.lattes;

import org.prog.lattes.convert.ReadXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LattesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LattesApplication.class, args);
	}
}
