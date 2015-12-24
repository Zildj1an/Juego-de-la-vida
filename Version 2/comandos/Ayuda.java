package comandos;

import logica.Mundo;

/**
 * Clase para el comando ayuda, se encarga de parsear el comando, su propia ayuda y ejecutarse
 *
 */
public class Ayuda extends Comando{

	/**
	 * metodo que se encarga de ejecutar el comando
	 * @param mundo recibe un mundo sobre el que ejecutar el comando
	 */
	@Override
	public String ejecuta(Mundo mundo) {
		return ParserComando.AyudaComandos();
		
		
	}

	/**
	 * metodo que se encarga de parsear el comando, decidiendo si la sintaxis se ajusta al comando o no
	 * @cadenaComando array de string que contiene los datos introducidos por el usuario
	 * @return Comando devuelve el comando instanciado
	 */
	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando ayuda = null;
		
		if (cadenaComando[0].equals("ayuda")){
			ayuda = new Ayuda();
		} 
		
		return ayuda;
	}

	/**
	 * metodo que se encarga de mostrar la ayuda del comando
	 * 
	 */
	
	@Override
	public String textoAyuda() {

		return "\n\t AYUDA: muestra la ayuda";
	}

}
