package arnaldo.anno2021.triumvirato.tamagolem;

import it.unibs.fp.mylib.MyMenu;

public class MenuScreen extends MyMenu {
	private static final int EXIT_NUMBER=0;
	private static final String DEFAULT_EXIT_MESSAGGE="\nChiusura del programma completata, torna presto a giocare!";


	public MenuScreen(String titolo, String[] voci) {
		super(titolo, voci);
		exitMessage=DEFAULT_EXIT_MESSAGGE;
	}

	public void runMenu() {
		int scelta;
		do {
			scelta=choose();
			runSelection(scelta);
						
			
		}while(scelta!=EXIT_NUMBER);
	}
	

	/**
	 * Esegue l'effettiva serie di istruzioni associata all'inserimento dell'utente
	 * @param selection è la selezione dell'utente
	 * @return ritorna un codice di controllo che indica al programma chiamante se deve prendere opportune scelte, come chiudere il programma in caso di ritorno -1, errore in caso di ritorno 1 e codice lanciato con successo in caso di ritorno 0
	 */
	@Override
	public void runSelection(int selection) {
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
			case EXIT_NUMBER:
				//quit game
				System.out.println(exitMessage);
				break;
			default:
				//undefined
			break;
		
		}
		
	}
	
}
