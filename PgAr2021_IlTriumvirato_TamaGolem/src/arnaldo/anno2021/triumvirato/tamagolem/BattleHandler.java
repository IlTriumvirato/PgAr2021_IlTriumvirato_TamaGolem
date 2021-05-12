package arnaldo.anno2021.triumvirato.tamagolem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.stream.XMLStreamException;

import it.unibs.fp.mylib.InputDati;

public class BattleHandler {
	//battle variables
	private static Equilibrio balance;
	private static int N; //numero di elementi
	private static int P; //numero di pietre elementali in possesso di ogni golem
	private static int V; //valore di hp(vita) per ogni golem)
	private static int G; //numero di golem per ogni giocatore
	private static int S; //numero di pietre nella scorta comune
	private static Giocatore giocatore1,giocatore2;
	private static ArrayList<Pietra> scorta_comune;
	
	public static ArrayList<Pietra> getScorta_comune() {
		return scorta_comune;
	}


	public static boolean removeGem(Pietra pietraRimossa) {
		//il controllo che la gemma ci sia � solo un extra di sicurezza, il metodo non verr� mai chiamato su gemme non presenti
		if(scorta_comune.contains(pietraRimossa)) {
			scorta_comune.remove(pietraRimossa);
			return true;
		}else {
			System.out.println(Constants.ERROR_MESSAGE_UNEXISTING_GEM); //l'eventuale re-iter avviene nel metodo chiamante, questo ritorna true se � andato bene e false in caso contrario 
			return false;
		}

	}



	public static void doBattle() {
		battleStartPhase();
		
		initialSummoning();
		
		do {
			scontroGolem(giocatore1,giocatore2);
			
		}while(canKeepFighting());
		
		announceVictory();
	}
	
		
	
	private static void initialSummoning() {
		//clearScreen();
		System.out.println(Constants.GOLEM_INITIAL_SUMMONING_MESSAGE);
		
		giocatore1.summonGolem();
		giocatore2.summonGolem();
	}


	private static void scontroGolem(Giocatore giocatore1, Giocatore giocatore2) {
		Golem golem1=giocatore1.getGolemInPlay();
		Golem golem2=giocatore2.getGolemInPlay();
		
		//check if the golems have complete null interaction, we have to figure out what happens then, probably both golems will die "due to power overload"
		
		if(golem1.hasSameElementsOrder(golem2)) {
			System.out.println(Constants.GOLEMS_SAME_ELEMENTS_MESSAGE);
			
			giocatore1.golemDeath();
			giocatore2.golemDeath();
		}else {
			
			do{
				turnoGolem(golem1,golem2);
				
			}while(golem1.isAlive()&&golem2.isAlive());
			
			
			
			if(golem1.isAlive()) {
				giocatore2.golemDeath();
			}else {
				giocatore1.golemDeath();
			}
		}
		
		
		
		
	}

	private static void outputAttack(Elemento element1, Elemento element2) {
		String output=String.format(Constants.GOLEM_GEM_CLASH_MESSAGE, giocatore1.getNome(),element1.getNome(),giocatore2.getNome(),element2.getNome());
		System.out.println(output);
	}
	
	private static void outputAttackEffect(Elemento element, int damage, Giocatore losingPlayer) {
		System.out.println(String.format(Constants.GOLEM_ELEMENTAL_INTERACTION_MESSAGE, element.getNome(),losingPlayer.getNome(),damage));
	}
	
