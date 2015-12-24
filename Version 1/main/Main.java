package es.ucm.fdi.tp.practica1.main;

import java.util.Scanner;

import es.ucm.fdi.tp.practica1.controlador.Controlador;
import es.ucm.fdi.tp.practica1.logica.Mundo;

/**
 * 
 * @author Carlos Gómez y Daniel Garcia
 *
 */
public class Main {
	/**
	 * Metodo de entra de la aplicacion
	 * @param args
	 */
	public static void main(String[] args) {
		Mundo mundo = new Mundo();
		Scanner scanner = new Scanner(System.in);
		Controlador controlador = new Controlador(mundo, scanner);
		controlador.realizaSimulacion();
	
		
	}

}
