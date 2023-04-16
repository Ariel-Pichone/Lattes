package org.prog.lattes.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.prog.lattes.service.PesquisadorService;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class converts the information from an XML file to a list of Pesquisador objects
 */
@Component
public class PesquisadorXml {

    private final PesquisadorService pesquisadorService;

    public PesquisadorXml(PesquisadorService pesquisadorService) {
        this.pesquisadorService = pesquisadorService;
    }

    /**
     * Converts the information from an XML file to a list of Pesquisador objects
     *
     * @param file      the input XML file
     * @param instituto the instituto to associate with the Pesquisador objects
     */
    public void convert(File file, Instituto instituto) {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(file);

            doc.getDocumentElement().normalize();            
            NodeList nodeList = doc.getElementsByTagName("DADOS-GERAIS");
            List<Pesquisador> pesquisadorList = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Node parent = node.getParentNode();
                Pesquisador pesquisador = new Pesquisador();

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String nome = element.getAttribute("NOME-COMPLETO");
                    String ufNascimento = element.getAttribute("UF-NASCIMENTO");
                    // Cast parent node to Element before calling getAttribute()
                    Element parentElement = (Element) parent;
                    String identificador = parentElement.getAttribute("NUMERO-IDENTIFICADOR");

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