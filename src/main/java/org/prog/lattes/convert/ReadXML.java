package org.prog.lattes.convert;

import java.io.File;
import org.prog.lattes.model.Instituto;
import org.prog.lattes.model.LeituraXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadXML{

    @Autowired
    private LeituraXml pesquisadorXml;

    public void start(String identificador, Instituto instituto) throws Exception{

        File diretorio = new File("src\\main\\java\\org\\prog\\lattes\\files");

        File file = new File(diretorio, identificador + ".xml");

        if(file.isFile()) {
            pesquisadorXml.convert(file, instituto);
        } else {
            throw new Exception("Pesquisador não encontrado.");
        }
    }
}