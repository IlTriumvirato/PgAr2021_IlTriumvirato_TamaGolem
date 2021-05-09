package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;
import java.util.Collections;

import it.unibs.fp.mylib.InputDati;

public class BattleHandler {
	//battle variables
	static Equilibrio balance;
	static int N; //numero di elementi
	static int P; //numero di pietre elementali in possesso di ogni golem
	static int V; //valore di hp(vita) per ogni golem)
	static int G; //numero di golem per ogni giocatore
	static int S; //numero di pietre nella scorta comune
	static Giocatore giocatore1,giocatore2;
	static ArrayList<Pietra> scorta_comune;
	
	public static void doBattle() {
		battleStartPhase();
		
		initialSummoning();
		do {
			scontroGolem(giocatore1,giocatore2);
			
		}while(canKeepFighting());
		
		announceVictory();
	}
	
		
	
	private static void initialSummoning() {
		giocatore1.summonGolem();
		giocatore2.summonGolem();
	}


	private static void scontroGolem(Giocatore giocatore1, Giocatore giocatore2) {
		Golem golem1=giocatore1.getGolemInPlay();
		Golem golem2=giocatore2.getGolemInPlay();
		
		//check if the golems have complete null interaction, we have to figure out what happens then, probably both golems will die "due to power overload"
		do {
			
			turnoGolem(golem1,golem2);
			
		}while(golem1.isAlive()&&golem1.isAlive());
		
		
		
		if(golem1.isAlive()) {
			System.out.println(Constants.GOLEM_DEAD_MESSAGE);
			giocatore2.golemDeath();
		}else {
			System.out.println(String.format(Constants.GOLEM_DEAD_MESSAGE, giocatore1.getNome()));
			giocatore1.golemDeath();
		}
		
		
	}

	private static void turnoGolem(Golem golem1, Golem golem2) {
		//calcola elemento dominante
		//il golem in svantaggio subisce il danno
	}



	private static void loadGolems(Golem golem1, Golem golem2) {
		
		
	}

	public static void battleStartPhase(){
		
		N=InputDati.leggiIntero(Constants.INPUT_MESSAGE_ELEMENTS_NUMBER_CHOICE,3,10);
		P=generateP(N);
		G=generateG(N,P);
		S=generateS(N,P,G);
		balance=new Equilibrio(N);
		scorta_comune=new ArrayList<Pietra>();
		for(int i=0;i<N;i++) {
			//S/N is always an integer value as S is always a perfect multiple of N
			for(int j=0;j<S/N;j++) {
				scorta_comune.add(new Pietra(balance.getListaElementi().get(i)));
			}
		}
		
		String nome1=InputDati.leggiStringa(String.format(Constants.INPUT_MESSAGE_NOME_GIOCATORE, 1));
		String nome2=InputDati.leggiStringa(String.format(Constants.INPUT_MESSAGE_NOME_GIOCATORE, 2));
		//Collections.shuffle(scorta_comune); //might need
		giocatore1=new Giocatore(nome1,G,V);
		giocatore1=new Giocatore(nome2,G,V);
	}
	
	
	
	private static int generateP(int N) {
		double frazione=((double)(N+1))/3.0;
		return (int)(Math.ceil(frazione)+1);
	}
	
	/**
	 * fornisce il numero di Golem
	 * @param N è il numero di elementi
	 * @param P è il numero di pietre che ogni golem può possedere
	 * @return
	 */
	private static int generateG(int N,int P) {
		double numeratore=(double)((N-1)*(N-2));
		double denominatore=(double)(2*P);
		return (int)Math.ceil(numeratore/denominatore);
	}
	
	/**
	 * fornisce il numero di pietre nella scorta comune
	 * @param N è il numero di elementi
	 * @param P è il numero di pietre che possiede ogni golem
	 * @param G è il numero di golem che ha ogni giocatore
	 * @return
	 */
	private static int generateS(int N, int P, int G) {
		return N*getNPietrePerElemento(N, P, G);
	}
	
	/**
	 * fornisce il numero di pietre per ogni elemento contenute nella scorta comune
	 * @param N è il numero di elementi
	 * @param P è il numero di pietre che possiede ogni golem
	 * @param G è il numero di golem che ha ogni giocatore
	 */
	public static int getNPietrePerElemento(int N, int P, int G) {
		double frazione=((double)(2*P*G))/2.0;
		return (int)Math.ceil(frazione);
	}
	
	public static ArrayList<Elemento> getListaElementi(){
		ArrayList<Elemento> array=new ArrayList<Elemento>();
		String[] stringheElementi= {"Fuoco","Acqua","Terra","Aria","Fulmine","Ghiaccio","Ombra","Luce","Natura","Metallo"};
		
		for(int i=0;i<Constants.N_MASSIMO;i++) {
			array.add(new Elemento(stringheElementi[i],i));			
		}

		
		
		return array;
	}

	public static boolean canKeepFighting() {
		
		return (giocatore1.golemRimasti()>0||giocatore2.golemRimasti()>0)&&scorta_comune.size()>0;
	}
	
	public static void announceVictory() {
		
		if(giocatore1.golemRimasti()>0&&giocatore1.golemRimasti()>0) {
			System.out.println(Constants.TIE_MESSAGE);
		}else {
			if(giocatore1.golemRimasti()>0) {
				System.out.println(String.format(Constants.VICTORY_MESSAGE_COLTA_CIT, giocatore1.getNome(),giocatore2.getNome()));
			}else {
				System.out.println(String.format(Constants.VICTORY_MESSAGE_COLTA_CIT, giocatore2.getNome(),giocatore1.getNome()));
			}
		}
	}
}
