/**
 * 
 */
package deportes.beisbol.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects.ToStringHelper;

import deportes.core.interfaces.DeportePartidoInterfaz;
import deportes.core.interfaces.DeporteRecordInterfaz;
import deportes.core.interfaces.EtapaInterfaz;

/**
 * @author juanfriverap
 *
 */
public class EtapaBeisbol implements EtapaInterfaz {

	private Short id;
	private String nombre;
	private LinkedHashSet<RecordBeisbol> records;
	
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
		return nombre;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.DeporteBasicoInterfaz#setNombre(java.lang.String)
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.EtapaInterfaz#getPartidos()
	 */
	@Override
	public Collection<? extends DeportePartidoInterfaz> getPartidos() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.EtapaInterfaz#setPartidos(java.util.Collection)
	 */
	@Override
	public void setPartidos(
			Collection<? extends DeportePartidoInterfaz> partidos) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.EtapaInterfaz#getRecords()
	 */
	@Override
	public Collection<? extends DeporteRecordInterfaz> getRecords() {
		return records;
	}

	/* (non-Javadoc)
	 * @see deportes.core.interfaces.EtapaInterfaz#setRecords(java.util.Collection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@JsonDeserialize(as=LinkedHashSet.class)
	public void setRecords(Collection<? extends DeporteRecordInterfaz> records) {
		// TODO Auto-generated method stub
		this.records = (LinkedHashSet<RecordBeisbol>) records;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (getClass() != object.getClass()) {
			return false;
		}

		final EtapaBeisbol other = (EtapaBeisbol) object;
		return Objects.equals(this.nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.nombre);
	}

	@Override
	public String toString() {
		
		ToStringHelper toStringHelper = com.google.common.base.MoreObjects.toStringHelper(this);

		toStringHelper.add("id", this.id).add("nombre", this.nombre);
		
		return toStringHelper.toString();
	}

}
