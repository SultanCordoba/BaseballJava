package deportes.beisbol.utils;

import java.util.Comparator;

import deportes.beisbol.model.RecordBeisbol;

public class RecordComparator implements Comparator<RecordBeisbol> {
	
	@Override
	public int compare(RecordBeisbol o1, RecordBeisbol o2) {
    	return o1.getIdVuelta() < o2.getIdVuelta() ?
    			-1 : o1.getIdVuelta() > o2.getIdVuelta() ?
    			1 : doSecondaryOrderSort(o1, o2);
    }
    
    private int doSecondaryOrderSort(RecordBeisbol o1, RecordBeisbol o2) {
    	return o1.getNombreGrupo().compareToIgnoreCase(o2.getNombreGrupo()) != 0 ?
        		o1.getNombreGrupo().compareToIgnoreCase(o2.getNombreGrupo()) :		            	
        		doTertiaryOrderSort(o1,o2);
    }

    //If 'TimeStarted' property is equal sorts by 'TimeEnded' property
    private int doTertiaryOrderSort(RecordBeisbol o1, RecordBeisbol o2) {
        return o1.getPorcentaje()>o2.getPorcentaje() ?
        		-1 : o1.getPorcentaje()<o2.getPorcentaje() ? 1:0;
    }
}
