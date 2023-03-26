package org.prog.lattes.convert;

import java.io.File;

import org.prog.lattes.model.PesquisadorXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReadXML{

    @Autowired
    private PesquisadorXml pesquisadorXml;

    public void start(){

        System.out.println("Starting reading xml");
        File diretorio = new File("C:\\Users\\jvito\\OneDrive\\Documentos\\José Vitor\\2023.1\\DS1\\Lattes\\src\\main\\java\\org\\prog\\lattes\\files");
        File[] files;
        files = diretorio.listFiles((dir, name)-> name.endsWith(".xml"));
        assert files != null;

        int count = 0;

        for(File file : files){
         
            count++;

            if(file.isFile()){
                pesquisadorXml.convert(file);
            }
        
        }

        System.out.println("Temos " + count + " arquivos no diretorio");

    }

}