	private static void turnoGolem(Golem golem1, Golem golem2) {

		
		Pietra golem1Attack=golem1.hurlGem();
		Pietra golem2Attack=golem2.hurlGem();
		Elemento golem1Element=golem1Attack.getTipo();
		Elemento golem2Element=golem2Attack.getTipo();
		
		
		
		int interaction=golem1Element.getInterazione(golem2Element);
		int damage=(int)Math.abs(interaction);
		if(interaction==0) {
			outputAttack(golem1Element,golem2Element);
			System.out.println(Constants.GOLEM_SAME_ENERGY_MESSAGE);
		}else {
			
			if(interaction>0) { //l'elemento 1 ha vinto
				
				golem2.takeDamage(damage);
				if(golem2.isAlive()) {
					outputAttack(golem1Element,golem2Element);
					outputAttackEffect(golem1Element,damage,giocatore2);					
				}else{
					System.out.println(Constants.LAST_HIT_MESSAGE);
				}
				//System.out.println(golem2.getCurrentHealth());
			
			}else { //l'elemento 2 ha vinto
				
				golem1.takeDamage(damage);
				if(golem1.isAlive()) {
					outputAttack(golem1Element,golem2Element);
					outputAttackEffect(golem2Element,damage,giocatore1);
				}else{
					System.out.print(Constants.LAST_HIT_MESSAGE);
				}

				
				//System.out.println(golem2.getCurrentHealth());
			}
		}
		
		System.out.println("\n");
	}


	public static void battleStartPhase(){
		
		N=InputDati.leggiIntero(Constants.INPUT_MESSAGE_ELEMENTS_NUMBER_CHOICE,3,10);
		P=generateP(N);
		G=generateG(N,P);
		S=generateS(N,P,G);
		V=Constants.V;
		balance=new Equilibrio(N);
		//balance.printBalance();
		scorta_comune=new ArrayList<Pietra>();
		for(int i=0;i<N;i++) {
			//S/N is always an integer value as S is always multiple of N
			for(int j=0;j<S/N;j++) {
				scorta_comune.add(new Pietra(balance.getListaElementi().get(i)));
			}
		}
		
		String nome1=InputDati.leggiStringa(String.format(Constants.INPUT_MESSAGE_NOME_GIOCATORE, 1));
		String nome2=InputDati.leggiStringa(String.format(Constants.INPUT_MESSAGE_NOME_GIOCATORE, 2));
		//Collections.shuffle(scorta_comune); //might need
		giocatore1=new Giocatore(nome1);
		giocatore2=new Giocatore(nome2);
		
		giocatore1.createTeam(G,V);
		giocatore2.createTeam(G,V);
	}
	
	
	/**
	 * fornisce il numero di pietre che ogni golem equipaggia
	 * @param N � il numero di elementi
	 * @return P � il numero di pietre che ogni golem pu� possedere
	 */
	private static int generateP(int N) {
		double frazione=((double)(N+1))/3.0;
		return (int)(Math.ceil(frazione)+1);
	}
	
	/**
	 * fornisce il numero di Golem di ogni giocatore
	 * @param N � il numero di elementi
	 * @param P � il numero di pietre che ogni golem pu� possedere
	 * @return numero di Golem
	 */
	private static int generateG(int N,int P) {
		double numeratore=(double)((N-1)*(N-2));
		double denominatore=(double)(2*P);
		return (int)Math.ceil(numeratore/denominatore);
	}
	
	/**
	 * fornisce il numero di pietre nella scorta comune
	 * @param N � il numero di elementi
	 * @param P � il numero di pietre che possiede ogni golem
	 * @param G � il numero di golem che ha ogni giocatore
	 * @return
	 */
	private static int generateS(int N, int P, int G) {
		return N*getNPietrePerElemento(N, P, G);
	}
	
	/**
	 * fornisce il numero di pietre per ogni elemento contenute nella scorta comune
	 * @param N � il numero di elementi
	 * @param P � il numero di pietre che possiede ogni golem
	 * @param G � il numero di golem che ha ogni giocatore
	 */
	public static int getNPietrePerElemento(int N, int P, int G) {
		double frazione=((double)(2*P*G))/2.0;
		return (int)Math.ceil(frazione);
	}
	


	public static boolean canKeepFighting() {
		
		return (giocatore1.golemRimasti()>0&&giocatore2.golemRimasti()>0)&&scorta_comune.size()>0;
	}
	
	
	private static int getVictoryResult() {
		boolean bothPlayersHaveGolems=giocatore1.golemRimasti()>0&&giocatore2.golemRimasti()>0;
		boolean allGolemsAreDead=giocatore1.golemRimasti()==0&&giocatore2.golemRimasti()==0;
		if(bothPlayersHaveGolems||allGolemsAreDead) {
			return Constants.TIE_RESULT_CODE;
		}else {
			if(giocatore1.golemRimasti()>0) {
				return Constants.WIN_PLAYER1_CODE;
			}else if(giocatore2.golemRimasti()>0){
				return Constants.WIN_PLAYER2_CODE;
			}else {
				return Constants.WIN_NOT_COMPUTABLE_CODE;
			}
		}
	}
	
