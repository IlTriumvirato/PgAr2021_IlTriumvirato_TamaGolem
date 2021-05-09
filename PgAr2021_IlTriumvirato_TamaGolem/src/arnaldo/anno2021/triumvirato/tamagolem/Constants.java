package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

public class Constants {
	public static final String GAME_NAME="Tamagolem";
	public static final String[] MENU_SELECTION_OPTIONS= {"Gioca","Opzioni","Regole"};
	public static final int N_MASSIMO=10;
	public static final int N_MINIMO=3;
	public static final int V=10;
	public static final int DANNO_MAX=V-1;
	public static final ArrayList<Elemento> LISTA_TOTALE_ELEMENTI=BattleHandler.getListaElementi();
	public static final String N_CHART="\nDefinendo N il numero di elementi scelti, il livello di difficolta' e' il seguente: \n3<=N<=5 livello facile, ma N=3 potrebbe rendere la partita meno interessante;\n6<=N<=8 livello intermedio;\n9<=N<=10 livello difficile;\nI comuni mortali non possono concepire un universo con piu' di 10 elementi, il caos sarebbe eccessivo.";
	public static final String INPUT_MESSAGE_ELEMENTS_NUMBER_CHOICE = "Inserisci il numero di elementi con cui desideri giocare, tieni a mente però che a seconda di questi valori la partita potrebbe cambiare considerevolmente.\n"+N_CHART;
	public static final String INPUT_MESSAGE_NOME_GIOCATORE = "Giocatore %d, inserisci il tuo nome";
	public static final String VICTORY_MESSAGE_COLTA_CIT="%s vince! Tuuu(%d) hai perso!";
	public static final String TIE_MESSAGE = "Pareggio... Perfettamente bilanciato, come ogni cosa dovrebbe essere...";
	public static final String GOLEM_DEAD_MESSAGE="Il Golem di %s e' stato sconfitto!";
	public static final String GOLEM_SUMMON_MESSAGE="%s ha evocato un golem!";
	public static final String GOLEM_ELEMENTAL_INTERACTION_MESSAGE="Il golem di %s scaglia una pietra di %s - Il golem di %s scaglia una pietra di %s ... Prevale %s e il golem di %s subisce %d danni";
	
	
}
