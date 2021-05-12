package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

import it.unibs.fp.mylib.ManipolaArray;

public class Equilibrio {
	private ArrayList<Elemento> listaElementi;

	Equilibrio(int n){

		listaElementi=new ArrayList<Elemento>();
		
		this.generaEquilibrio(n);
	}
	

	private void generaEquilibrio(int n){

		for(int i=0;i<n;i++) {
			listaElementi.add(new Elemento(GlobalValues.getListaTotaleElementi().get(i)));
			
			int sommaAttuale=0; //valore attuale della somma dei vantaggi per l'elemento i-esimo
			for(int j=0;j<i;j++) {
				int vantaggioPrecedente=(-1)*listaElementi.get(j).getFromMappa(i); //metto -valore quando so che funziona
				sommaAttuale+=vantaggioPrecedente;
				listaElementi.get(i).addToMappa(vantaggioPrecedente);
			}
			listaElementi.get(i).addToMappa(0);
			
			//genera n-1-i numeri con somma k tra 1 e Constants.DANNO_MAX
			int numeroElementiRimasti=n-1-i;
			int[] valori=ArrayWithFixedSumGenerator.generateCommonArrayWithFixedSum(numeroElementiRimasti, Constants.DANNO_MAX, -sommaAttuale);
			int counter=0;
						
			for(int j=i+1;j<n;j++) {
				int vantaggio=valori[counter];
				counter++;
				listaElementi.get(i).addToMappa(vantaggio);
			}
			
		}
		
	}

	

	
	public ArrayList<Elemento> getListaElementi() {
		return listaElementi;
	}



	public void printBalance() {
		if(listaElementi!=null) {
			for(int i=0;i<listaElementi.size();i++) {
				System.out.print("Elemento: "+listaElementi.get(i).getNome()+" lista: ");
				for(int j=0;j<listaElementi.size();j++) {
					System.out.print(listaElementi.get(i).getListaVantaggi()[j]+" ");
				}
				System.out.println();
			}
		}
		
	}
	

}