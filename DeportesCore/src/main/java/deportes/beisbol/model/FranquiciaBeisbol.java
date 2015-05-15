/**
 * 
 */
package deportes.beisbol.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.google.common.base.MoreObjects.ToStringHelper;

import deportes.core.interfaces.RangoFechaInterfaz;
import deportes.core.interfaces.FranquiciaInterfaz;

/**
 * @author juanfriverap
 *
 */
public class FranquiciaBeisbol implements FranquiciaInterfaz {

	private Short id;
	private String nombre;
	private Set<RangoFechaBeisbol> nombres;
	private Set<RangoFechaBeisbol> escudos;
	private Set<RangoFechaBeisbol> parques;
	private String pais;
	
	public FranquiciaBeisbol() {
		nombres = new LinkedHashSet<>();
		escudos = new LinkedHashSet<>();
		parques = new LinkedHashSet<>();
	}
	
	public FranquiciaBeisbol(Short id) {
		this();
		this.id = id;
	}
	
	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {		
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.FranquiciaInterfaz#getNombres()
	 */
	@Override
	public Collection<? extends RangoFechaInterfaz> getNombres() {
		return this.nombres;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.FranquiciaInterfaz#setNombres(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setNombres(
			Collection<? extends RangoFechaInterfaz> nombres) {
		this.nombres = (Set<RangoFechaBeisbol>) nombres;

	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.FranquiciaInterfaz#getEscudos()
	 */
	@Override
	public Collection<? extends RangoFechaInterfaz> getEscudos() {
		return this.escudos;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.FranquiciaInterfaz#setEscudos(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setEscudos(
			Collection<? extends RangoFechaInterfaz> escudos) {
		this.escudos = (Set<RangoFechaBeisbol>) escudos;

	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.FranquiciaInterfaz#getParques()
	 */
	@Override
	public Collection<? extends RangoFechaInterfaz> getParques() {
		return this.parques;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.FranquiciaInterfaz#setParques(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setParques(
			Collection<? extends RangoFechaInterfaz> parques) {
		this.parques = (Set<RangoFechaBeisbol>) parques;
	}
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (getClass() != object.getClass()) {
			return false;
		}

		final FranquiciaBeisbol other = (FranquiciaBeisbol) object;
		return Objects.equals(this.id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public String toString() {
		
		ToStringHelper toStringHelper = com.google.common.base.MoreObjects.toStringHelper(this);

		toStringHelper.add("id", this.id).add("nombre", nombre);
		
		try {
			toStringHelper.add("nombres", nombres.size());
		} catch (NullPointerException npe) {
			toStringHelper.add("nombres", 0);
		}
		
		try {
			toStringHelper.add("escudos", escudos.size());
		} catch (NullPointerException npe) {
			toStringHelper.add("escudos", 0);
		}
		
		try {
			toStringHelper.add("parques", parques.size());
		} catch (NullPointerException npe) {
			toStringHelper.add("parques", 0);
		}
		
		return toStringHelper.toString();
	}



}
