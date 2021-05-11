package arnaldo.anno2021.triumvirato.tamagolem;

public class Pietra {
	Elemento tipo;

	public Pietra(Pietra copia) {
		this.tipo=new Elemento(copia.getTipo());
	}
	
	public Pietra(Elemento tipo) {
		super();
		this.tipo = tipo;
	}

	public Elemento getTipo() {
		return tipo;
	}
	
	public String toString() {
		return String.format(Constants.GEMMA_DI, tipo.getNome());
	}
	
	public boolean hasSameElement(Pietra confronto) {
		return this.getTipo().equals(confronto.getTipo());
	}
}
