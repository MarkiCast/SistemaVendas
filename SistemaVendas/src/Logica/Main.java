package Logica;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import InterfaceGráfica.MenuPrincipal;

public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException, Exception {
		// TODO Auto-generated method stub
		
		MenuPrincipal menu = new MenuPrincipal();
		Caixa caixa = new Caixa();
		Controle control = new Controle(caixa, menu);

		control.loop();
	
	}

}
