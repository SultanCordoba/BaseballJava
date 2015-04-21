/**
 * 
 */
package deportes.beisbol.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;

import deportes.core.interfaces.FranquiciaInterfaz;
import deportes.core.interfaces.LigaInterfaz;
import deportes.core.interfaces.TemporadaInterfaz;

/**
 * @author juanfriverap
 *
 */
public class LigaBeisbol implements LigaInterfaz {

	private Byte id;
	private String nombre;
	private String siglas;
	
	private LinkedHashSet<TemporadaBeisbol> temporadas;
	private LinkedHashSet<FranquiciaBeisbol> franquicias;
	
	public LigaBeisbol() {
		this.temporadas = new LinkedHashSet<>();
		this.franquicias = new LinkedHashSet<>();
	}
	
	public Byte getId() {
		return id;
	}

	public void setId(Byte id) {
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

	@Override
	public String getSiglas() {
		return siglas;
	}

	@Override
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	@Override
	public Collection<? extends TemporadaInterfaz> getTemporadas() {
		return temporadas;
	}

	@SuppressWarnings("unchecked")
	@Override
	@JsonDeserialize(as=LinkedHashSet.class)
	public void setTemporadas(
			Collection<? extends TemporadaInterfaz> temporadas) {
		this.temporadas = (LinkedHashSet<TemporadaBeisbol>) temporadas;
	}

	@Override
	public Collection<? extends FranquiciaInterfaz> getFranquicias() {
		// TODO Auto-generated method stub
		return franquicias;
	}

	@SuppressWarnings("unchecked")
	@Override
	@JsonDeserialize(as=LinkedHashSet.class)
	public void setFranquicias(
			Collection<? extends FranquiciaInterfaz> franquicias) {
		this.franquicias = (LinkedHashSet<FranquiciaBeisbol>) franquicias;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (getClass() != object.getClass()) {
			return false;
		}

		final LigaBeisbol other = (LigaBeisbol) object;
		return Objects.equals(this.nombre, other.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.nombre);
	}

	@Override
	public String toString() {
		
		ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);

		toStringHelper.add("id", this.id).add("nombre", this.nombre)
				.add("siglas", this.siglas);
		
		try {
			toStringHelper.add("temporadas", temporadas.size());
		} catch (NullPointerException npe) {
			toStringHelper.add("temporadas", 0);
		}
		
		try {
			toStringHelper.add("franquicias", franquicias.size());
		} catch (NullPointerException npe) {
			toStringHelper.add("franquicias", 0);
		}
		
		return toStringHelper.toString();
	}
}
