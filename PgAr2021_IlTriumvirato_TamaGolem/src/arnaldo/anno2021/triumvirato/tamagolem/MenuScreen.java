package arnaldo.anno2021.triumvirato.tamagolem;
import it.unibs.fp.mylib.MyMenu;

public class MenuScreen {
	private static final int SUCCESSFUL_EXECUTION_NUMBER=0;
	private static final int EXIT_NUMBER=-1;
	private static final String EXIT_MESSAGE="\nChiusura del programma completata, torna presto a giocare!";
	
	MyMenu menu_principale;
	
	MenuScreen(String titolo, String [] voci){
		this.menu_principale=new MyMenu(titolo, voci);
	}
	
	public void runMenu() {
		int check;
		do {
			check=selectAndRun();
			
		}while(check!=EXIT_NUMBER);
	}
	
	/**
	 * Mostra all'utente le opzioni del menu principale, gliene fa scegliere una e ne avvia l'esecuzione
	 * @return ritorna un codice di controllo che indica al programma chiamante se deve prendere opportune scelte, come chiudere il programma in caso di ritorno -1, errore in caso di ritorno 1 e codice lanciato con successo in caso di ritorno 0
	 */
	public int selectAndRun() {
		int selezione=menu_principale.scegli();
		int check=runSelection(selezione);
		return check;
	}
	
	/**
	 * Esegue l'effettiva serie di istruzioni associata all'inserimento dell'utente
	 * @param selection è la selezione dell'utente
	 * @return ritorna un codice di controllo che indica al programma chiamante se deve prendere opportune scelte, come chiudere il programma in caso di ritorno -1, errore in caso di ritorno 1 e codice lanciato con successo in caso di ritorno 0
	 */
	public int runSelection(int selection) {
		int check=SUCCESSFUL_EXECUTION_NUMBER;
		switch(selection) {
			case 1:
				//play
			break;
			case 2:
				//options 
			break;
			case 3:
				//rules
			break;
			case 0:
				//quit game
				System.out.println(EXIT_MESSAGE);
				check=EXIT_NUMBER;
			break;
			default:
				//undefined
			break;
		
		}
		
		return check;
	}
}
