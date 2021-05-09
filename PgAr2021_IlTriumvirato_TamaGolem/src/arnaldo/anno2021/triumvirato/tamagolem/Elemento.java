package arnaldo.anno2021.triumvirato.tamagolem;

public class Elemento {
	String nome;
	int id;
	MappaElementale mappa;
	
	Elemento(String nome, int id){
		this.nome=nome;
		this.id=id;
		this.mappa=new MappaElementale();
	}
	
	public Elemento(Elemento elemento) {
		this.nome=elemento.nome;
		this.id=elemento.id;
		this.mappa=new MappaElementale();
	}

	/**
	 * Controlla l'interazione di questo elemento con un altro dato come parametro
	 * @param nemico è l'elemento contro il quale questo viene confrontato
	 * @return ritorna il numero di vantaggio che questo elemento ha rispetto all'altro(ritorna un valore negativo se è invece l'elemento nemico ad essere in vantaggio)
	 */
	public int getInterazione(Elemento nemico){
		int indice=nemico.getId();
		if(mappa.getSize()>=indice+1){
			return mappa.getValore(indice);
		}else {
			return 0;			
		}

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addToMappa(int valore) {
		mappa.aggiungiValore(valore);
	}
	
	public int getFromMappa(int index) {
		return mappa.getValore(index);
	}
}
