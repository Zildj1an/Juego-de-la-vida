package es.ucm.fdi.tp.practica1.logica;

/**
 * La clase casilla representa una celda de la matriz, que contiene la fila y columna donde esta alojada
 * @author Carlos Gómez y Daniel García
 *
 */
public class Casilla {
	private int fila;
	private int columna;
	
	/**
	 * Constructor de la clase Casilla
	 * @param fila parametro para inicializar la fila
	 * @param columna parametro para inicializar la columna
	 */
	public Casilla(int fila, int columna){
		this.fila = fila;
		this.columna = columna;
	}
	
	/**
	 * Metodo que retorna el valor de la fila
	 * @return devuelve un entero que representa la fila
	 */
	public int getFila(){
		return this.fila;
	}
	
	/**
	 * Método que retorna el valor de la columna
	 * @return devuelve un entero que representa la columna
	 */
	public int getColumna(){
		return this.columna;
	}
}
