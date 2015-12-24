package controlador;

import java.util.Scanner;

import comandos.Comando;
import comandos.ParserComando;
import logica.Mundo;


/**
 * 
 * @author Carlos Gomez y Daniel Garcia
 * @version 2.0
 *
 */
public class Controlador {

	private Mundo mundo;
	private Scanner entrada;
		
	/**
	 * Constructor de la clase controlador
	 * @param mun parametro que contiene un mundo
	 * @param sc parametro que contiene un Scanner para leer por teclado
	 */
	public Controlador(Mundo mun, Scanner sc) {
		entrada = sc;
		mundo = mun;
	}
	
	/**
	 * Método que se encargada de la simulación del juego 
	 */
	public void realizaSimulacion(){
		
		String[] comandoSeparado;
		mundo.inicializa();
		
		do{
			System.out.println(mundo.toString());
			System.out.print("COMANDO > ");
			String comandoString = entrada.nextLine().toLowerCase();
			comandoSeparado = comandoString.split(" ");
			
			Comando comando = ParserComando.parseaComando(comandoSeparado);
			if (comando != null){
				System.out.println(comando.ejecuta(mundo));
			}else{
				System.out.println("ERROR, comando invalido");
				System.out.println(ParserComando.AyudaComandos());
			}
			
		}while (!mundo.isSimulacionTerminada());
	}
}
