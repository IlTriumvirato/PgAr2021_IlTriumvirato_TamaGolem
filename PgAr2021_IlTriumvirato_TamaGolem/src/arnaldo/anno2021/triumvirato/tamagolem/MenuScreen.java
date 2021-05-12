package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;
import it.unibs.fp.mylib.ServizioFile;

public class MenuScreen extends MyMenu {
	private static final int EXIT_NUMBER=0;
	private static final String DEFAULT_EXIT_MESSAGGE_MENU_SCREEN="\nChiusura del programma completata, torna presto a giocare!";


	public MenuScreen(String titolo, String[] voci) {
		super(titolo, voci);
		exitMessage=DEFAULT_EXIT_MESSAGGE_MENU_SCREEN;
	}

	/**
	 * Esegue l'effettiva serie di istruzioni associata all'inserimento dell'utente
	 * @param selection � la selezione dell'utente
	 * @return ritorna un codice di controllo che indica al programma chiamante se deve prendere opportune scelte, come chiudere il programma in caso di ritorno -1, errore in caso di ritorno 1 e codice lanciato con successo in caso di ritorno 0
	 */
	@Override
	public void runSelection(int selection) {
		switch(selection) {
			case 1:
				//play
				BattleHandler.doBattle();
			break;
			case 2:
				//rules
				printRules();
			break;
			case 3:
				//change elements
				changeElements();
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

	private void changeElements() {
		final String elementInsertion="Elemento %d:";
		System.out.println(Constants.CHANGE_WORLD_ELEMENTS_MESSAGE);
		String[] array=new String[Constants.N_MASSIMO];
		for(int i=0;i<Constants.N_MASSIMO;i++) {
			array[i]=InputDati.leggiStringa(String.format(elementInsertion, i));
		}
		
		GlobalValues.generateElementsList(array);
		
	}

	private void printRules() {
		for(int i=0;i<Constants.LORE.length;i++) {
			System.out.println(Constants.LORE[i]);
		}
		System.out.println();
		for(int i=0;i<Constants.RULES.length;i++) {
			System.out.println(Constants.RULES[i]);
		}
		System.out.println();
	}
	
}
