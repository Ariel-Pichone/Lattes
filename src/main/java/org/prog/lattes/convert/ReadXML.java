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

    public void start(String identificador, Instituto instituto) throws Exception{

        System.out.println("Starting reading xml");

        File diretorio = new File("src\\main\\java\\org\\prog\\lattes\\files");

        File file = new File(diretorio, identificador + ".xml");

        if(file.isFile()) {
            pesquisadorXml.convert(file, instituto);
        } else {
            throw new Exception("Pesquisador não encontrado.");
        }
    }
}
