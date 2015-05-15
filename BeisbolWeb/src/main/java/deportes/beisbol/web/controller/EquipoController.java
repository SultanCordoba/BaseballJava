package deportes.beisbol.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import deportes.beisbol.converter.EquipoConverter;
import deportes.beisbol.converter.JugadorConverter;
import deportes.beisbol.converter.ParticipanteConverter;
import deportes.beisbol.jpa.model.Equipo;
import deportes.beisbol.jpa.model.Jugador;
import deportes.beisbol.jpa.model.Participante;
import deportes.beisbol.jpa.model.Roster;
import deportes.beisbol.jpa.services.JugadorService;
import deportes.beisbol.jpa.services.ParticipanteService;
import deportes.beisbol.jpa.services.RecordService;
import deportes.beisbol.jpa.services.RosterService;
import deportes.beisbol.model.EquipoBeisbol;
import deportes.beisbol.model.JugadorBeisbol;
import deportes.beisbol.utils.ConstructorBreadcrumb;
import deportes.beisbol.utils.EquipoAux;
import deportes.beisbol.utils.JugadorComparator;
import deportes.beisbol.utils.RecordEtapa;
import deportes.beisbol.utils.RosterBeisbol;

@Controller
@RequestMapping("/equipo")
public class EquipoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EquipoController.class);
	
	@Autowired
	ParticipanteService participanteService;
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	RosterService rosterService;
	
	@Autowired
	JugadorService jugadorService;

	@RequestMapping(value = "{id}/show/{zona}", method = RequestMethod.GET)
	public String getEquipo(@PathVariable short id, @PathVariable String zona, 
			Model model, Locale locale) {
		
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
			equipoBeisbol = EquipoConverter.convierteDeBase(equipoPaso);
			equipoAux.setEquipoBeisbol(EquipoConverter.convierteDeBase(equipoPaso));
			equipoAux.setRecords((LinkedHashSet<RecordEtapa>) recordService.findEtapaEquipo
					(equipoPaso.getFranquiciaHistorico().getFranquicia().getId(), 
					 participante.getTemporada().getId(), Optional.of(locale.getLanguage())));
			
			Iterator<Roster> iteraJugador = rosterService.findRosterByEquipo(equipoBeisbol).iterator();
			Roster roster;
			RosterBeisbol rosterBeisbol;
			JugadorBeisbol jugadorBeisbol;
			Jugador jugadorBase;
			
			// TODO: Comparar con temproada para determinar si el jugador inició o terminó la temporada con otro equipo.
			
			while (iteraJugador.hasNext()) {
				roster = iteraJugador.next();
				
				jugadorBase = jugadorService.findOne(roster.getJugadorId()).get();
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
			
			ArrayList<RosterBeisbol> paso;
			paso = Lists.newArrayList(equipoAux.getBateadores());
			Collections.sort(paso, new JugadorComparator());
			equipoAux.setBateadores(Sets.newLinkedHashSet(paso));
			
			paso = Lists.newArrayList(equipoAux.getPitchers());
			Collections.sort(paso, new JugadorComparator());
			equipoAux.setPitchers(Sets.newLinkedHashSet(paso));
			
			equipos.add(equipoAux);
		}
		
		model.addAttribute("equipos", equipos);
		
		model.addAttribute("menuBread", ConstructorBreadcrumb.construyeEquipo(participante, zona));
		
		String menuActivo = "INICIO";
		
		switch (zona.toUpperCase()) {
		case "E":
			menuActivo = participante.getTemporada().getNombre();
			break;
			
		case "T":
			menuActivo = ParticipanteConverter.nombreParticipante(participante, false, Optional.of(locale.getLanguage()));
			break;
		}
		
		model.addAttribute("menuActivo", menuActivo);
		
		return "../templates/equipo/show";
	}
}
