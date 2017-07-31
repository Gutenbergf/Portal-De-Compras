package pkgClasses;

public class Produto implements IdAutoIncremento{

	private int idProduto;
	private Categoria categoria;
	private String nomeProduto;
	
	public Produto(){
		gerarIdAutomatico();				
	}
	
	public Produto(Categoria categoria, String nomeProduto){
		gerarIdAutomatico();		
		setCategoria(categoria);
		setNomeProduto(nomeProduto);
	}
	
	/*
	 * Implementa a interface->IdAutoIncremento
	 * */
	public void gerarIdAutomatico(){
		if(Teste.produtos.isEmpty()){
			setIdProduto(1);
		}else{
			int lastId = Teste.produtos.get(Teste.produtos.size() - 1).idProduto + 1;			
			setIdProduto(lastId);			
		}
	} 	
	
	/*
	 * GET's e SET's
	 * */
	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
}
