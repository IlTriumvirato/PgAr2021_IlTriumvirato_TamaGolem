package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MappaElementale {
	ArrayList<Integer> tabellaVantaggi;

	public MappaElementale() {
		this.tabellaVantaggi = new ArrayList<Integer>();
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
