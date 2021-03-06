package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

import it.unibs.fp.mylib.ManipolaArray;

public class Equilibrio {
	private ArrayList<Elemento> listaElementi;

	Equilibrio(int n){

		listaElementi=new ArrayList<Elemento>();
		
		this.generaEquilibrio(n);
	}
	
	/**
	 * Genera l'equilibrio utilizzando i metodi della classe "ArrayWithFixedSumGenerator"
	 * @param n, numero degli elementi
	 */
	private void generaEquilibrio(int n){

		//le operazioni in questo for vengono eseguite su tutti gli elementi (sull'i-esimo)
		for(int i=0;i<n;i++) {
			
			listaElementi.add(new Elemento(GlobalValues.getListaTotaleElementi().get(i)));
			
			//con questo for aggiunge alla lista vantaggi dell'elemento i-esimo tutti i valori presi dall'interazione tra questo elemento e quelli che sono gi? stati dichiarati(per il primo il for non parte ovviamente)
			
			int sommaAttuale=0; //valore attuale della somma dei vantaggi per l'elemento i-esimo
			for(int j=0;j<i;j++) {
				int vantaggioPrecedente=(-1)*listaElementi.get(j).getFromMappa(i); //metto -valore quando so che funziona
				sommaAttuale+=vantaggioPrecedente;
				listaElementi.get(i).addToMappa(vantaggioPrecedente);
			}
			
			//quando ha finito con gli elementi per cui sono gi? stati scelti i vantaggi, segna l'interazione nulla con s? stesso, nella posizione uguale al suo id infatti
			listaElementi.get(i).addToMappa(0);
			
			//genera i vantaggi contro gli elementi che non sono ancora stati dichiarati
			//ma per rispettare la condizione che la somma di tutti i vantaggi dovr? essere 0, questi n-1-i elementi rimasti devono avere somma pari a -sommaAttuale, chiama quindi ArrayWithFixedSumGenerator
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

	

	/**
	 * ritorna la lista degli elementi attualmente utilizzati, si noti che non ? la lista totale, ma quella dell'equilibrio attualmente generato
	 * @return
	 */
	public ArrayList<Elemento> getListaElementi() {
		return listaElementi;
	}



	
	private void stampaSpazi(int n) {
		for(int i=0;i<n;i++) {
			System.out.print(" ");
		}
	}
	
	private int getMaxElementLength() {
		int maxElementLength=listaElementi.get(0).getNome().length();
		for(int i=0;i<listaElementi.size();i++) {
			if(listaElementi.get(i).getNome().length()>maxElementLength) {
				maxElementLength=listaElementi.get(i).getNome().length();
			}
		}
		
		return maxElementLength;
	}
	
	
	public void printBalance() {
		if(listaElementi!=null) {
			
			int maxLength=getMaxElementLength();
			/*if(maxLength%2==1) {
				maxLength++;
			}*/
			
			stampaSpazi(maxLength+3);
			
			for(int i=0;i<listaElementi.size();i++) {
				System.out.print("|");
				int lengthRimasta=maxLength-listaElementi.get(i).getNome().length();
				
				
				stampaSpazi(lengthRimasta/2);
				System.out.print(listaElementi.get(i).getNome());
				
				if(lengthRimasta%2==1) {
					lengthRimasta++;
				}
				stampaSpazi(lengthRimasta/2);
				
			}
			System.out.println("|");
			
			
			for(int i=0;i<listaElementi.size();i++) {
				int lengthRimasta=maxLength-listaElementi.get(i).getNome().length();
				
				
				stampaSpazi(lengthRimasta/2);
				System.out.print(listaElementi.get(i).getNome()+"-->");
				
				if(lengthRimasta%2==1) {
					lengthRimasta++;
				}
				stampaSpazi(lengthRimasta/2);
			
				
				for(int j=0;j<listaElementi.size();j++) {
					int vantaggio=listaElementi.get(i).getFromMappa(j);
					int lengthRimastaNumeri=maxLength-2;
					
					
					System.out.print("|");
					stampaSpazi(lengthRimastaNumeri/2);
					System.out.print(String.format("%+d",vantaggio));
					
					if(lengthRimastaNumeri%2==1) {
						lengthRimastaNumeri++;
					}
					stampaSpazi(lengthRimastaNumeri/2);
				}
				
				System.out.println("|");
				
			}
			
			
			
		}
		
	}
	

}