	public static void announceVictory() {
		int victoryCode=getVictoryResult();
		System.out.println();
		if(victoryCode==Constants.TIE_RESULT_CODE) {
			System.out.println(Constants.TIE_MESSAGE);
		}else {
			if(victoryCode==Constants.WIN_PLAYER1_CODE) {
				System.out.println(String.format(Constants.VICTORY_MESSAGE_COLTA_CIT, giocatore1.getNome(),giocatore2.getNome()));
			}else if(victoryCode==Constants.WIN_PLAYER2_CODE){
				System.out.println(String.format(Constants.VICTORY_MESSAGE_COLTA_CIT, giocatore2.getNome(),giocatore1.getNome()));
			}else {
				System.out.println(Constants.UNCOMPUTABLE_WIN_MESSAGE);
			}
		}
		
		System.out.println(Constants.INVITE_TO_PLAY_AGAIN);
	}

	public static Giocatore getOtherPlayer(Giocatore currentPlayer) {
		if(giocatore1==currentPlayer) {
			return giocatore2;
		}else if(giocatore2==currentPlayer) {
			return giocatore1;
		}else {
			return null;
		}
	}
	
	public static String getOtherPlayerName(Giocatore currentPlayer) {
		
		Giocatore altroGiocatore=getOtherPlayer(currentPlayer);
		
		if(altroGiocatore==null) {
			return null;
		}else {
			return (String) altroGiocatore.getNome();
		}
	}
	
	
	public static ArrayList<Pietra> getArrayFirstGemsByType() {
		 ArrayList<Pietra> elencoGemme= new ArrayList<Pietra>();
		 int i=0;
		 while(i<scorta_comune.size()) {

			 	elencoGemme.add(scorta_comune.get(i));
	            
			 	Elemento confronto = scorta_comune.get(i).getTipo();
			 	i++;
			 	while(i<scorta_comune.size()&&confronto.equals(scorta_comune.get(i).getTipo())){
	            	Elemento daConfrontare;
	            	i++;
	            }
            
	     }
				
        return elencoGemme;
	}
	
	public static ArrayList<String> getElencoGemme(){
		ArrayList<String> elencoGemme = new ArrayList<String>();
		ArrayList<Pietra> primeGemme = getArrayFirstGemsByType();
		
		for(int i=0;i<primeGemme.size();i++) {
			//String.format(Constants.GEMMA_DI, primeGemme.get(i).getTipo().getNome())
			elencoGemme.add(primeGemme.get(i).toString());
		}
		return elencoGemme;
	}
	
	public static ArrayList<String> getElencoGemmeTesting(){
        ArrayList<String> elencoGemme = new ArrayList<String>();
        for(int i=0; i<scorta_comune.size(); ) {
            int contatore=0;
            Elemento confronto = scorta_comune.get(i).getTipo();
            
            for(int j=i;j<=scorta_comune.size();j++) {

            	if(j>=scorta_comune.size()) {
            		i=j;
            		elencoGemme.add(new String(String.format(Constants.RAGGRUPPAMENTO_GEMME, contatore, confronto.getNome())));
                    break;
            	}else {
            		Elemento daControllare = scorta_comune.get(j).getTipo();
                    if(confronto.equals(daControllare)) {
                        contatore++;
                    }else {
                        i=j;
                        elencoGemme.add(new String(String.format(Constants.RAGGRUPPAMENTO_GEMME, contatore, confronto.getNome())));
                        break;
                    }
            	}
                
            }
        }
        return elencoGemme;
    }
	
	
	public static int getN() {
		return N;
	}
	

	public static int getP() {
		return P;
	}  


	public static void pressEnterToContinue() {
		String temp=InputDati.leggiStringa(Constants.CONTINUE_MESSAGE);
	}
	
}
