package comandos;

import logica.Casilla;
import logica.Mundo;

/**
 * Clase para el comando crear celula compleja, se encarga de parsear el comando, su propia ayuda y ejecutarse
 *
 */
public class CrearCelulaCompleja extends Comando{

	private Casilla casilla;
	
	/**
	 * Contructor por defecto
	 */
	public CrearCelulaCompleja() {
	}
	
	/**
	 * Contructor con parametros fila y columna
	 * @param fila contiene la fila de la celula a crear
	 * @param columna contiene la columna de la celula a crear
	 */
	private CrearCelulaCompleja(int fila, int columna){
		this.casilla = new Casilla(fila, columna);
	}
	
	/**
	 * metodo que se encarga de ejecutar el comando
	 * @param mundo recibe un mundo sobre el que ejecutar el comando
	 */
	@Override
	public String ejecuta(Mundo mundo) {
		String string;
		
		if(mundo.crearCelulaCompleja(this.casilla.getFila(), this.casilla.getColumna())){
			string = "Creamos celula compleja en la poscion: (" + this.casilla.getFila() + " " + this.casilla.getColumna() + ")";
		}else{
			string = "No ha sido posible crear una celula compleja en la posicio: (" + this.casilla.getFila() + " " + this.casilla.getColumna() + ")";
		}
		
		return string;
	}
	
	/**
	 * metodo que se encarga de parsear el comando, decidiendo si la sintaxis se ajusta al comando o no
	 * @cadenaComando array de string que contiene los datos introducidos por el usuario
	 * @return Comando devuelve el comando instanciado
	 */
	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando crearCelulaCompleja = null;
		
		if (cadenaComando[0].equals("crearcelulacompleja") && cadenaComando.length == 3){
			crearCelulaCompleja = new CrearCelulaCompleja(Integer.parseInt(cadenaComando[1]), Integer.parseInt(cadenaComando[2]));
		}
		
		
		return crearCelulaCompleja;
	}
	
	/**
	 * metodo que se encarga de mostrar la ayuda del comando
	 * 
	 */
	@Override
	public String textoAyuda() {

		return "\n\t CREARCELULACOMPLEJA F C: crea una nueva celula en la posici�n (f,c) si es posible";
	}

}
