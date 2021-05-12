package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

import it.unibs.fp.mylib.ExitLessMenu;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class Giocatore {
	
	private String nome;
	private ArrayList<Golem> squadra;

	public Giocatore(String nome) {
		this.nome = nome;
	}

	public void createTeam(int Golem_amount, int Golem_health) {
		this.squadra = new ArrayList<Golem>();
		
		for(int i=0;i<Golem_amount;i++) {
			squadra.add(new Golem(Golem_health));
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public int golemRimasti() {
		return squadra.size();
	}
	
	public Golem getGolemInPlay() { //il golem che è sul campo a combattere al momento è sempre l'ultimo dell'array, l'alternativa era fare il primo, ma così è leggermente meglio l'esecuzione
		return squadra.get(squadra.size()-1);
	}
	
	public void summonGolem() {
		System.out.println(String.format(Constants.SECRET_INPUT_SUMMONING_MESSAGE, nome,BattleHandler.getOtherPlayerName(this)));
		loadCurrentGolem();
		System.out.println("\n"+String.format(Constants.GOLEM_SUMMON_MESSAGE,this.nome));
		BattleHandler.pressEnterToContinue();
	}
	
	public void golemDeath() {
		System.out.println(String.format(Constants.GOLEM_DEAD_MESSAGE, this.nome));
		BattleHandler.pressEnterToContinue();
		this.squadra.remove(squadra.size()-1);
		if(squadra.size()>0&&BattleHandler.canKeepFighting()) {
			summonGolem();
		}
	}

	public void loadCurrentGolem() {
		
		BattleHandler.pressEnterToContinue();
		this.getGolemInPlay().resetGems();
		
		for(int i=1;i<=BattleHandler.getP();i++) {
			
			
			ArrayList<Pietra> primePerTipo=BattleHandler.getScortaComune().getArrayFirstGemsByType();
			ArrayList<String> elencoGemme=BattleHandler.getScortaComune().getElencoGemme();
			
			ExitLessMenu menuGemme=new ExitLessMenu(String.format(Constants.GOLEM_GEM_CHOICE_MESSAGE, i),elencoGemme);
			int gemChoice=menuGemme.choose();
			
			Pietra riferimentoGemma=primePerTipo.get(gemChoice-1);//despite the user choosing between 1 and the number of gems, indexing starts from 0
			
			this.getGolemInPlay().addGem(riferimentoGemma); //copy happens, this object is thus different and will not be removed with the following deletion
			BattleHandler.getScortaComune().removeGem(riferimentoGemma); //this object is a pointer and thus the gem will be effectively removed from the global list		
			
		
		}
	}
	

}
