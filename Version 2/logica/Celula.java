package logica;

/**
 * Clase abstracta de celula
 *
 */
public abstract class Celula {
	protected boolean comestible;
	
	/**
	 * metodo encargado de que se ejecute la celula
	 * @param f fila en la que se encuentra la celula a ejecutar
	 * @param c columna en la que se encuentra la celula a ejecutar
	 * @param superficie superficie sobre la que se ejecutara el movimiento
	 * @return Casilla de destino de la celula
	 */
	public abstract Casilla ejecutaMovimiento(int f, int c, Superficie superficie);
	
	/**
	 * metodo que determina si una celula es comestible o no
	 * @return booleano que indica si es comestible o no
	 */
	public abstract boolean esComestible();
	
	/**
	 * Metodo para convertir a String la celula
	 *@return String con la cadena a mostrar 
	 */
	public abstract String toString();
	
	/**
	 * metodo que determina si la celula debe morir o no
	 * @return boolean que indica si muere o no
	 */
	public abstract boolean muere();
	
	/**
	 * metodo qeu determina si la celula debe reproducirse o no
	 * @return boolean que indica si se reproduce o no
	 */
	public abstract boolean reproduce();
}
