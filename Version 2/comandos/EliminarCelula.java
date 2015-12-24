package comandos;

import logica.Casilla;
import logica.Mundo;

public class EliminarCelula extends Comando{
	
	private Casilla casilla;
	
	/**
	 * contrucctor por defecto
	 */
	public EliminarCelula() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * contructor con fila y columna
	 * @param fila fila de la que se va a borrar la celula
	 * @param columna columna de la que se va a borral la celula
	 */
	private EliminarCelula(int fila, int columna){
		this.casilla = new Casilla(fila, columna);
	}
	
	/**
	 * metodo que se encarga de ejecutar el comando
	 * @param mundo recibe un mundo sobre el que ejecutar el comando
	 */
	@Override
	public String ejecuta(Mundo mundo) {
		String string;
		if (mundo.eliminarCelula(this.casilla.getFila(), this.casilla.getColumna())){
			string = "Eliminamos la celula de la posicion: (" + this.casilla.getFila() + " " + this.casilla.getColumna() + ")";
		}else{
			string = "No ha sido posible eliminar la celula de la posicion:(" + this.casilla.getFila() + " " + this.casilla.getColumna() + ")";
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
		Comando eliminarCelula = null;

		if (cadenaComando[0].equals("eliminarcelula") && cadenaComando.length == 3){
			eliminarCelula = new EliminarCelula(Integer.parseInt(cadenaComando[1]), Integer.parseInt(cadenaComando[2]));
		}
		
		return eliminarCelula;
	}

	/**
	 * metodo que se encarga de mostrar la ayuda del comando
	 * 
	 */
	@Override
	public String textoAyuda() {
		
		return "\n\t ELIMINARCELULACOMPLEJA F C: elimina una celula de la posici�n (f,c) si es posible";
	}

}
