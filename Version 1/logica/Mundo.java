package es.ucm.fdi.tp.practica1.logica;

/**
 * La clase mundo se encarga de realizar la ejecución de los comandos sobre la superficie
 * @author Carlos Gómez y Daniel García
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
	 * Construccor del mundo, que inicializa la superficie con las fílas y colúmnas
	 */
	public Mundo() {
		superficie = new Superficie(FILAS, COLUMNAS);
	}
	
	/**
	 * Método que inicializa la superficie, seteando a NULL todas las posiciones y posicionando aleatoriamente las celulas iniciales 
	 */
	public void inicializa(){
		superficie.inicializaSuperficie(FILAS, COLUMNAS, CELULAS_INICIALES);
	}
	
	/**
	 * Método que setea todas las posiciones a NULL, borrando así todo el contenido de la superficie
	 */
	public void vaciar(){
		superficie.reset(FILAS, COLUMNAS);
	}
	
	/**
	 * Método que crea una célula en la posicion (fila,columna)
	 * @param fila en la que se va a crear la nueva célula
	 * @param columna en la que se va a crear la nueva célula
	 * @return retorna un booleano indicando si ha sido posible crear una célula en dicha posición
	 */
	public boolean crearCelula(int fila, int columna){		
		
		return superficie.crearCelula(fila, columna);
	}
	
	/**
	 * Método que elimina una célula en la posicion (fila,columna)
	 * @param fila en la que se va a eliminar la nueva célula
	 * @param columna en la que se va a eliminar la nueva célula
	 * @return retorna booleano que indica si ha sido posible borrarla o no
	 */
	public boolean eliminarCelula(int fila, int columna){
		
		return superficie.eliminarCelula(fila, columna);
	}
	
	/**
	 * Método que se encarga de convertir la superficie a string
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
	 * Método que se encarga de hacer evolucionar el múndo deacuedo con las siguientes normas:
	 * Una célula se mueve a una posición vecina aleatoria siempre que pueda, es decir que haya alguna posición libre.
	 * Si una célula está MAX_PASOS_SIN_MOVER pasos sin moverse, entonces la célula muere por falta de ejercicio en la siguiente iteración.
	 * Cuando una célula ha dado PASOS_REPRODUCCION pasos, entonces pueden pasar dos cosas:
	 * • Si la célula tiene alguna posición vecina libre, entonces esta célula se mueve aleatoriamente a una de ellas, dejando una cría en su posición, es decir una nueva célula.
	 * • Si la célula no puede moverse entonces muere.
	 * @return retorna string con los pásos realizados durante la evolución
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
