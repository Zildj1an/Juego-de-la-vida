package es.ucm.fdi.tp.practica1.logica;

import java.lang.Math;

/**
 * Clase que contiene una matriz sobre la que se realizan todas las interacciones con las celulas
 * @author Carlos G�mez y Daniel Garc�a
 *
 */
public class Superficie {
	private Celula[][] superficie;
	private int filas;
	private int columnas;
	private int numCelulas;
	
	/**
	 * Contructor de la clase Superficie que genera una matriz de nf * nc
	 * @param nf parametro que contiene el numero de filas
	 * @param nc parametro que contiene el numero de columnas
	 */
	public Superficie(int nf, int nc){
		this.filas = nf;
		this.columnas = nc;
		superficie = new Celula[filas][columnas];
		this.numCelulas = 0;
	}
	
	/**
	 * M�todo que inicializa la supercie con el tama�o filas*columnas con el numero de celulasInicialies colocadas aleatoriamente
	 * @param filas parametro que contiene el numero de filas
	 * @param columnas parametro que contiene el numero de columnas
	 * @param celulasIniciales parametro que contien el n�mero de celulas que se repartiran inicialmente
	 */
	public void inicializaSuperficie(int filas, int columnas, int celulasIniciales){		
		int posFila;
		int posColumna;
		int numCelulas = 0;
		
		reset(filas, columnas);
		do{
			posFila = numAleatorio(filas);
			posColumna = numAleatorio(columnas);
			if (crearCelula(posFila, posColumna)){
				numCelulas++;
			}
		}while (numCelulas != celulasIniciales);
		
		this.numCelulas = celulasIniciales;
	}
	
	/**
	 * M�todo que resetea toda la superfie
	 * @param filas parametro que contiene el n�mero de filas de la superficie
	 * @param columnas parametro que contiene el n�mero de columnas de la superficie
	 */
	public void reset(int filas, int columnas){
		for (int i = 0; i < filas; i++){
			for (int j = 0; j < columnas; j++ ){
				superficie[i][j] = null;
			}
		}
		this.numCelulas = 0;
	}
	
	/**
	 * Metodo que devuelve un numero aleatorio de 0 a el n�mero pasado por parametro
	 * @param limite n�mero hasta el que queremos obtener el numero aleatorio
	 * @return retorna el valor entero del n�mero aleatorio obtenido
	 */
	private int numAleatorio(int limite){
		
		return (int)(Math.random()*limite);
		
	}
	
	/**
	 * Metodo que crea una c�lula en la posicion (f,c)
	 * @param f parametro que contiene la fila
	 * @param c parametro que contiene la columna
	 * @return retorna booleando que determina si ha sido posible o no la creacion de la c�lula
	 */
	public boolean crearCelula(int f, int c){
		boolean creada = false;
		
		if (comprobacionLimitePosicion(f, c) && superficie[f][c] == null){
			superficie[f][c] = new Celula();
			creada = true;
			this.numCelulas++;
		}
		
		return creada;
	}
	
	/**
	 * M�todo que elimina la c�lula de la posicion (f,c)
	 * @param f parametro que contiene la fila
	 * @param c parametro que contiene la columna
	 * @return retorna booleando indicando si ha sido posible elimanr la c�lula o no
	 */
	public boolean eliminarCelula(int f, int c){
			boolean eliminada = false;
		
		if (comprobacionLimitePosicion(f, c) && superficie[f][c] != null){
			superficie[f][c] = null;
			eliminada = true;
			this.numCelulas--;
		}
		
		return eliminada;
	}
	
	
	/**
	 * Metedo que determina si la fila y la columna se encuentra entre los rango permitidos
	 * @param fila parametro que contiene la fila
	 * @param columna parametro que contiene la columna
	 * @return retorna booleando indicando si la fila y columan estan entre los valores permitidos
	 */
	private boolean comprobacionLimitePosicion(int fila, int columna){
		
		return ((fila >= 0 && fila < this.filas) && (columna >= 0 && columna < this.columnas));
	}

	/**
	 * metodo que busca las c�lulas en la superficie, devlviendo un array de casillas con la informacion de la fila y columna donde se encuentra cada c�lula
	 * @param casillas array de casillas que contiene la posicion exacta de cada c�lula
	 */
	public void buscarCelulas(Casilla[] casillas){
		int contador = 0;
		
		for (int i = 0; i < this.filas; i++){
			for (int j = 0; j < this.columnas; j++){
				if (superficie[i][j] != null){
					casillas[contador] = new Casilla(i, j);
					contador++;
				}
			}
		}
	}
	
	/**
	 * metodo que devuelve una casilla limitrofe libre de forma aleatoria
	 * @param casilla casilla a partir de la cual queremos buscar una libre
	 * @return retorna una casilla libre que se encuentre alrededor de la casilla
	 */
	public Casilla getCasillasLibre(Casilla casilla){
		Casilla[] casillas = new Casilla[8];
		int contador = 0;
		
		//deberia controlar que la casilla sea nula para evitar null pointer exception
		
		//syso log
		//System.out.println("celulas " + this.numCelulas);
		//System.out.println(casilla.getFila() + " " + casilla.getColumna());
		//
		
		for(int i = Math.max(0, casilla.getFila() - 1); i <= Math.min(casilla.getFila() + 1, this.filas - 1); i++){
			for (int j = Math.max(0, casilla.getColumna() - 1); j <= Math.min(casilla.getColumna() + 1, this.columnas -1); j++){
				if (superficie[i][j] == null){
					casillas[contador] = new Casilla(i, j);
					contador++;
				}
			}
		}
		
		return casillas[numAleatorio(contador)];
		
	}
	
