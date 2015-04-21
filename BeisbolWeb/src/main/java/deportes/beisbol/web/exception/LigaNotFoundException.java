package deportes.beisbol.web.exception;

public class LigaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6779250917217620051L;

	public LigaNotFoundException(Byte id) {
		super("No hay una liga '" + id + "'.");
	}

}
