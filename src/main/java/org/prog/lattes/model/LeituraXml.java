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
public class LeituraXml {

    @Autowired
    private PesquisadorService pesquisadorService;

    @Autowired
    private ProducaoController producaoController;

    public LeituraXml(){

    }
    
    public LeituraXml(PesquisadorService pesquisadorService){
        this.pesquisadorService = pesquisadorService;
    }

    public LeituraXml(ProducaoController producaoController){
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

            List<Pesquisador> pesquisadorList = new ArrayList<>();
            List<Producao> producaoList = new ArrayList<>();
                        
            /*********************************************************************************************************/
            /*                                    Lendo os dados do pesquisador                                      */
            /*********************************************************************************************************/
            
            Pesquisador pesquisador = new Pesquisador();
            
            Node node = doc.getElementsByTagName("CURRICULO-VITAE").item(0);

            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                
                String identificador = element.getAttribute("NUMERO-IDENTIFICADOR");

                pesquisador.setIdentificador(identificador);
            }

            node = doc.getElementsByTagName("DADOS-GERAIS").item(0);
            
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
            
                String nome = element.getAttribute("NOME-COMPLETO");
                String ufNascimento = element.getAttribute("UF-NASCIMENTO");
            
                pesquisador.setNome(nome);
                pesquisador.setUfNascimento(ufNascimento);
            }

            pesquisador.setInstituto(instituto);

            /*********************************************************************************************************/
            /*                     Lendo todos os livros que o pesquisador trabalhou na produção                     */
            /*********************************************************************************************************/
            
            Tipo tipoProducao = Tipo.LIVRO;

            // Obtenha a lista de elementos "LIVRO-PUBLICADO-OU-ORGANIZADO"
            NodeList nodeList = doc.getElementsByTagName("LIVRO-PUBLICADO-OU-ORGANIZADO");
            
            // Percorra os elementos "LIVRO-PUBLICADO-OU-ORGANIZADO"
            for (int i = 0; i < nodeList.getLength(); i++) {
                Producao producao = new Producao();

                List<Autor> autoresList = new ArrayList<>();

                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String nome = element.getElementsByTagName("DADOS-BASICOS-DO-LIVRO").item(0).getAttributes().getNamedItem("TITULO-DO-LIVRO").getNodeValue();
                    Integer ano = Integer.valueOf(element.getElementsByTagName("DADOS-BASICOS-DO-LIVRO").item(0).getAttributes().getNamedItem("ANO").getNodeValue());

                    producao.setNome(nome);
                    producao.setAno(ano);
                    producao.setTipoProducao(tipoProducao);

                    // Obtenha a lista de elementos "AUTORES" dentro do elemento "LIVRO-PUBLICADO-OU-ORGANIZADO"
                    NodeList autoresNodeList = element.getElementsByTagName("AUTORES");

                    // Percorra os elementos "AUTORES"
                    for (int j = 0; j < autoresNodeList.getLength(); j++) {
                        Autor autor = new Autor();

                        Node autorNode = autoresNodeList.item(j);

                        if (autorNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element autorElement = (Element) autorNode;
                            String nomeAutor = autorElement.getAttribute("NOME-COMPLETO-DO-AUTOR");

                            autor.setNome(nomeAutor);

                            //Lendo os nomes para citação
                            String nomesCitacao = autorElement.getAttribute("NOME-PARA-CITACAO");

                            // Divida a string em nomes de citação separados por vírgula
                            String[] nomesCitacaoArray = nomesCitacao.split(";");

                            List<Citacao> citacaoList = new ArrayList<>();

                            // Percorra os nomes de citação
                            for (String nomeCitacao : nomesCitacaoArray) {
                                Citacao citacao = new Citacao();

                                // Remova espaços em branco no início e no final do nome de citação
                                nomeCitacao = nomeCitacao.trim();

                                citacao.setNomeCitacao(nomeCitacao);
                                
                                citacao.setAutores(autoresList);

                                citacaoList.add(citacao);
                            }
                            autor.setCitacoes(citacaoList);

                            autoresList.add(autor);
                        }
                    }
                    producao.addAutores(autoresList);
                    
                    producaoList.add(producao);
                }
            }

            /*********************************************************************************************************/
            /*                     Lendo todos os artigos que o pesquisador trabalhou na produção                    */
            /*********************************************************************************************************/
            
            tipoProducao = Tipo.ARTIGO;

            // Obtenha a lista de elementos "ARTIGO-PUBLICADO"
            nodeList = doc.getElementsByTagName("ARTIGO-PUBLICADO");
            
            // Percorra os elementos "ARTIGO-PUBLICADO"
            for (int i = 0; i < nodeList.getLength(); i++) {
                Producao producao = new Producao();

                List<Autor> autoresList = new ArrayList<>();

                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String nome = element.getElementsByTagName("DADOS-BASICOS-DO-ARTIGO").item(0).getAttributes().getNamedItem("TITULO-DO-ARTIGO").getNodeValue();
                    Integer ano = Integer.valueOf(element.getElementsByTagName("DADOS-BASICOS-DO-ARTIGO").item(0).getAttributes().getNamedItem("ANO-DO-ARTIGO").getNodeValue());

                    producao.setNome(nome);
                    producao.setAno(ano);
                    producao.setTipoProducao(tipoProducao);

                    // Obtenha a lista de elementos "AUTORES" dentro do elemento "ARTIGO-PUBLICADO"
                    NodeList autoresNodeList = element.getElementsByTagName("AUTORES");

                    // Percorra os elementos "AUTORES"
                    for (int j = 0; j < autoresNodeList.getLength(); j++) {
                        Autor autor = new Autor();

                        Node autorNode = autoresNodeList.item(j);

                        if (autorNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element autorElement = (Element) autorNode;
                            String nomeAutor = autorElement.getAttribute("NOME-COMPLETO-DO-AUTOR");

                            autor.setNome(nomeAutor);

                            //Lendo os nomes para citação
                            String nomesCitacao = autorElement.getAttribute("NOME-PARA-CITACAO");

                            // Divida a string em nomes de citação separados por vírgula
                            String[] nomesCitacaoArray = nomesCitacao.split(";");

                            List<Citacao> citacaoList = new ArrayList<>();

                            // Percorra os nomes de citação
                            for (String nomeCitacao : nomesCitacaoArray) {
                                Citacao citacao = new Citacao();

                                // Remova espaços em branco no início e no final do nome de citação
                                nomeCitacao = nomeCitacao.trim();

                                citacao.setNomeCitacao(nomeCitacao);
                                citacao.setAutores(autoresList);

                                citacaoList.add(citacao);
                            }
                            autor.setCitacoes(citacaoList);

                            autoresList.add(autor);

                            //fazer if para verificar se o autor está cadastrado
                        }
                    }
                    producao.addAutores(autoresList);
                    
                    producaoList.add(producao);
                }
            }
            
            /*********************************************************************************************************/
            /*                                 Gravando toda a lista de produções                                    */
            /*********************************************************************************************************/

            pesquisador.addProducao(producaoList);
            
            pesquisadorList.add(pesquisador);
            
            pesquisadorService.saveAll(pesquisadorList);
        
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
