package es.ucm.fdi.tp.practica1.logica;

/**
 * La clase mundo se encarga de realizar la ejecuci�n de los comandos sobre la superficie
 * @author Carlos G�mez y Daniel Garc�a
 *
 */
public class Mundo {
	
	private final int FILAS = 5;
	private final int COLUMNAS = 5;
	private final int CELULAS_INICIALES = 6;
	private final int MAX_PASOS_SIN_MOVER = 3;
	private final int PASOS_REPRODUCCION = 2;
	private Superficie superficie;
	
	/**
	 * Construccor del mundo, que inicializa la superficie con las f�las y col�mnas
	 */
	public Mundo() {
		superficie = new Superficie(FILAS, COLUMNAS);
	}
	
	/**
	 * M�todo que inicializa la superficie, seteando a NULL todas las posiciones y posicionando aleatoriamente las celulas iniciales 
	 */
	public void inicializa(){
		superficie.inicializaSuperficie(FILAS, COLUMNAS, CELULAS_INICIALES);
	}
	
	/**
	 * M�todo que setea todas las posiciones a NULL, borrando as� todo el contenido de la superficie
	 */
	public void vaciar(){
		superficie.reset(FILAS, COLUMNAS);
	}
	
	/**
	 * M�todo que crea una c�lula en la posicion (fila,columna)
	 * @param fila en la que se va a crear la nueva c�lula
	 * @param columna en la que se va a crear la nueva c�lula
	 * @return retorna un booleano indicando si ha sido posible crear una c�lula en dicha posici�n
	 */
	public boolean crearCelula(int fila, int columna){		
		
		return superficie.crearCelula(fila, columna);
	}
	
	/**
	 * M�todo que elimina una c�lula en la posicion (fila,columna)
	 * @param fila en la que se va a eliminar la nueva c�lula
	 * @param columna en la que se va a eliminar la nueva c�lula
	 * @return retorna booleano que indica si ha sido posible borrarla o no
	 */
	public boolean eliminarCelula(int fila, int columna){
		
		return superficie.eliminarCelula(fila, columna);
	}
	
	/**
	 * M�todo que se encarga de convertir la superficie a string
	 * @return retorna String con el contenido de la superficie
	 */
	public String toString(){
		
		return superficie.toString();
		
	}
	
	/*
	public String evoluciona(){
		String textoParaMostrar = "";
		Casilla casillaDestino;
		int numCelulas = superficie.numCelulas();
		Casilla[] casillasOcupadas = new Casilla[numCelulas];
		superficie.buscarCelulas(casillasOcupadas);
		
		for (int i = 0; i < numCelulas; i++){
			casillaDestino = superficie.getCasillasLibre(casillasOcupadas[i]);
			if (casillaDestino == null){
				superficie.celulaMovida(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna(), false);
				if (superficie.celulaMuere(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna(), this.MAX_PASOS_SIN_MOVER)){
					superficie.eliminarCelula(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna());
				}
			}else{
				if(superficie.moverCelula(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna(), casillaDestino.getFila(), casillaDestino.getColumna())){
					superficie.celulaMovida(casillaDestino.getFila(), casillaDestino.getColumna(), true);
					if(superficie.celulaReproduce(casillaDestino.getFila(), casillaDestino.getColumna(), this.PASOS_REPRODUCCION)){
						superficie.reproduceCelula(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna());
					}
				}
			}
		}
		
		return "";
	}
	*/
	
	/**
	 * M�todo que se encarga de hacer evolucionar el m�ndo deacuedo con las siguientes normas:
	 * Una c�lula se mueve a una posici�n vecina aleatoria siempre que pueda, es decir que haya alguna posici�n libre.
	 * Si una c�lula est� MAX_PASOS_SIN_MOVER pasos sin moverse, entonces la c�lula muere por falta de ejercicio en la siguiente iteraci�n.
	 * Cuando una c�lula ha dado PASOS_REPRODUCCION pasos, entonces pueden pasar dos cosas:
	 * � Si la c�lula tiene alguna posici�n vecina libre, entonces esta c�lula se mueve aleatoriamente a una de ellas, dejando una cr�a en su posici�n, es decir una nueva c�lula.
	 * � Si la c�lula no puede moverse entonces muere.
	 * @return retorna string con los p�sos realizados durante la evoluci�n
	 */
	public String evoluciona(){
		String cadena = "";
		int numCelulas = superficie.numCelulas();
		Casilla[] casillasOcupadas = new Casilla[numCelulas];
		superficie.buscarCelulas(casillasOcupadas);
		
		for (int i = 0; i < numCelulas; i++){
			if (superficie.celulaMuere(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna(), MAX_PASOS_SIN_MOVER) && superficie.eliminarCelula(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna())){
				cadena += "Muere la celula de la casilla (" + casillasOcupadas[i].getFila() + " " + casillasOcupadas[i].getColumna() + ") muere por falta de actividad \n";
			}else{
				if (superficie.celulaReproduce(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna(), PASOS_REPRODUCCION)){
					if(!superficie.reproduceCelula(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna())){
						if(superficie.eliminarCelula(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna())){
						cadena += "Muere la celula (" + casillasOcupadas[i].getFila() + " " + casillasOcupadas[i].getColumna() + ") por no poder reproducirse \n";
						}
					}else{
						cadena += "La celula " + casillasOcupadas[i].getFila() + " " + casillasOcupadas[i].getColumna() + " se reproduce \n";
					}
				}else{
					cadena += superficie.moverCelula(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna());
				}
			}
		}
		
		return cadena;
	}
}
