package comandos;

import logica.Casilla;
import logica.Mundo;

/**
 * Clase para el comando crear celula simple, se encarga de parsear el comando, su propia ayuda y ejecutarse
 *
 */
public class CrearCelulaSimple extends Comando{
	
	private Casilla casilla;
	
	/**
	 * contructor por defecto
	 */
	public CrearCelulaSimple() {
	}
	
	/**
	 * contructor que contiene la fila y columna de la celula
	 * @param fila fila en la que se crea la celula
	 * @param columna columna en la que se crea la celula
	 */
	private CrearCelulaSimple(int fila, int columna) {
		this.casilla = new Casilla(fila, columna);
	}
	
	/**
	 * metodo que se encarga de ejecutar el comando
	 * @param mundo recibe un mundo sobre el que ejecutar el comando
	 */
	@Override
	public String ejecuta(Mundo mundo) {
		String string;
		
		if (mundo.crearCelulaSimple(casilla.getFila(), casilla.getColumna())){
			string ="Creamos nueva celula simple en la posicion: ("  + this.casilla.getFila() + " " + this.casilla.getColumna() + ")";
		}else{
			string = "No ha sido posible eliminar la celula simple en la posicion: ("  + this.casilla.getFila() + " " + this.casilla.getColumna() + ")";
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
		Comando crearCelulaSimple = null;

		if (cadenaComando[0].equals("crearcelulasimple") && cadenaComando.length == 3){
			crearCelulaSimple = new CrearCelulaSimple(Integer.parseInt(cadenaComando[1]), Integer.parseInt(cadenaComando[2]));
		}
		
		return crearCelulaSimple;
	}

	/**
	 * metodo que se encarga de mostrar la ayuda del comando
	 * 
	 */
	@Override
	public String textoAyuda() {
		
		return "\n\t CREARCELULASIMPLE F C: crea una nueva celula en la posici�n (f,c) si es posible";
	}

}
