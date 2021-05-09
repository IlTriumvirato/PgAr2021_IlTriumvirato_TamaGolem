package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

public class Equilibrio {
	ArrayList<Elemento> listaElementi;

	Equilibrio(int n){

		listaElementi=new ArrayList<Elemento>();
		
		this.generaEquilibrio(n);
	}

	private void generaEquilibrio(int n) {
		for(int i=0;i<n;i++) {
			listaElementi.add(new Elemento(Constants.LISTA_TOTALE_ELEMENTI.get(i)));
			int sommaAttuale=0; //valore attuale della somma dei vantaggi per l'elemento i-esimo
			for(int j=0;j<i;j++) {
				int vantaggioPrecedente=(-1)*listaElementi.get(j).getFromMappa(i); //metto -valore quando so che funziona
				sommaAttuale+=vantaggioPrecedente;
				listaElementi.get(i).addToMappa(vantaggioPrecedente);
			}
			listaElementi.get(i).addToMappa(0);
			
			//genera n-1-i numeri con somma k tra 1 e Constants.DANNO_MAX
			int numeroElementiRimasti=n-1-i;
			int[] valori=new int[numeroElementiRimasti];
			int counter=0;
			valori=generaArrayRandomConSomma(numeroElementiRimasti,sommaAttuale,-Constants.DANNO_MAX,Constants.DANNO_MAX);
			
			for(int j=i+1;j<n;j++) {
				int vantaggio=valori[counter];
				counter++;
				listaElementi.get(i).addToMappa(vantaggio);
			}
			
		}
		
	}
	
	private int[] generaArrayRandomConSomma(int cardinality,int sum, int rangeMin, int rangeMax) {
		int[] toReturn=new int[cardinality];
		
		//for testing
		
		for(int i=0;i<cardinality;i++) {
			toReturn[i]=1;
		}
		
		
		
		
		
		return toReturn;
	}
	
	private int getRandom(int min, int max) {
		if(min<=0&&max>=0) {
			return (2*(int)(Math.random()*(max+1)))+min;
		}else if(min>=0&&max>0){
			return ((int)(Math.random()*max))+min;
		}
		
		return 0;
	}

	public ArrayList<Elemento> getListaElementi() {
		return listaElementi;
	}
	

}
