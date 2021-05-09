package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

public class Golem {
	ArrayList<Pietra> pietre;
	int hp_max;
	int hp_attuali;
	int currentGemSlot;
	
	public Golem(int hp_max) {
		this.hp_max = hp_max;
		hp_attuali = hp_max;
	}
	
	
	public int getCurrentHealth() {
		return hp_attuali;
	}


	public boolean isAlive() {
		if(this.hp_attuali>0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void takeDamage(int damageAmount) {
		hp_attuali-=damageAmount;
	}
}
