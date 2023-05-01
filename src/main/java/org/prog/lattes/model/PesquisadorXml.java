package org.prog.lattes.model;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.prog.lattes.controller.ProducaoController;
import org.prog.lattes.service.PesquisadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component  //Carregar o xml, ler o xml, alimentar um objeto de pesquisador
public class PesquisadorXml {

    @Autowired
    private PesquisadorService pesquisadorService;

    @Autowired
    private ProducaoController producaoController;

    public PesquisadorXml(){

    }
    
    public PesquisadorXml(PesquisadorService pesquisadorService){
        this.pesquisadorService = pesquisadorService;
    }

    public PesquisadorXml(ProducaoController producaoController){
        this.producaoController = producaoController;
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
            NodeList nodeList2 = doc.getElementsByTagName("DADOS-BASICOS-DO-ARTIGO");
            NodeList nodeList3 = doc.getElementsByTagName("DADOS-BASICOS-DO-LIVRO");

            List<Pesquisador> pesquisadorList = new ArrayList<>();
            List<Producao> artigoList = new ArrayList<>();
            List<Producao> livroList = new ArrayList<>();
            List<Producao> allList = new ArrayList<>();
            
            Pesquisador pesquisador;
            Producao producao;
      
            for(int k = 0; k< nodeList3.getLength(); k++){
                producao = new Producao();

                Tipo tipoProducao = Tipo.LIVRO;
                Node node = nodeList3.item(k);

                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    String nomeLivro = element.getAttribute("TITULO-DO-LIVRO");
                    String anoLivro = element.getAttribute("ANO");

                    producao.setAno(anoLivro);
                    producao.setNome(nomeLivro);
                    producao.setTipoProducao(tipoProducao);
                    producao.addPesquisador(pesquisadorList);
                    livroList.add(producao);
                }
            }

            allList.addAll(livroList);
            
            for(int j = 0; j< nodeList2.getLength(); j++){
                producao = new Producao();
                Tipo tipoproducao = Tipo.ARTIGO;
                Node node = nodeList2.item(j);
            
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    
                    String tituloProducao = element.getAttribute("TITULO-DO-ARTIGO");
                    String anoArtigo = element.getAttribute("ANO-DO-ARTIGO");
                    
                    producao.setNome(tituloProducao);
                    producao.setAno(anoArtigo);
                    producao.setTipoProducao(tipoproducao);
                    producao.addPesquisador(pesquisadorList);
                    artigoList.add(producao);
                    
                    
                }
            }
            allList.addAll(artigoList);
            producaoController.saveAll(allList);
                
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
                    pesquisador.addProducao(livroList);
                    pesquisador.addProducao(artigoList);
                    pesquisadorList.add(pesquisador);
                }
            }

            pesquisadorService.saveAll(pesquisadorList);
        
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}