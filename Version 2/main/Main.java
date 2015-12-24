/*PREGUNTAS
 * - Crear booleano simulacionTerminada. (modificar controlador)
 * -  
 * */

package main;

import java.util.Scanner;

import controlador.Controlador;
import logica.Mundo;


public class Main {

	public static void main(String[] args) {
		Mundo mundo = new Mundo();
		Scanner scanner = new Scanner(System.in);
		Controlador controlador = new Controlador(mundo, scanner);
		controlador.realizaSimulacion();
		
	}

}
