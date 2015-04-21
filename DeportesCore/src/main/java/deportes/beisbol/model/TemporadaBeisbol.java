/**
 * 
 */
package deportes.beisbol.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

import deportes.core.interfaces.EtapaInterfaz;
import deportes.core.interfaces.TemporadaInterfaz;

/**
 * @author juanfriverap
 *
 */
@JsonTypeName("tempBeisbol")
public class TemporadaBeisbol implements TemporadaInterfaz {

	private Short id;
	private String nombre;
	private LinkedHashSet<EtapaBeisbol> etapas;
	private EquipoBeisbol campeon;
	
	public TemporadaBeisbol() {
		this.etapas = new LinkedHashSet<>();
	}
	
	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.DeporteBasicoInterfaz#getNombre()
	 */
	@Override
	public String getNombre() {
		return this.nombre;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.DeporteBasicoInterfaz#setNombre(java.lang.String)
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.DeporteTemporadaInterfaz#getEtapas()
	 */
	@Override
	public Collection<? extends EtapaInterfaz> getEtapas() {
		return etapas;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.DeporteTemporadaInterfaz#setEtapas(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@JsonDeserialize(as=LinkedHashSet.class)
	public void setEtapas(Collection<? extends EtapaInterfaz> etapas) {
		this.etapas = (LinkedHashSet<EtapaBeisbol>) etapas;
	}
	
	public EquipoBeisbol getCampeon() {
		return campeon;
	}

	public void setCampeon(EquipoBeisbol campeon) {
		this.campeon = campeon;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (getClass() != object.getClass()) {
			return false;
		}

		final TemporadaBeisbol other = (TemporadaBeisbol) object;
		return Objects.equals(this.nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.nombre);
	}

	@Override
	public String toString() {
		
		ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);

		toStringHelper.add("id", this.id).add("nombre", this.nombre);
		
		try {
			toStringHelper.add("etapas", etapas.size());
		} catch (NullPointerException npe) {
			toStringHelper.add("etapas", 0);
		}
		
		try {
			toStringHelper.add("campeon", campeon.getNombre());
		} catch (NullPointerException npe) { }
		
		return toStringHelper.toString();
	}

}
