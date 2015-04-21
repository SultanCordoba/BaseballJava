package deportes.beisbol.jpa.services;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import deportes.beisbol.jpa.model.Partido;
import deportes.beisbol.jpa.model.PartidoEquipo;
import deportes.beisbol.jpa.repository.EquipoRepository;
import deportes.beisbol.jpa.repository.EtapaRepository;
import deportes.beisbol.jpa.repository.ParqueRepository;
import deportes.beisbol.jpa.repository.PartidoRepository;
import deportes.beisbol.jpa.repository.VueltaRepository;
import deportes.beisbol.model.PartidoBeisbol;
import deportes.beisbol.service.PartidoService;

@Service
@Transactional
public class PartidoServiceImpl implements PartidoService {

	//private static final Logger logger = LoggerFactory.getLogger(PartidoServiceImpl.class);
	
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
					
			//partidoBase.setParque(parqueRepository.findOne(equipoLocal.getEquipo().getParqueId()));
			partidoBase.setParque(equipoLocal.getEquipo().getParque());
			partidoBase.setFechaActualizacion(new Timestamp(ahora.getTime()));
			
			partidoBase.addPartidoEquipo(equipoLocal);
			partidoBase.addPartidoEquipo(equipoVisita);
			
			partidoBase = partidoRepository.save(partidoBase);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public PartidoBeisbol findOne(Short id) {
		// TODO Auto-generated method stub
		return null;
	}

}
