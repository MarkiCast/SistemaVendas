
import org.w3c.dom.*;
import javax.xml.parsers.*;

import java.util.*;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;

import org.xml.sax.SAXException;

public class LoadStoreXml{
    public static final String xmlClientes = "./../xmlFiles/clientes.xml";
    public static final String xmlProdutos = "./../xmlFiles/produtos.xml";
    public static final String xmlVendas = "./../xmlFiles/vendas.xml";

    public void addClient(File file, Document doc, Cliente c) throws Exception{
        
        Node root = doc.getFirstChild();
        Element cliente = doc.createElement("cliente");

        String nome = c.getNome();
        String l = Long.toString(c.getCpf());

        cliente.setAttribute("nome", nome);
        cliente.setAttribute("cpf", l);

        root.appendChild(cliente);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        //transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        //transf.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        
        StreamResult myFile = new StreamResult(file);

        transf.transform(source, myFile);
    }
    public void addProduto(File file, Document doc, Produto p) throws Exception{
        
        Node root = doc.getFirstChild();
        Element produto = doc.createElement("produto");

        String val = Integer.toString(p.getValor()); 
        String nome = p.getDesc();
        String l = Long.toString(p.getUpc());

        produto.setAttribute("valor", val);
        produto.setAttribute("upc", l);
        produto.setAttribute("descricao", nome);

        root.appendChild(produto);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        //transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        //transf.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        
        StreamResult myFile = new StreamResult(file);

        transf.transform(source, myFile);
    }
    
    public void addVenda(File file, Document doc, Venda v) throws Exception{
        
        Node root = doc.getFirstChild();
        Element venda = doc.createElement("venda");

        String id = Integer.toString(v.getId());
        String valorTotal = Integer.toString(v.getValorTotal());
        String desconto = Integer.toString(v.getDesconto());
        String tipoPagamento = Integer.toString(v.getTipoPagamento());
        String cpf = Long.toString(v.getCliente().getCpf());

        venda.setAttribute("id", id);
        venda.setAttribute("valor_total", valorTotal);
        venda.setAttribute("desconto", desconto);
        venda.setAttribute("tipoPagamento", tipoPagamento);
        venda.setAttribute("cpf_cliente", cpf);

        root.appendChild(venda);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        //transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        //transf.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        
        StreamResult myFile = new StreamResult(file);

        transf.transform(source, myFile);
    }

    public void salvaVenda(Venda v) throws SAXException, Exception,
            IOException, ParserConfigurationException, TransformerException {
            
        LoadStoreXml lsx = new LoadStoreXml();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc;
        Element root;
        File xmlSales = new File(xmlVendas);
        if(fileVazia("vendas")) {
            doc = dBuilder.newDocument();
            String mother = "vendas";
            doc = lsx.createDocument(xmlSales, doc, mother);
        }
        
        doc = dBuilder.parse(xmlSales);
        lsx.addVenda(xmlSales, doc, v);

    }

    public void eraseClient(Cliente c) {

    }

    public Document createDocument(File file, Document doc, String mother) throws TransformerException,
            TransformerConfigurationException{
        Element root = doc.createElementNS("klaus-marcelo", mother);
        doc.appendChild(root);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();
       
        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        File myFile = new File("./../xmlFiles/"+mother+".xml");
       
        StreamResult f = new StreamResult(file);

        transf.transform(source, f);
        return doc;
    }



    public LoadStoreXml() {
        
    }


    public void cadastraCliente() throws SAXException, Exception,
            IOException, ParserConfigurationException, TransformerException {
            
        LoadStoreXml lsx = new LoadStoreXml();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc;
        Element root;
        File xmlClients = new File(xmlClientes);
        if(fileVazia("clientes")) {
            doc = dBuilder.newDocument();
            String mother = "clientes";
            doc = lsx.createDocument(xmlClients, doc, mother);
        }
        Scanner in = new Scanner(System.in);
        System.out.println("insira nome do cliente");
        String nome = in.nextLine();
               
        System.out.println("insira cpf do cliente");
        String cpfString = in.nextLine();
        Long cpf = Long.parseLong(cpfString, 10);

        Cliente c1 = new Cliente(nome, cpf);
        
        doc = dBuilder.parse(xmlClients);
        lsx.addClient(xmlClients, doc, c1);

    }

