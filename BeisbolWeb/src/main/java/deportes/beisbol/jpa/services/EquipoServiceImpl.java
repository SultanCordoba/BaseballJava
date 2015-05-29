package deportes.beisbol.jpa.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import deportes.beisbol.converter.EquipoConverter;
import deportes.beisbol.converter.JugadorConverter;
import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.jpa.model.Jugador;
import deportes.beisbol.jpa.model.Participante;
import deportes.beisbol.jpa.model.Roster;
import deportes.beisbol.jpa.repository.EquipoRepository;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.FranquiciaBeisbol;
import deportes.beisbol.model.JugadorBeisbol;
import deportes.beisbol.model.TemporadaBeisbol;
import deportes.beisbol.utils.EquipoAux;
import deportes.beisbol.utils.JugadorComparator;
import deportes.beisbol.utils.RecordEtapa;
import deportes.beisbol.utils.RosterBeisbol;
import deportes.beisbol.web.model.EquipoModel;

@Service
@Transactional(readOnly = true)
public class EquipoServiceImpl implements EquipoService {

	@Autowired
	EquipoRepository equipoRepository;
	
	@Autowired
	ParticipanteService participanteService;
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	RosterService rosterService;
	
	@Override
	public Optional<EquipoBeisbol> findCampeon(TemporadaBeisbol t, Optional<String> idioma) {
		
		EquipoBeisbol equipoBeisbol = null;
		Equipo equipo = equipoRepository.findCampeon(t.getId());
		equipoBeisbol = EquipoConverter.convierteDeBase(equipo, idioma);
		
		return Optional.ofNullable(equipoBeisbol);
	}

	@Override
	public Optional<EquipoBeisbol> findOne(Short id, Optional<String> idioma) {
		
		EquipoBeisbol equipoBeisbol = null;
		Equipo equipo = equipoRepository.findOne(id);
		equipoBeisbol = EquipoConverter.convierteDeBase(equipo, idioma);
		
		return Optional.ofNullable(equipoBeisbol);
	}

	@Override
	public Collection<EquipoBeisbol> findByFranquicia(FranquiciaBeisbol arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EquipoModel creaEquipoModel(Short id, Optional<String> idioma) {
		EquipoModel equipoModelo = new EquipoModel();
		
		Participante participante = participanteService.findOne(id).get();
		
		// logger.info("Participante:" + participante.getId() + " Equipos: "  + participante.getEquipos().size());
		
		LinkedHashSet<EquipoAux> equipos = new LinkedHashSet<>();
		Iterator<Equipo> iteraEquipos = participante.getEquipos().iterator();
		EquipoAux equipoAux;
		Equipo equipoPaso;
		EquipoBeisbol equipoBeisbol;
		
		while (iteraEquipos.hasNext()) {
			equipoPaso = iteraEquipos.next();
			equipoAux = new EquipoAux();
			equipoBeisbol = EquipoConverter.convierteDeBase(equipoPaso, idioma);

			equipoAux.setEquipoBeisbol(equipoBeisbol);
			equipoAux.setRecords((LinkedHashSet<RecordEtapa>) recordService.findEtapaEquipo
					(equipoPaso.getFranquiciaHistorico().getFranquicia().getId(), 
					 participante.getTemporada().getId(), idioma));
			
			Iterator<Roster> iteraJugador = rosterService.findRosterByEquipo(equipoBeisbol).iterator();
			Roster roster;
			RosterBeisbol rosterBeisbol;
			JugadorBeisbol jugadorBeisbol;
			Jugador jugadorBase;
			
			// TODO: Comparar con temproada para determinar si el jugador inició o terminó la temporada con otro equipo.
			
			while (iteraJugador.hasNext()) {
				roster = iteraJugador.next();
				
				//jugadorBase = jugadorService.findOne(roster.getJugadorId()).get();
				jugadorBase = roster.getJugador();
				jugadorBeisbol = JugadorConverter.convierteDeBase(jugadorBase);
				
				rosterBeisbol = new RosterBeisbol();
				rosterBeisbol.setJugador(jugadorBeisbol);
				
				/* Asignando Posición */
				
				rosterBeisbol.setPosicion("");
				if (!Strings.isNullOrEmpty(roster.getPosicion())) {
					rosterBeisbol.setPosicion(roster.getPosicion());					
				}
				else {
					if (!Strings.isNullOrEmpty(jugadorBase.getPosicion())) {
						rosterBeisbol.setPosicion(jugadorBase.getPosicion());
					}
				}
				
				/* TODO: Calculando otra temporada */
				
				Iterator<Roster> listaRoster = 
					rosterService.hallarRosterByJugadorTemporada(
						roster.getJugador().getId(), 
						equipoPaso.getParticipante().getTemporada().getFechaInicio(), 
						equipoPaso.getParticipante().getTemporada().getFechaFin()).iterator();
				Roster pasoJugador;
				int i = 1;
				int numRoster = 0;
				
				
				while (listaRoster.hasNext()) {
					pasoJugador = listaRoster.next();
					numRoster++;
					
					if (pasoJugador.getEquipo().getId() == equipoPaso.getId()) {
						i = numRoster;
					}
				}
				
/*				logger.info(jugadorBeisbol.getNombreAbreviado() + " j_id=" + jugadorBeisbol.getId() +
						" i=" + i + 
						" numRoster=" + numRoster); */
				
				rosterBeisbol.setOtraTemporada("");
				if (i > 1) {
					rosterBeisbol.setOtraTemporada(rosterBeisbol.getOtraTemporada() + "^");
				}
				
				if (i < numRoster) {
					rosterBeisbol.setOtraTemporada(rosterBeisbol.getOtraTemporada() + "*");
				}
				
				// Calculando posición.
				
				if (rosterBeisbol.getPosicion().toUpperCase().contains("M")) {
					equipoAux.addManager(rosterBeisbol);
				}
				else {
					if (rosterBeisbol.getPosicion().toUpperCase().contains("P")) {
						equipoAux.addPitcher(rosterBeisbol);
					}
					else {
						equipoAux.addBateador(rosterBeisbol);
					}
				}				
			}
			
			Locale locale;
			if (idioma.isPresent()) {
				locale = new Locale.Builder().setLanguage(idioma.get()).build();
			}
			else {
				locale = new Locale.Builder().setLanguage("es").build();
			}
			
			ArrayList<RosterBeisbol> paso;
			paso = Lists.newArrayList(equipoAux.getBateadores());
			Collections.sort(paso, new JugadorComparator(locale));
			equipoAux.setBateadores(Sets.newLinkedHashSet(paso));
			
			paso = Lists.newArrayList(equipoAux.getPitchers());
			Collections.sort(paso, new JugadorComparator(locale));
			equipoAux.setPitchers(Sets.newLinkedHashSet(paso));
			
			equipos.add(equipoAux);
		}

		equipoModelo.setParticipante(participante);
		equipoModelo.setEquipos(equipos);
		
		return equipoModelo;
	}

}
