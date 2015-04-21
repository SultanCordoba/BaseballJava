package deportes.beisbol.service;

import deportes.beisbol.model.PartidoBeisbol;

public interface PartidoService {
	public boolean save(PartidoBeisbol partido, short etapaId, byte vueltaId);
	
	public PartidoBeisbol findOne(Short id);
}
