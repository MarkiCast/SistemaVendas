import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import InterfaceGráfica.MenuPrincipal;

public class Main {
	public static void main(String[] args) throws SAXException, Exception,
            IOException, ParserConfigurationException, TransformerException {

		//MenuPrincipal mainMenu = new MenuPrincipal();
		//Por enquanto a interface ta toda dentro do controle mas eventualmente vai ser grafico e não console
		
		Scanner scan = new Scanner(System.in);
		Caixa caixa = new Caixa();
		caixa.encheListaProdutos();
		Controle control = new Controle(caixa, scan);
		
		control.loop();
		
	}	
}


