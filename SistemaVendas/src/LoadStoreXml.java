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
    public static final String xmlFilePath = "./../xmlFiles/clientes.xml";

    public static void main(String[] args) throws SAXException, Exception,
            IOException, ParserConfigurationException, TransformerException {
        LoadStoreXml lsx = new LoadStoreXml();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc;
        Element root;
        File xmlFile = new File(xmlFilePath);
        if(xmlFile.length() == 0) {
            doc = dBuilder.newDocument();
            doc = lsx.createDocument(xmlFile, doc);
        }
       
        //NodeList nList = doc.getElementsByTagName("cliente");

        Scanner in = new Scanner(System.in);
        System.out.println("MENU \nchecar lista de clientes [1]\nadicionar um cliente [2]\napagar um cliente[3]");
        int k = Integer.parseInt(in.nextLine());
        switch (k) {
            case 1:
                break;
            case 2:
                System.out.println("insira nome do cliente");
                String nome = in.nextLine();
               
                System.out.println("insira cpf do cliente");
                String cpfString = in.nextLine();
                Long cpf = Long.parseLong(cpfString, 10);

                Cliente c1 = new Cliente(nome, cpf);
                
                doc = dBuilder.parse(xmlFile);
                lsx.addClient(xmlFile, doc, c1);
                break;
            case 3:
                break;
            default:
        }
    }

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

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        
        StreamResult myFile = new StreamResult(file);

        transf.transform(source, myFile);
    }

    public void eraseClient(Cliente c) {

    }

    public Document createDocument(File file, Document doc) throws TransformerException,
            TransformerConfigurationException{
        Element root = doc.createElementNS("klaus-marcelo", "clientes");
        doc.appendChild(root);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();
       
        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        File myFile = new File("src/main/resources/users.xml");
       
        StreamResult f = new StreamResult(file);

        transf.transform(source, f);
        return doc;
    }
}