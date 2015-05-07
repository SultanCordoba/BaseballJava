package deportes.beisbol.jpa.services;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import deportes.beisbol.jpa.model.Partido;
import deportes.beisbol.jpa.model.PartidoEquipo;
import deportes.beisbol.jpa.model.Record;
import deportes.beisbol.jpa.repository.EquipoRepository;
import deportes.beisbol.jpa.repository.EtapaRepository;
import deportes.beisbol.jpa.repository.ParqueRepository;
import deportes.beisbol.jpa.repository.PartidoRepository;
import deportes.beisbol.jpa.repository.RecordRepository;
import deportes.beisbol.jpa.repository.VueltaRepository;
import deportes.beisbol.model.PartidoBeisbol;
import deportes.beisbol.service.PartidoService;

@Service
@Transactional
public class PartidoServiceImpl implements PartidoService {

	private static final Logger logger = LoggerFactory.getLogger(PartidoServiceImpl.class);
	
	@Autowired
	PartidoRepository partidoRepository;
	
	@Autowired
	EtapaRepository etapaRepository;
	
	@Autowired
	VueltaRepository vueltaRepository;
	
	@Autowired
	ParqueRepository parqueRepository;
	
	@Autowired
	EquipoRepository equipoRepository;
	
	@Autowired
	RecordRepository recordRepository;
	
	@Override
	public boolean save(PartidoBeisbol partido, short etapaId, byte vueltaId) {	
		
		try {
			Partido partidoBase = new Partido();
			
			partidoBase.setFechaRealizacion(Date.from(partido.getFechaRealizacion().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			partidoBase.setComentario(partido.getComentario());
			partidoBase.setEntradas((byte) partido.getEntradas());
			partidoBase.setJuegoDia((byte) partido.getNumJuego());
			partidoBase.setEtapa(etapaRepository.findOne(etapaId));		
			partidoBase.setVuelta(vueltaRepository.findOne(vueltaId));		
			partidoBase.setPartidoMilb(partido.getClaveMilb());
			
			Date ahora = new Date();
			
			// HashSet<PartidoEquipo> partidoEquipos = new HashSet<>();
			PartidoEquipo equipoLocal = new PartidoEquipo();
			equipoLocal.setCarreras((byte) partido.getEquipoLocal().getScore());
			equipoLocal.setHits((byte) partido.getEquipoLocal().getHits());
			equipoLocal.setErrores((byte) partido.getEquipoLocal().getErrores());
			equipoLocal.setLocalia("L");
			equipoLocal.setEquipo(equipoRepository.findAbreviaturaLiga
					(partido.getEquipoLocal().getEquipo().getSiglas(), 
					 partidoBase.getEtapa().getTemporada().getLigaHistorico().getSiglas(),
					 partidoBase.getEtapa().getTemporada().getId()));
			equipoLocal.setFechaActualizacion(new Timestamp(ahora.getTime()));
			
			PartidoEquipo equipoVisita = new PartidoEquipo();
			equipoVisita.setCarreras((byte) partido.getEquipoVisita().getScore());
			equipoVisita.setHits((byte) partido.getEquipoVisita().getHits());
			equipoVisita.setErrores((byte) partido.getEquipoVisita().getErrores());
			equipoVisita.setLocalia("V");
			equipoVisita.setEquipo(equipoRepository.findAbreviaturaLiga
					(partido.getEquipoVisita().getEquipo().getSiglas(), 
				     partidoBase.getEtapa().getTemporada().getLigaHistorico().getSiglas(),
					 partidoBase.getEtapa().getTemporada().getId()));
			equipoVisita.setFechaActualizacion(new Timestamp(ahora.getTime()));
			
			if (partido.getEquipoVisita().getScore() > partido.getEquipoLocal().getScore()) {
				equipoVisita.setGano((byte) 1);
				equipoLocal.setPerdio((byte) 1);
			}
			else {
				if (partido.getEquipoLocal().getScore() > partido.getEquipoVisita().getScore()) {
					equipoVisita.setPerdio((byte) 1);
					equipoLocal.setGano((byte) 1);
				}
			}
					
			partidoBase.setParque(equipoLocal.getEquipo().getParque());
			partidoBase.setFechaActualizacion(new Timestamp(ahora.getTime()));
			
			partidoBase.addPartidoEquipo(equipoLocal);
			partidoBase.addPartidoEquipo(equipoVisita);
			
			partidoBase = partidoRepository.save(partidoBase);
			
			/* Buscar el Record para tanto el equipo local, como el equipo visitante */
			
			Iterator<PartidoEquipo> iteraEquipos = partidoBase.getPartidoEquipos().iterator();
			PartidoEquipo partidoTemp;
			Optional<Record> recordTemp;
			Record registroEquipo = null;
			
			while (iteraEquipos.hasNext()) {
				partidoTemp = iteraEquipos.next();
				
				recordTemp = recordRepository.findByEquipo(partidoTemp.getEquipo().getId(), partidoBase.getEtapa(), partidoBase.getVuelta());
				
				if (recordTemp.isPresent()) {
					registroEquipo = recordTemp.get();
					
					if (partidoTemp.getGano() > 0) {
						registroEquipo.setGanados((byte)(registroEquipo.getGanados() + 1));
					}
					
					if (partidoTemp.getPerdio() > 0) {
						registroEquipo.setPerdidos((byte)(registroEquipo.getPerdidos() + 1));
					}
				}
				else {
					registroEquipo = new Record();
					registroEquipo.setEtapa(partidoBase.getEtapa());
					registroEquipo.setGanados((byte) registroEquipo.getGanados());
					registroEquipo.setPerdidos((byte) registroEquipo.getPerdidos());
					registroEquipo.setParticipante(partidoTemp.getEquipo().getParticipante());
					registroEquipo.setVuelta(partidoBase.getVuelta());
					registroEquipo.setFechaActualizacion(new Timestamp(ahora.getTime()));
				}
								
				recordRepository.save(registroEquipo);
			}
			
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public PartidoBeisbol findOne(Short id) {
		// TODO Auto-generated method stub
		return null;
	}

}
