package org.prog.lattes.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.prog.lattes.controller.ProducaoController;
import org.prog.lattes.service.PesquisadorService;
import org.springframework.beans.factory.annotation.Autowired;
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
                    producao.adicionarPesquisador(pesquisadorList);
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
                    producao.adicionarPesquisador(pesquisadorList);
                    artigoList.add(producao);
                    
                    
                }
            }
            allList.addAll(artigoList);
            producaoController.saveAll(allList);
            
        

            for(int i=0; i< nodelist.getLength(); i++){
                pesquisador = new Pesquisador();
                Node node = nodelist.item(0);
                Node parent = node.getParentNode();   
             
                
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
                    pesquisador.adicionarProducao(livroList);
                    pesquisador.adicionarProducao(artigoList);
                    pesquisadorList.add(pesquisador);
                    
                }

            pesquisadorService.saveAll(pesquisadorList);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}