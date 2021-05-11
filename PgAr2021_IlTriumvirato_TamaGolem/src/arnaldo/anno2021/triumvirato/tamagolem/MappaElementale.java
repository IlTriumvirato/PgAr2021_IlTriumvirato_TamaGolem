package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MappaElementale {
	private ArrayList<Integer> tabellaVantaggi;

	public MappaElementale() {
		this.tabellaVantaggi = new ArrayList<Integer>();
	}
	
	public MappaElementale(MappaElementale copia) {
		this.tabellaVantaggi = new ArrayList<Integer>();
		for(int i=0;i<copia.getSize();i++) {
			this.aggiungiValore(copia.getValore(i));
		}
	}

	public void aggiungiValore(int valore) {
		tabellaVantaggi.add(valore);		
	}
	
	public int getValore(int index) {
		return tabellaVantaggi.get(index);
	}
	
	public int getSize() {
		return tabellaVantaggi.size();
	}
	
}
