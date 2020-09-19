import org.w3c.dom.*;
import javax.xml.parsers.*;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;

import java.util.Scanner;

public class Teste{
    public static final String xmlFilePath = "./../xmlFiles/clientes.xml";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("insira nome do cliente");
        String nome = in.nextLine();
        
        System.out.println("insira cpf do cliente");
        String cpfString = in.nextLine();
        Long cpf = Long.parseLong(cpfString, 10);

        Cliente c1 = new Cliente(nome, cpf);

        try{
            FileOutputStream fos = new FileOutputStream(new File(xmlFilePath));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(c1);
            encoder.close();
            fos.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