	/**
	 * Metodo que dada una fila y columna mueve la c�lula a una posicion circundante
	 * @param fila fila donde se encuentra la c�lula
	 * @param columna columna donde se encuentra la c�lula
	 * @return devuelve la descripci�n del movimiento de la c�lula
	 */
	public String moverCelula(int fila, int columna){
		String cadena ="";

		Casilla casillaOcupada = new Casilla(fila, columna);
		Casilla casillaLibre = getCasillasLibre(casillaOcupada);
		
		if(casillaLibre != null){
			if (moverCelula(fila, columna, casillaLibre.getFila(), casillaLibre.getColumna())){
				cadena += "Movimiento de (" + fila + " " + columna + ") a (" + casillaLibre.getFila() + " " + casillaLibre.getColumna() + ") \n";
				superficie[casillaLibre.getFila()][casillaLibre.getColumna()].actualizarPasosReproduccion();
			}
		}else{
			superficie[fila][columna].actualizarPasosNoMovidos();
		}
		
		return cadena;
	}
	
	/**
	 * M�todo que mueve una c�lula de una posicion dada a otra dada
	 * @param filaOrigen fila de la cual queremos mover la celula
	 * @param columnaOrigen columna de la cual queremos mover la celula
	 * @param filaDestino fila a la que queremos mover la celula
	 * @param columnaDestino columna a la que queremos mover la celula
	 * @return devuelve si ha sido posible mover o no la celula a la posicion indicada
	 */
	private boolean moverCelula(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino){
		boolean ok = false;
		
		if (comprobacionLimitePosicion(filaOrigen, columnaOrigen) && comprobacionLimitePosicion(filaDestino, columnaDestino)){
			superficie[filaDestino][columnaDestino] = superficie[filaOrigen][columnaOrigen];
			superficie[filaOrigen][columnaOrigen] = null;
			ok = true;
		}
		return ok;
	}
	
	/**
	 * Metodo que reproduce la celula dada por la fila y columna
	 * @param fila fila de la celula que se va a reproducir
	 * @param columna columna de la celula que se va a reproducir
	 * @return retorna un booleano indicando si ha sido posible la reproduccion
	 */
	public boolean reproduceCelula(int fila, int columna){
		boolean reproducida = false;
		
		if(comprobacionLimitePosicion(fila, columna)){
			Casilla casillaOcupada = new Casilla(fila, columna);
			Casilla casilla = getCasillasLibre(casillaOcupada);
			
			if(casilla != null){
				moverCelula(fila, columna, casilla.getFila(), casilla.getColumna());
				crearCelula(fila, columna);
				reproducida = true;
			}
		}
		
		return reproducida;
	}
	
	/**
	 * Metodo que transforma a string la superficie 
	 */
	public String toString(){
		String cadena = "";
		
		for (int i = 0; i < this.filas; i++){
			for (int j = 0; j < this.columnas; j++){
				if(superficie[i][j] == null){
					cadena += " - ";
				}else{
					cadena += superficie[i][j].toString();
				}
				cadena += "  "; // a�ade dos espacios entre la informacion de cada c�lula
			}
			cadena += "\n";
		}
		return cadena;
	}
	
	/**
	 * Metodo que devuelve el numero de celulas de la superficie
	 * @return entero correspondiente al numero de c�lulas
	 */
	public int numCelulas(){
		return this.numCelulas;
	}	
	
	//
	//private void celulaMovida(int fila, int columna, boolean movida){
	//	if (movida){
	//		superficie[fila][columna].actualizarPasosReproduccion();
	//	}else{
	//		superficie[fila][columna].actualizarPasosNoMovidos();
	//	}
	//}
	
	/**
	 * Metodo que determina si la celula dada por fila y columna muere o no
	 * @param fila fila donde se encuetra la celula
	 * @param columna columna donde se encuentra la celula
	 * @param maxPasos entero que representa el numero maximo de pasos que puede no haber dado una celula hasta su muerte
	 * @return retorna booleano indicando si debe morir o no
	 */
	public boolean celulaMuere(int fila, int columna, int maxPasos){
		boolean ok = false;
		
		if(comprobacionLimitePosicion(fila, columna)){
			if (superficie[fila][columna].muere(maxPasos)){
				ok = true;
			}
		}
		return ok;
	}
	
	/**
	 * Metodo que determina si la celula dada por la fila y columna se debe reproducir dada el numero de pasos para su reproduccion
	 * @param fila fila correspondiente a la celula que se desea comprobar
	 * @param columna columna correspondiente a la celula que se desea comprobar
	 * @param maxPasos entero que representa el numero que debe de dar la c�lula hasta su reproduccion
	 * @return retorna booleano indicando si la celula se reproduce o no
	 */
	public boolean celulaReproduce(int fila, int columna, int maxPasos){
		boolean ok = false;
		
		if(comprobacionLimitePosicion(fila, columna)){
			if(superficie[fila][columna].reproduce(maxPasos)){
				ok = true;
			}
		}
		
		return ok;
	}
}