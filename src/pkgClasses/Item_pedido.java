package pkgClasses;

public class Item_pedido{
	private Produto produto;
	private int quantidade;
	
	public Item_pedido(){
	}
	
	public Item_pedido(Produto produto, int quantidade){
		setProduto(produto);
		setQuantidade(quantidade);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}	
	
}