    public void cadastraProduto(Produto p) throws SAXException, Exception,
            IOException, ParserConfigurationException, TransformerException {
            
        LoadStoreXml lsx = new LoadStoreXml();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc;
        Element root;
        File xmlProducts = new File(xmlProdutos);
        if(fileVazia("produtos")) {
            doc = dBuilder.newDocument();
            String mother = "produtos";
            doc = lsx.createDocument(xmlProducts, doc, mother);
        }
        
        doc = dBuilder.parse(xmlProducts);
        lsx.addProduto(xmlProducts, doc, p);

    }
    public Cliente findCliente(String cpf) throws SAXException, Exception,
            IOException, ParserConfigurationException, TransformerException {
        Cliente c = new Cliente();

        File xmlClients = new File(xmlClientes);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlClients);
        Element root;

        NodeList nodes = doc.getElementsByTagName("clientes");
        Element element = (Element) nodes.item(0);
        NodeList clientes = element.getElementsByTagName("cliente");

        boolean isInDatabase = false;
        String strNome = "";
        String strCpf = "";
        for(int i=0; i<clientes.getLength(); i++){
            Element e = (Element) clientes.item(i);
            //System.out.println(e.getAttributes().getNamedItem("cpf").getNodeValue());
            //String strCpf = e.getAttributes().getNamedItem("cpf").getNodeValue();
            strCpf = (String) e.getAttribute("cpf");
            if(strCpf.equals(cpf)){
                strNome = e.getAttributes().getNamedItem("nome").getNodeValue();
                c.setName(strNome);
                strCpf = e.getAttributes().getNamedItem("cpf").getNodeValue();
                Long var = Long.parseLong(strCpf, 10); 
                c.setCpf(var);
                isInDatabase = true;
            }
        }
        if(isInDatabase){
            return c;
        }else{
            return null;
        }
        
    }

    public Produto findProduto(String upc) throws SAXException, Exception,
            IOException, ParserConfigurationException, TransformerException {
        Produto p = new Produto();

        File xmlProducts = new File(xmlProdutos);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlProducts);
        Element root;

        NodeList nodes = doc.getElementsByTagName("produtos");
        Element element = (Element) nodes.item(0);
        NodeList produtos = element.getElementsByTagName("produto");

        boolean isInDatabase = false;
        String strNome = "";
        String strValor = "";
        String strDesc = "";
        for(int i=0; i<produtos.getLength(); i++){
            Element e = (Element) produtos.item(i);
            //System.out.println(e.getAttributes().getNamedItem("cpf").getNodeValue());
            //String strCpf = e.getAttributes().getNamedItem("cpf").getNodeValue();
            String strUpc = (String) e.getAttribute("upc");
            if(strUpc.equals(upc)){
                strValor = e.getAttributes().getNamedItem("valor").getNodeValue();
                p.setValor(Integer.parseInt(strValor));
                strUpc = e.getAttributes().getNamedItem("upc").getNodeValue();
                Long var = Long.parseLong(strUpc, 10); 
                p.setUpc(var);
                strDesc = e.getAttributes().getNamedItem("descricao").getNodeValue();
                p.setDesc(strDesc);
                isInDatabase = true;
            }
        }
        if(isInDatabase){
            return p;
        }else{
            return null;
        }
        
    }
    public boolean fileVazia(String file){
        if(file == "produtos"){
            File f = new File(xmlProdutos);
            if(f.length()==0){
                return true;
            }
            return false;
        }else if(file == "vendas"){
            File f = new File(xmlVendas);
            if(f.length()==0){
                return true;
            }
            return false;
        }else{
            File f = new File(xmlClientes);
            if(f.length()==0){
                return true;
            }
            return false;            
        }
    }
}
