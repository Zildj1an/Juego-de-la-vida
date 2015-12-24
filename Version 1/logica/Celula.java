package es.ucm.fdi.tp.practica1.logica;

public class Celula {
	private int numPasosNoMovidos;
	private int numPasosReproduccion;

	public Celula() {
		this.numPasosReproduccion = 0;
		this.numPasosNoMovidos = 0;
	}
	
	public Celula(int pasosNoMovidos, int pasosDados){
		this.numPasosNoMovidos = pasosNoMovidos;
		this.numPasosReproduccion = pasosDados;
	}
	
	public String toString(){
		return numPasosNoMovidos + "-" + numPasosReproduccion;
	}
	
	public void actualizarPasosNoMovidos(){
		this.numPasosNoMovidos++;
	}
	
	public void actualizarPasosReproduccion(){
		this.numPasosReproduccion++;
	}

	//public int getNumPasosNoMovidos() {
	//	return numPasosNoMovidos;
	//}

	//public int getNumPasosReproduccion() {
	//	return numPasosReproduccion;
	//}
	
	//public void setNumPasosReproduccion(int numPasosReproduccion) {
	//	this.numPasosReproduccion = numPasosReproduccion;
	//}
	
	public boolean muere(int max_pasos){
		
		return max_pasos == this.numPasosNoMovidos;
	}
	
	public boolean reproduce(int max_pasos){
		boolean reproduce = false;
		
		if(max_pasos == this.numPasosReproduccion){
			reproduce = true;
			this.numPasosReproduccion = 0;
		}
		
		return reproduce;
	}
}
