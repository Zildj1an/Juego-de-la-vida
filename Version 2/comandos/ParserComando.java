package comandos;

/**
 * 
 * Clase que se encarga del parseo del comando
 *
 */
public class ParserComando {
	private static Comando comando[] = {new Ayuda(), 
										new CrearCelulaCompleja(), 
										new CrearCelulaSimple(), 
										new EliminarCelula(),
										new Iniciar(),
										new Paso(),
										new Salir(),
										new Vaciar()};

	/**
	 * metodo que devuelve la ayuda de todos los posibles comandos
	 * @return string con la informacion de los comandos
	 */
	static public String AyudaComandos(){
		StringBuilder string = new StringBuilder();
		string.append("POSIBLES COMANDOS:");
		for(int i = 0; i < comando.length; i++){
			string.append(comando[i].textoAyuda());
		}
		
		return string.toString();
	}
	/**
	 * metodo que parsea el comando
	 * @param cadenas array con la informacion tecleada por el usuario
	 * @return comando instanciado
	 */
	static public Comando parseaComando(String[] cadenas){
		Comando comandoInstanciado = null;
		int contador = 0;
		
		while (comandoInstanciado == null && contador < comando.length){
			comandoInstanciado = comando[contador].parsea(cadenas);
			contador++;
		}
		
		return comandoInstanciado;
	}
	
}