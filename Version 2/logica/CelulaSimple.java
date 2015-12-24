package logica;



/**
 * Clase celula simple, contiene la informacion relevante sobre la celula y sus metodos para poder
 * realizar todas las operaciones con ella
 *
 */
public class CelulaSimple extends Celula{
	private final int NUMPASOSNOMOVIDOS = 2;
	private final int NUMPASOSREPRODUCCION = 1;
	private int numPasosNoMovidos;
	private int numPasosReproduccion;

	
	/**
	 * Constructor que inicializa los pasos no movidos y los pasos de reproduccion
	 */
	public CelulaSimple(){
		this.numPasosNoMovidos = this.NUMPASOSNOMOVIDOS;
		this.numPasosReproduccion = this.NUMPASOSREPRODUCCION;
		comestible = true;
	}

	/**
	 * metodo encargado de que se ejecute la celula
	 * @param f fila en la que se encuentra la celula a ejecutar
	 * @param c columna en la que se encuentra la celula a ejecutar
	 * @param superficie superficie sobre la que se ejecutara el movimiento
	 * @return Casilla de destino de la celula
	 */
	public Casilla ejecutaMovimiento(int f, int c, Superficie superficie) {
		Casilla casillaOcupada = new Casilla(f, c);
		Casilla casillaNueva = null;
		
		//Primero comprobarmos si le toca morir
		if(superficie.celulaMuere(f, c)){
			superficie.eliminarCelula(f, c);
		}
		
		//Si no le toca morir comprobamos si se reproduce
		else if (superficie.celulaReproduce(f,c)){
			casillaNueva = superficie.getCasillasLibre(casillaOcupada);
			
			if(casillaNueva == null){
				superficie.eliminarCelula(f, c);
			}
				
			else{
				superficie.reproduceCelula(f, c, casillaNueva);
			}
				
		}
			
		else{
			casillaNueva = superficie.getCasillasLibre(casillaOcupada);
			
			if(casillaNueva != null){
				this.disminuyePasosReproduccion();
				superficie.moverCelula(f, c, casillaNueva.getFila(), casillaNueva.getColumna());
			}
			
			else{
				this.disminuyePasosNoMovidos();
			}	
		}	
		
		
		/*if (superficie.celulaMuere(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna(), MAX_PASOS_SIN_MOVER) && superficie.eliminarCelula(casillasOcupadas[i].getFila(), casillasOcupadas[i].getColumna())){
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
		}*/
		
		
		
		/*	
			if(casillaLibre != null){
				if (moverCelula(fila, columna, casillaLibre.getFila(), casillaLibre.getColumna())){
					cadena += "Movimiento de (" + fila + " " + columna + ") a (" + casillaLibre.getFila() + " " + casillaLibre.getColumna() + ") \n";
					//superficie[casillaLibre.getFila()][casillaLibre.getColumna()].actualizarPasosReproduccion();
				}
			}else{
				//superficie[fila][columna].actualizarPasosNoMovidos();
				}
				
				return cadena;
			} 
		 * */
		
		//Si no se mueve, se restan los pasos no movidos
		
		return casillaNueva;
	}

	public boolean esComestible() {
		
		return comestible;
	}
	
	public String toString(){
		String cadena;
		
		//return " X ";
		cadena = this.numPasosNoMovidos + "-" + this.numPasosReproduccion;
		
		return cadena;
	}
	
	
	
	public boolean muere(){
		
		return this.numPasosNoMovidos == 0;
	}
	
	public boolean reproduce(){
		
		return this.numPasosReproduccion == 0;
	}
	
	public void disminuyePasosNoMovidos(){
		
		this.numPasosNoMovidos--;
	}
	
	public void disminuyePasosReproduccion(){
		
		this.numPasosReproduccion--;
	}
}