package comandos;

import logica.Mundo;

/**
 * 
 * Clase q
 *
 */
public class Vaciar extends Comando{

	/**
	 * metodo que se encarga de ejecutar el comando
	 * @param mundo recibe un mundo sobre el que ejecutar el comando
	 */
	@Override
	public String ejecuta(Mundo mundo) {
		mundo.vaciar();
		
		return "";
	}

	/**
	 * metodo que se encarga de parsear el comando, decidiendo si la sintaxis se ajusta al comando o no
	 * @cadenaComando array de string que contiene los datos introducidos por el usuario
	 * @return Comando devuelve el comando instanciado
	 */
	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando vaciar = null;
		
		if(cadenaComando[0].equals("vaciar")){
			vaciar = new Vaciar();
		}

		
		return vaciar;
	}

	/**
	 * metodo que se encarga de mostrar la ayuda del comando
	 * 
	 */
	@Override
	public String textoAyuda() {	

		return "\n\t VACIAR: crea un mundo vac�o";
	}

}
