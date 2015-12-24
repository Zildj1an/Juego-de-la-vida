package logica;

/**
 * Clase celula compleja, contiene la informacion relevante sobre la celula y sus metodos para poder
 * realizar todas las operaciones con ella
 *
 */
public class CelulaCompleja extends Celula{
	private final int MAX_COMER = 3;
	private int comidas;
	
	/**
	 * Constructor de celula compleja, inicializa comestible y comidas
	 */
	public CelulaCompleja(){
		comestible = false;
		comidas = MAX_COMER;
	}
	
	/**
	 * metodo encargado de que se ejecute la celula
	 * @param f fila en la que se encuentra la celula a ejecutar
	 * @param c columna en la que se encuentra la celula a ejecutar
	 * @param superficie superficie sobre la que se ejecutara el movimiento
	 * @return Casilla de destino de la celula
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		//En caso de no moverse, daria null, pero en nuestro caso nunca da null.
		Casilla casillaNueva = superficie.getCasillaCelulaCompleja();
		
		if(!superficie.hayCelula(casillaNueva.getFila(), casillaNueva.getColumna())){
			superficie.moverCelula(f, c, casillaNueva.getFila(), casillaNueva.getColumna());
		}
		
		else if(superficie.esComestible(casillaNueva.getFila(), casillaNueva.getColumna())){
			superficie.eliminarCelula(casillaNueva.getFila(), casillaNueva.getColumna());
			superficie.moverCelula(f, c, casillaNueva.getFila(), casillaNueva.getColumna());
			
			this.disminuyeComidas();
			
			if(this.muere()){
				superficie.eliminarCelula(f, c);
			}
		}
		
		return casillaNueva;
	}

	/**
	 * metodo que determina si una celula es comestible o no
	 * @return booleano que indica si es comestible o no
	 */
	public boolean esComestible() {
		
		return comestible;
	}
	/**
	 * Metodo para convertir a String la celula
	 *@return String con la cadena a mostrar 
	 */
	public String toString(){
		
		return " * ";
	}
	/**
	 * metodo que determina si la celula debe morir o no
	 * @return boolean que indica si muere o no
	 */
	public boolean muere() {
		
		return this.comidas == 0;
	}

	/**
	 * metodo qeu determina si la celula debe reproducirse o no
	 * @return boolean que indica si se reproduce o no
	 */
	public boolean reproduce() {
		return false;
	}
	
	/**
	 * metodo que se encarga de disminuir el valor de celulas comidas
	 */
	public void disminuyeComidas(){
		
		this.comidas--;
	}
}
