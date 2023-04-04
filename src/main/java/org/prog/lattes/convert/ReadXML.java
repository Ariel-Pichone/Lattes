package org.prog.lattes.convert;

import java.io.File;

import org.prog.lattes.model.Instituto;
import org.prog.lattes.model.PesquisadorXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.OverridesAttribute.List;


@Component
public class ReadXML{

    @Autowired
    private PesquisadorXml pesquisadorXml;

    public void start(String identificador, Instituto instituto){

        System.out.println("Starting reading xml");

        File diretorio = new File("C:\\Users\\jvito\\OneDrive\\Documentos\\José Vitor\\2023.1\\DS1\\Lattes\\src\\main\\java\\org\\prog\\lattes\\files");

        File[] files = diretorio.listFiles();
        assert files != null;

        int count = 0;

        for(File file : files){
            
            count++;

            if(file.isFile() && file.getName().equalsIgnoreCase(identificador + ".xml")){
                System.out.println("O nome do arquivo é " + file.getName());
                pesquisadorXml.convert(file, instituto);
            }
        }

        System.out.println("Temos " + count + " arquivos no diretorio");

    }

}
