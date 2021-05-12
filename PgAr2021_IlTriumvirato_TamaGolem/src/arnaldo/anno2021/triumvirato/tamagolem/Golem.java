package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

public class Golem {
	private ArrayList<Pietra> pietre;
	private int hp_max;
	private int hp_attuali;
	private int currentGemSlot;
	
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
	
	public void kill() {
		this.hp_attuali=0;
	}
	
	public void resetGems() {
		pietre=new ArrayList<Pietra>();
		currentGemSlot=0;
	}
	
	public void addGem(Pietra gem) {
		pietre.add(new Pietra(gem));
	}
	
	public Pietra hurlGem() {
		Pietra toHurl=pietre.get(currentGemSlot);
		cycleGemSlot();
		return toHurl;
	}
	
	private void cycleGemSlot() {
		currentGemSlot++;
		if(currentGemSlot==pietre.size()) {
			currentGemSlot=0;
		}
	}
	
	private ArrayList<Pietra> getListaGemme(){
		return pietre;
	}
	
	public boolean hasSameElementsOrder(Golem confronto) {
		ArrayList<Pietra> pietreConfronto=confronto.getListaGemme();
		
		if(pietreConfronto.size()==this.pietre.size()) {
			int gemsAmount=this.pietre.size();
			boolean ritorno=true;
			
			int currentPositionThis=this.currentGemSlot;
			int currentPositionConfronto=confronto.getCurrentGemSlot();
			
			
			for(int i=0;i<gemsAmount;i++) {
				
				if(!pietre.get(currentPositionThis).hasSameElement(pietreConfronto.get(currentPositionConfronto))) {
					ritorno=false;
					break;
				}
				
				currentPositionThis++;
				currentPositionConfronto++;
				if(currentPositionThis>=gemsAmount) {
					currentPositionThis=0;
				}
				
				if(currentPositionConfronto>=gemsAmount) {
					currentPositionConfronto=0;
				}
					
			}
			
			return ritorno;
		}else {
			return false;
		}
		

	}
	
	private int getCurrentGemSlot() {
		return this.currentGemSlot;
	}
	
}
