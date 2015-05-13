package deportes.beisbol.utils;

import deportes.beisbol.model.RecordBeisbol;

import java.util.LinkedHashSet;
import java.util.TreeMap;

public class EtapaBeisbolAux {
	private Short id;
	private String nombre;
	private TreeMap<String, TreeMap<String, LinkedHashSet<RecordBeisbol>>> vueltas;
	
	private int ganadosLider;
	private int perdidosLider;
	
	public EtapaBeisbolAux() {
		vueltas = new TreeMap<>();
	}
	
	public int getGanadosLider() {
		return ganadosLider;
	}

	public void setGanadosLider(int ganadosLider) {
		this.ganadosLider = ganadosLider;
	}

	public int getPerdidosLider() {
		return perdidosLider;
	}

	public void setPerdidosLider(int perdidosLider) {
		this.perdidosLider = perdidosLider;
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public TreeMap<String, TreeMap<String, LinkedHashSet<RecordBeisbol>>> getVueltas() {
		return vueltas;
	}

	public void setVueltas(
			TreeMap<String, TreeMap<String, LinkedHashSet<RecordBeisbol>>> vueltas) {
		this.vueltas = vueltas;
	}

	public void addVuelta(String nombreVuelta) {
		vueltas.put(nombreVuelta, new TreeMap<>());
	}
	
	public void addGrupo(String nombreGrupo, String nombreVuelta) {
		TreeMap<String, LinkedHashSet<RecordBeisbol>> vuelta = vueltas.get(nombreVuelta);
		vuelta.put(nombreGrupo, new LinkedHashSet<>());
		vueltas.put(nombreVuelta, vuelta);
	}
	
	public void setGrupo(String nombreGrupo, String nombreVuelta, LinkedHashSet<RecordBeisbol> records) {
		vueltas.get(nombreVuelta).put(nombreGrupo, records);
	}
	
	public String calculaJuegosDetras(short ganados, short perdidos) {
		float calculo;
		
		calculo = ((float)(ganadosLider - ganados) + (perdidos - perdidosLider)) / 2;
		
		if (calculo == 0) {
			return "";
		}
		
		return Float.toString(calculo);
	}
}
