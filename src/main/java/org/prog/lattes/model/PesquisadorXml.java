package org.prog.lattes.model;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import jakarta.validation.constraints.Null;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.prog.lattes.service.PesquisadorService;
import org.springframework.stereotype.Component;

@Component  //Carregar o xml, ler o xml, alimentar um objeto de pesquisador
public class PesquisadorXml {

    private final PesquisadorService pesquisadorService;

    
    public PesquisadorXml(PesquisadorService pesquisadorService){
        this.pesquisadorService = pesquisadorService;
    }

    public void convert(File file, Instituto instituto) {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();//DocumentBuilderFactory é usada para criar uma instância de DocumentBuilder
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();//o DocumentBuilder é usado para criar um novo documento e adicionar elementos a ele.     
            Document doc = documentBuilder.parse(file);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter stringWriter = new StringWriter();

            transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));

            doc.getDocumentElement().normalize();

            NodeList nodelist = doc.getElementsByTagName("DADOS-GERAIS");
            List<Pesquisador> pesquisadorList = new ArrayList<>();
            Pesquisador pesquisador;
        

            for(int i=0; i< nodelist.getLength(); i++){
                pesquisador = new Pesquisador();
                Node node = nodelist.item(0);

                Node parent = node.getParentNode();   
             
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    Element element2 = (Element) parent;

                    String nome = element.getAttribute("NOME-COMPLETO");
                    String ufNascimento = element.getAttribute("UF-NASCIMENTO");
                    String identificador = element2.getAttribute("NUMERO-IDENTIFICADOR");


                    pesquisador.setNome(nome);
                    pesquisador.setUfNascimento(ufNascimento);
                    pesquisador.setIdentificador(identificador);
                    pesquisador.setInstituto(instituto);
                    
                    pesquisadorList.add(pesquisador);
                }
            }

            pesquisadorService.saveAll(pesquisadorList);
        

        } catch (Exception e) {
           e.printStackTrace();
        }

      
    } 
    
}