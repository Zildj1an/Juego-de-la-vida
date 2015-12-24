package logica;

/**
 * La clase mundo se encarga de realizar la ejecuci�n de los comandos sobre la superficie
 * @author Carlos G�mez y Daniel Garc�a
 *
 */
public class Mundo {
	private final int FILAS = 5;
	private final int COLUMNAS = 5;
	private final int CELULAS_SIMPLES_INICIALES = 3;
	private final int CELULAS_COMPLEJAS_INICIALES = 2;
	private Superficie superficie;
	boolean simulacionTerminada = false;

	/**
	 * Constructor del mundo, que inicializa la superficie con las f�las y col�mnas
	 */
	public Mundo() {
		this.superficie = new Superficie(FILAS, COLUMNAS);
		this.simulacionTerminada = false;
	}
	
	/**
	 * M�todo que inicializa la superficie, seteando a NULL todas las posiciones y posicionando aleatoriamente las celulas iniciales 
	 */
	public void inicializa(){
		superficie.inicializaSuperficie(FILAS, COLUMNAS, CELULAS_SIMPLES_INICIALES, CELULAS_COMPLEJAS_INICIALES);
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
	public boolean crearCelulaCompleja(int fila, int columna){		
		
		return superficie.crearCelulaCompleja(fila, columna);
	}
	
	public boolean crearCelulaSimple(int fila, int columna){
		
		return superficie.crearCelulaSimple(fila, columna);
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
	
	public boolean isSimulacionTerminada() {
		return simulacionTerminada;
	}

	public void setSimulacionTerminada() {
		this.simulacionTerminada = true;
	}
	
	
	/**
	 * M�todo que se encarga de convertir la superficie a string
	 * @return retorna String con el contenido de la superficie
	 */
	public String toString(){
		
		return superficie.toString();
	}
	
	///////////////////////////////////////////////
	
	/**
	 * Metodo encargado de evolucionar todas las celulas
	 * @return String con la informacion que ha tenido lugar en el paso
	 */
	public String evoluciona(){
		String frases = "";
		boolean [][]activas = new boolean[this.FILAS][this.COLUMNAS];
		Casilla casillaNueva;
		
		//superficie.rellenarArrayBidimensional(activas);	
		for (int f = 0; f < FILAS; f++){
			for (int c = 0; c < COLUMNAS; c++){
				activas[f][c] = true;
			}
		}
		
		
		//EjecutarCelula
		for(int f = 0; f < this.FILAS; f++){
			for(int c = 0; c < this.COLUMNAS; c++){
				if(activas[f][c] && superficie.hayCelula(f, c)){ //si no es false y si hay celula entro
					boolean hayCelula = superficie.hayCelula(f, c);
					
					//Ejecuto el movimiento
					casillaNueva = superficie.ejecutarCelula(f, c);		
					
					if(casillaNueva != null){
						activas[casillaNueva.getFila()][casillaNueva.getColumna()] = false;
						//Registro el movimiento
					}
					
					frases += concatenarFrasesEvoluciona(f, c, casillaNueva, hayCelula);
				}
			}
		}	
		
		return frases;
	}
	
	/**
	 * metodo que se encarga de generar el string a mostrar
	 * @param filaOriginal fila de origen de la celula
	 * @param columnaOriginal columna de origen de la celula
	 * @param casillaNueva casilla de destino de la celula
	 * @param hayCelula indica si hay celula o no
	 * @return
	 */
	public String concatenarFrasesEvoluciona(int filaOriginal, int columnaOriginal, Casilla casillaNueva, boolean hayCelula){
		String cadena = "";
		
		//Si se mueve, habra que registrar movimiento
		if(filaOriginal != casillaNueva.getFila() && columnaOriginal != casillaNueva.getColumna()){
			//COMPLEJA
			if(superficie.esComestible(casillaNueva.getFila(), casillaNueva.getColumna())){
				//Aqui compruebo si se reproduce
				if(superficie.hayCelula(casillaNueva.getFila(), casillaNueva.getColumna())){
					cadena += "Movimiento de (" + filaOriginal + " " + columnaOriginal + ") a (" 
							+ casillaNueva.getFila() + " " + casillaNueva.getColumna() + ") \n";
				}
				if(superficie.hayCelula(casillaNueva.getFila(), casillaNueva.getColumna())
						&& superficie.hayCelula(filaOriginal, columnaOriginal)){
					cadena += "Nace nueva celula en (" + casillaNueva.getFila() + ", " + casillaNueva.getColumna() + 
							") cuyo padre ha sido (" + filaOriginal + ", " + columnaOriginal + ") \n";
					
				}
			}
			
			//SIMPLE
			else{
				cadena += "Celula Compleja en (" + filaOriginal + " " + columnaOriginal + ") "
						+ "se mueve a (" + casillaNueva.getFila() + " " + casillaNueva.getColumna() + ")";
				
				if(hayCelula){
					cadena += "  --COME--\n";
				}
				
				else{
					cadena += "  --NO COME--\n";
				}	
			}
		}
		
		return cadena;
	}
	
	
}

