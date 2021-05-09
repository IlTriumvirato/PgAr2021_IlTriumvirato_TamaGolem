package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

public class Giocatore {
	private String nome;
	private ArrayList<Golem> squadra;

	public Giocatore(String nome, int Golem_amount, int Golem_health) {
		this.nome = nome;
		this.squadra = new ArrayList<Golem>();
		
		for(int i=0;i<Golem_amount;i++) {
			squadra.add(new Golem(Golem_health));
		}
	}

	public Object getNome() {
		return nome;
	}
	
	public int golemRimasti() {
		return squadra.size();
	}
	
	public Golem getGolemInPlay() {
		return squadra.get(squadra.size()-1);
	}
	
	public void summonGolem() {
		loadCurrentGolem();
		System.out.println(Constants.GOLEM_SUMMON_MESSAGE);
	}
	
	public void golemDeath() {
		this.squadra.remove(squadra.size()-1);
		if(squadra.size()>0) {
			summonGolem();
		}
	}

	public void loadCurrentGolem() {
		
		
	}
	
	
}
