package deportes.beisbol.utils;

import java.util.Comparator;

public class JugadorComparator implements Comparator<RosterBeisbol> {

	@Override
	public int compare(RosterBeisbol o1, RosterBeisbol o2) {
		
		if (o1.getJugador().getApellidoPaterno().compareTo(o2.getJugador().getApellidoPaterno()) != 0) {
			return o1.getJugador().getNombre().compareTo(o2.getJugador().getNombre());
		}
		else {
			return 0;
		}
	}

}
