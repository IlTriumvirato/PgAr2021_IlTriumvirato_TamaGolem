package it.unibs.fp.mylib;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;

public class AlgebraicFunctions {

	
	
public static final int ELEMENT_NUM=10, MAX_DAMAGE=ELEMENT_NUM-1, MIN_DAMAGE=-MAX_DAMAGE;
	
	//genera un numero casuale che non sia 0, tanto è molto difficile che faccia più di due iterazioni, sicuramente non si blocca all'infinito
	static public int getRandomNumber(int min, int max) {
		if(max==min) {
			return max;
		}else {
			int number=0;
			min-=1;
			max+=1;
			while(number==0) {
				number=(int) ((Math.random() * (max - min)) + min);
			}
		    return number;
		}
		
	}
	/**
	 * returns array of integers that sum is =0
	 * @param size numbers of the random element
	 * @return
	 */
	static ArrayList<Integer> getArrayRandomSumZero(int size,int equal, int max_damage){
		final int MAX_DAMAGE=max_damage;
		final int MIN_DAMAGE=-max_damage;
		//System.out.println("********* "+equal+"***");
		ArrayList<Integer> array=new ArrayList<>();
		int temp_max,temp_min,sum, last_element;
		for(int i=0;i<size;i++) {
			sum=0;
			for(int j=0;j<array.size();j++) {
				sum+=array.get(j);
			}
			if(i!=size-1) {
				temp_min=MIN_DAMAGE-sum;
				temp_max=MAX_DAMAGE-sum;
				if(temp_min<=MIN_DAMAGE) {
					temp_min=MIN_DAMAGE;
				}
				if(temp_max>=MAX_DAMAGE) {
					temp_max=MAX_DAMAGE;
				}
				
				array.add(i,getRandomNumber(temp_min,temp_max));
				//System.out.print(array.get(i)+" ");
			}else if(i==size-1) {
				//System.out.println("last elementi"+equal+" -"+sum);
				last_element=equal-sum;
				//System.out.println("last elementi"+last_element);
				int k=size-2;
				if(last_element>MAX_DAMAGE) {
					/*
					 * qui cercavo di risolvere il fatto che l'ultima colonna possa superare il range di danno
						while(Math.abs(last_element)!=6 && k>=0) {
							if(array.get(k)<MAX_DAMAGE) {
								array.set(k,array.get(k)+1);
								last_element--;
								k--;
						}
					}*/
					//array.set(k,last_element-MAX_DAMAGE+array.get(k));
					
				}else if(last_element<MIN_DAMAGE && k>=0) {
					/*while(Math.abs(last_element)!=6) {
						if(array.get(k)>MIN_DAMAGE ) {
							array.set(k,array.get(k)-1);
							last_element++;
							k--;
					}
				}	
					*/
					
					//array.set(k,last_element-MIN_DAMAGE+array.get(k));
				
				
			}
				if(last_element==0) {
					array.add(array.size(),array.get(array.size()-1)+1);
					last_element=-1;
					//possibilità: lo mette a -1 o più 1 a random e poi lo swappa con uno a random dell'array, così non è random perfetto teorico, ma è accettabile
				}
				array.add(i,last_element);
			}
			
		}
		return array;
	}
	
	
	public static int[][] generateSimmetricMatrixOfZeroColumnsRowsSum(int N_elementi, int max_damage) {
		final int ELEMENT_NUM=N_elementi;
		final int MAX_DAMAGE=max_damage;
		
		int counter, equals=0;
		int[][] matrix=new int[ELEMENT_NUM][ELEMENT_NUM];
		ArrayList<Integer> array;
		
		for(int i=0;i<ELEMENT_NUM;i++) {
			for(int j=0;j<ELEMENT_NUM;j++) {
					matrix[i][j]=0;
			}
		}
		
		for(int i=0;i<ELEMENT_NUM;i++) {
			equals=0;
			for(int j=0;j<=i;j++) {
				equals+=matrix[j][i];
			}
			//System.out.println("okkk"+equals);
			array= getArrayRandomSumZero(ELEMENT_NUM-i-1,equals,MAX_DAMAGE);
			counter=0;
			for(int j=0;j<ELEMENT_NUM;j++) {
				if(j>i) {
					matrix[i][j]=array.get(counter);
					matrix[j][i]=-matrix[i][j];
					counter++;
				}
			}
			//System.out.print("\n");
		}
		
		//questo è il controllo delle somme e 
		for(int i=0;i<ELEMENT_NUM;i++) {
			int somma=0;
			for(int j=0;j<ELEMENT_NUM;j++) {
				//???Serve????
				if(i>j) {
					matrix[i][j]=-matrix[j][i];
				}
				somma+=matrix[i][j];
				//System.out.print(matrix[i][j]+" ");
			}
			//System.out.print(" somma: "+somma);
			//System.out.println();
		}
		//System.out.println("terminata");
		
		
		return matrix;
		
	}
	
	/*
	public static void main(String[] args) throws XMLStreamException {
		int[][] matrix=generateSimmetricMatrixOfZeroColumnsRowsSum();
		
		for(int i=0;i<ELEMENT_NUM;i++) {
			int somma=0;
			for(int j=0;j<ELEMENT_NUM;j++) {
				//???Serve????
				System.out.print(matrix[i][j]+" ");
				//System.out.print(String.format("\t%d",matrix[i][j]));
			}
			System.out.print(" somma: "+somma);
			System.out.println();
		}
	}
	*/
	

	/*
	 * 1-l'ultimo valore potrebbe essere 0, quindi viene generato a random e non va bene perché sfasa la somma.   possibile soluzione: prende a caso uno dei valori dell'array e 
	 * 2-l'ultimo valore potrebbe superare il valore massimo(in positivo o negativo)   ()-0-8
	 * 3-il doppio ribaltamento???
	 * 4-il fatto che se esce 0 rirolla forse è un po' una patoccata(chiede a Nikita)
	 *
	 * possibile soluzione a TUTTO: l'algoritmo stronzo che genera due numeri si somma data e poi 
	 * n        -10  10     v  -(n-v)  //re-iter on 0 che non è un problema(ma chiediamo a Nikita lo stesso)
	 * 
	 * 
	 */
}
