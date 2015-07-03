package deportes.beisbol.converter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.TreeMap;

/* import org.slf4j.Logger;
import org.slf4j.LoggerFactory; */

import com.google.common.base.Strings;

import deportes.beisbol.jpa.model.Etapa;
import deportes.beisbol.jpa.model.EtapaInt;
import deportes.beisbol.model.EtapaBeisbol;
import deportes.beisbol.model.RecordBeisbol;
import deportes.beisbol.utils.EtapaBeisbolAux;
import deportes.beisbol.utils.RecordComparator;

public class EtapaConverter {
	
	// private static final Logger logger = LoggerFactory.getLogger(EtapaConverter.class);
	
	public static EtapaBeisbol convierteDeBase(Etapa etapaBase, Optional<String> idioma) {
		EtapaBeisbol resultado = new EtapaBeisbol();
		
		resultado.setId(etapaBase.getId());
		resultado.setNombre(etapaBase.getNombre());
		
		String idiomaAbrev = Strings.nullToEmpty(idioma.get()).toUpperCase();
		
		if (!idiomaAbrev.equals("ES")) {
			Iterator<EtapaInt> etapasInt = etapaBase.getEtapaInts().iterator();
			EtapaInt etapaInt;
			
			while (etapasInt.hasNext()) {
				etapaInt = etapasInt.next();
				
				if (etapaInt.getIdioma().getAbreviatura().toUpperCase().equals(idiomaAbrev)) {
					resultado.setNombre(etapaInt.getNombre());
				}
			}
		}
		
		return resultado;
	}
	
	private static Iterator<RecordBeisbol> fusionarVueltas(
			TreeMap<String, LinkedHashSet<RecordBeisbol>> primeraVuelta, 
			TreeMap<String, LinkedHashSet<RecordBeisbol>> segundaVuelta) {
		
		TreeMap<String, RecordBeisbol> recordFusionado = new TreeMap<>();
		
		for (String grupo : primeraVuelta.keySet()) {			
			for (RecordBeisbol recordActual : primeraVuelta.get(grupo)) {
				recordFusionado.put(recordActual.getNombreAbrev() , recordActual);
			}
		}
		
		RecordBeisbol recordNuevo;
		RecordBeisbol recordPaso;
		
		NumberFormat formatter = new DecimalFormat("0.000");
		
		for (String grupo : segundaVuelta.keySet()) {
			for (RecordBeisbol recordActual : segundaVuelta.get(grupo)) {				
				recordNuevo = new RecordBeisbol();		
				recordPaso = recordFusionado.get(recordActual.getNombreAbrev());
				
				recordNuevo.setGanados((short) (recordPaso.getGanados() + recordActual.getGanados()));
				recordNuevo.setPerdidos((short) (recordPaso.getPerdidos() + recordActual.getPerdidos()));
				recordNuevo.setIdVuelta(recordActual.getIdVuelta());
				recordNuevo.setNombre(recordActual.getNombre());
				recordNuevo.setNombreAbrev(recordActual.getNombreAbrev());
				recordNuevo.setNombreGrupo(recordActual.getNombreGrupo());
				recordNuevo.setNombreVuelta(recordActual.getNombreVuelta());

				recordNuevo.setPorcentaje(recordNuevo.getGanados() / ((double) recordNuevo.getGanados() + recordNuevo.getPerdidos())); 				
				recordNuevo.setPctjeString(formatter.format(recordNuevo.getPorcentaje()));
				recordFusionado.put(recordNuevo.getNombreAbrev(), recordNuevo);
			}
		}
		
		ArrayList<RecordBeisbol> recordTemp = new ArrayList<RecordBeisbol>(recordFusionado.values());
		Collections.sort(recordTemp, new RecordComparator());
		
		return recordTemp.iterator();
	}
	
