package comandos;

import logica.Mundo;

public class Salir extends Comando{

	/**
	 * metodo que se encarga de ejecutar el comando
	 * @param mundo recibe un mundo sobre el que ejecutar el comando
	 */
	@Override
	public String ejecuta(Mundo mundo) {
		mundo.setSimulacionTerminada();
		
		return "Fin de simulaci�n";
	}

	/**
	 * metodo que se encarga de parsear el comando, decidiendo si la sintaxis se ajusta al comando o no
	 * @cadenaComando array de string que contiene los datos introducidos por el usuario
	 * @return Comando devuelve el comando instanciado
	 */
	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando salir = null;
		
		if(cadenaComando[0].equals("salir")){
			salir = new Salir();
		}

		
		return salir;
	}

	/**
	 * metodo que se encarga de mostrar la ayuda del comando
	 * 
	 */
	@Override
	public String textoAyuda() {
		
		return "\n\t SALIR: cierra la aplicaci�n";
	}

}
