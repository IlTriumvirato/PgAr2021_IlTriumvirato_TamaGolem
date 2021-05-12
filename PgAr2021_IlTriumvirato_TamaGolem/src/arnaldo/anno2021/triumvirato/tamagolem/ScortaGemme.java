package arnaldo.anno2021.triumvirato.tamagolem;

import java.util.ArrayList;

public class ScortaGemme extends ArrayList<Pietra>{

	ScortaGemme(){
		super();
	}
	
	public boolean removeGem(Pietra pietraRimossa) {
		//il controllo che la gemma ci sia è solo un extra di sicurezza, il metodo non verrà mai chiamato su gemme non presenti
		if(this.contains(pietraRimossa)) {
			this.remove(pietraRimossa);
			return true;
		}else {
			System.out.println(Constants.ERROR_MESSAGE_UNEXISTING_GEM); //l'eventuale re-iter avviene nel metodo chiamante, questo ritorna true se è andato bene e false in caso contrario 
			return false;
		}

	}
	
	public ArrayList<Pietra> getArrayFirstGemsByType() {
		 ArrayList<Pietra> elencoGemme= new ArrayList<Pietra>();
		 int i=0;
		 while(i<this.size()) {

			 	elencoGemme.add(this.get(i));
	            
			 	Elemento confronto = this.get(i).getTipo();
			 	i++;
			 	while(i<this.size()&&confronto.equals(this.get(i).getTipo())){
	            	Elemento daConfrontare;
	            	i++;
	            }
           
	     }
				
       return elencoGemme;
	}
	
	public ArrayList<String> getElencoGemme(){
		ArrayList<String> elencoGemme = new ArrayList<String>();
		ArrayList<Pietra> primeGemme = this.getArrayFirstGemsByType();
		
		for(int i=0;i<primeGemme.size();i++) {
			elencoGemme.add(primeGemme.get(i).toString());
		}
		return elencoGemme;
	}
}