	@SuppressWarnings("unchecked")
	public static EtapaBeisbolAux convierteDeEntidad(EtapaBeisbol etapaBeisbol) {
		EtapaBeisbolAux resultado = new EtapaBeisbolAux();
	
		resultado.setId(etapaBeisbol.getId());
		resultado.setNombre(etapaBeisbol.getNombre());
		
		Iterator<RecordBeisbol> iteraRecords = (Iterator<RecordBeisbol>) etapaBeisbol.getRecords().iterator();
		String vueltaActual = "XXX";
		String grupoActual = "XXX";
		RecordBeisbol recordActual;
		
		LinkedHashSet<RecordBeisbol> recordGrupo = null;
		
		while (iteraRecords.hasNext()) {
			recordActual = iteraRecords.next();
			
			if (!vueltaActual.equalsIgnoreCase(recordActual.getNombreVuelta())) {				
				
				if (!grupoActual.equalsIgnoreCase("XXX")) {
					resultado.setGrupo(grupoActual, vueltaActual, recordGrupo);
				}
				
				vueltaActual = recordActual.getNombreVuelta();
				
				resultado.addVuelta(vueltaActual);
				grupoActual = "XXX";
			}
			
			if (!grupoActual.equalsIgnoreCase(recordActual.getNombreGrupo())) {
				
				if (!grupoActual.equalsIgnoreCase("XXX")) {
					resultado.setGrupo(grupoActual, vueltaActual, recordGrupo);
				}
				
				grupoActual = Strings.nullToEmpty(recordActual.getNombreGrupo());
				
				resultado.addGrupo(grupoActual, vueltaActual);
				recordGrupo = new LinkedHashSet<>();
				
				resultado.setGanadosLider(recordActual.getGanados());
				resultado.setPerdidosLider(recordActual.getPerdidos());
			}
			
			recordActual.setJuegosDetras(resultado.calculaJuegosDetras(recordActual.getGanados(), recordActual.getPerdidos()));
			recordGrupo.add(recordActual);
		}
		
		if (!grupoActual.equalsIgnoreCase("XXX") && !(vueltaActual.equalsIgnoreCase("XXX"))) {
			resultado.setGrupo(grupoActual, vueltaActual, recordGrupo);
		}
		
		if (resultado.getVueltas().keySet().size() > 1) {
			Iterator<String> totalVueltas = resultado.getVueltas().keySet().iterator();
			
			grupoActual = "XXX";
			recordGrupo = new LinkedHashSet<>();
			Iterator<RecordBeisbol> recordTotales =
					fusionarVueltas(resultado.getVueltas().get(totalVueltas.next()),
							resultado.getVueltas().get(totalVueltas.next()));
			RecordBeisbol recordTemporal;
						
			vueltaActual = "Total";
			resultado.addVuelta(vueltaActual);
			while (recordTotales.hasNext()) {
				recordTemporal = recordTotales.next();
				
				if (!grupoActual.equalsIgnoreCase(recordTemporal.getNombreGrupo())) {
					
					if (!grupoActual.equalsIgnoreCase("XXX")) {
						resultado.setGrupo(grupoActual, vueltaActual, recordGrupo);
					}
					
					grupoActual = recordTemporal.getNombreGrupo();
					
					resultado.addGrupo(grupoActual, vueltaActual);
					recordGrupo = new LinkedHashSet<>();
					
					resultado.setGanadosLider(recordTemporal.getGanados());
					resultado.setPerdidosLider(recordTemporal.getPerdidos());
				}
				
				recordTemporal.setJuegosDetras(resultado.calculaJuegosDetras(recordTemporal.getGanados(), recordTemporal.getPerdidos()));
				recordGrupo.add(recordTemporal);
			}
			
			if (!grupoActual.equalsIgnoreCase("XXX") && !(vueltaActual.equalsIgnoreCase("XXX"))) {
				resultado.setGrupo(grupoActual, vueltaActual, recordGrupo);
			}
			
		}
		
		return resultado;
	}
}
