package arnaldo.anno2021.triumvirato.tamagolem;

public class BattleHandler {
	
	
	public static int getP(int N) {
		
		double frazione=((double)(N+1))/3.0;
		return (int)(Math.ceil(frazione)+1);
	}
	
	/**
	 * fornisce il numero di Golem
	 * @param N è il numero di elementi
	 * @param P è il numero di pietre che ogni golem può possedere
	 * @return
	 */
	public static int getG(int N,int P) {
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
	int getS(int N, int P, int G) {
		return N*getNPietrePerElemento(N, P, G);
	}
	
	/**
	 * fornisce il numero di pietre per ogni elemento contenute nella scorta comune
	 * @param N è il numero di elementi
	 * @param P è il numero di pietre che possiede ogni golem
	 * @param G è il numero di golem che ha ogni giocatore
	 */
	int getNPietrePerElemento(int N, int P, int G) {
		double frazione=((double)(2*P*G))/2.0;
		return (int)Math.ceil(frazione);
	}
}
