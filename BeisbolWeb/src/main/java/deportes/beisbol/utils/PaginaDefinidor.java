package deportes.beisbol.utils;

import org.springframework.data.domain.Sort;



public class PaginaDefinidor {
	private int longitud;
	private int inicio;
	
	public PaginaDefinidor() {
		inicio = 1;
		longitud = 10;
	}
	
	public int getLongitud() {
		return longitud;
	}
	
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	
	public int getInicio() {
		return inicio;
	}
	
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	
	public int getNumeroPagina() {
		return (inicio + longitud) / longitud;
	}
	
	public Sort getSort(String campo) {
		return new Sort(Sort.Direction.ASC, campo);
	}
	
}
