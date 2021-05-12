package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;

public class GlobalValues {
	private static ArrayList<Elemento> lista_totale_elementi;
	
	public static void generateElementsList(String[] stringheElementi){
		ArrayList<Elemento> array=new ArrayList<Elemento>();
		
		for(int i=0;i<Constants.N_MASSIMO;i++) {
			array.add(new Elemento(stringheElementi[i],i));			
		}

		
		
		lista_totale_elementi=array;
	}
	
	public static ArrayList<Elemento> getListaTotaleElementi() {
		return lista_totale_elementi;
	}
	
	public static void pressEnterToContinue() {
		String temp=InputDati.leggiStringa(Constants.CONTINUE_MESSAGE);
	}
}
