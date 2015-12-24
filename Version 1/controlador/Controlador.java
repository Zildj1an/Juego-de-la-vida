package es.ucm.fdi.tp.practica1.controlador;

import java.util.Scanner;
import es.ucm.fdi.tp.practica1.logica.Mundo;
/**
 * 
 * @author Carlos Gómez y Daniel Gárcia
 * @version 1.0
 *
 */
public class Controlador {

	private Mundo mundo;
	private Scanner entrada;
	
	//public Controlador(){
	//	this.mundo = new Mundo();
	//	this.entrada = new Scanner(System.in);
	//}
	
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
			System.out.println("COMANDO > ");
			String comando = entrada.nextLine().toLowerCase();
			comandoSeparado = comando.split(" ");
			
			switch (comandoSeparado[0]){
				case "paso":
					System.out.println(mundo.evoluciona());
					break;
					
				case "iniciar":
					mundo.inicializa();
					break;
					
				case "crearcelula":
					if (comandoSeparado.length != 3){
						System.out.println("Parametros invalido");
					}else{
						if (mundo.crearCelula(Integer.parseInt(comandoSeparado[1]), Integer.parseInt(comandoSeparado[2]))){
							System.out.println("Creamos celula en la posicion: (" + comandoSeparado[1] + "," + comandoSeparado[2] + ")" );
						}else{
							System.out.println("No se puede crear una celula");
						}
					}
					break;
					
				case "eliminarcelula":
					if (comandoSeparado.length != 3){
						System.out.println("Parametros invalidos");
					}else{
						if (mundo.eliminarCelula(Integer.parseInt(comandoSeparado[1]), Integer.parseInt(comandoSeparado[2]))){
							System.out.println("Eliminamos celula en la posicion: (" + comandoSeparado[1] + "," + comandoSeparado[2] + ")" );
						}else{
							System.out.println("No se ha podido eliminar la celula");
						}
					}	
					break;
				case "ayuda":
					ayuda();
					break;
					
				case "vaciar":
					mundo.vaciar();
					break;
					
				case "salir":
					System.out.println("Fin de la simulacion...");
					break;
					
				default:
					System.out.println("Comando no valido");
					break;
			}
		}while (!comandoSeparado[0].equals("salir"));
	}
	
	/**
	 * Método estático que contiene la información de la ayuda
	 */
	private static void ayuda(){
		System.out.println("POSIBLES COMANDOS:");
		System.out.println("\t PASO:  realiza un paso en la simulacion");
		System.out.println("\t AYUDA: muestra esta ayuda");
		System.out.println("\t SALIR: cierra la aplicación");
		System.out.println("\t INICIAR: inicia una nueva simulación");
		System.out.println("\t VACIAR: crea un mundo vacío");
		System.out.println("\t CREARCELULA F C: crea una nueva celula en la posición (f,c) si es posible");
		System.out.println("\t ELIMINARCELULA F C: elimina una celula de la posición (f,c) si es posible \n");
	}
}
