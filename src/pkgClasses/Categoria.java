package pkgClasses;

public class Categoria implements IdAutoIncremento{
	private int idCategoria;
	private String nomeCategoria;

	public Categoria(){
		gerarIdAutomatico();
	}
	
	public Categoria(String nomeCategoria){
		gerarIdAutomatico();
		setNomeCategoria(nomeCategoria);
	}
	
	/*
	 * Implementa a interface->IdAutoIncremento
	 * */
	public void gerarIdAutomatico(){
		if(Teste.categorias.isEmpty()){
			setIdCategoria(1);
		}else{
			int lastId = Teste.categorias.get(Teste.categorias.size() - 1).idCategoria + 1;			
			setIdCategoria(lastId);			
		}
	} 
	
	
	/*
	 * GET's e SET's
	 * */
	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
}
