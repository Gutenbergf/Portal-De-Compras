package pkgClasses;

import java.util.ArrayList;
import java.util.Scanner;

public class Pedido implements IdAutoIncremento{
	private int idPedido;
	private Categoria categoria;
	private ArrayList<Item_pedido> itens;		
	private String descricao;
	private double valorPedido;
	private int prazoIdeal;
	private int grauUrgencia;
	private boolean pedidoEncerrado;
	
	public Pedido(){
		gerarIdAutomatico();		
	}
	
	public void escolherCategoria(){
    	System.out.println("=====================");		
		System.out.println("Selecione a categoria");
		
		Teste execucao = new Teste();
		execucao.listarCategorias();
		
		Scanner sc = new Scanner(System.in);
		int opcao = sc.nextInt();
    	boolean estaNaLista = false;
    	Categoria categoriaSelecionada = new Categoria();
    	for(Categoria categoria: Teste.categorias){
    		if(categoria.getIdCategoria() == opcao && categoria.getIdCategoria() == categoria.getIdCategoria()){
    			estaNaLista = true;
    			categoriaSelecionada = categoria;
    			break; 
    		}   		
    	}
    	
    	if(estaNaLista){
    		System.out.println("Categoria selecionada - "+categoriaSelecionada.getNomeCategoria());            		
    		setCategoria(categoriaSelecionada);
    	}else{
    		System.out.println("A categoria selecionada não existe na lista, tente novamente");
    		this.escolherCategoria();
    	}  	
	}
	
	public void addItens(Categoria categoria){
		this.itens = new ArrayList<Item_pedido>();
		Scanner sc = new Scanner(System.in);
        int opcao = 1;
		do{
        	System.out.println("Selecione os produtos para seu pedido (0 p/ parar a seleção)");
			//Listagem de produtos
        	for(Produto produto: Teste.produtos){
        		if(produto.getCategoria().getIdCategoria() == categoria.getIdCategoria()){
        			System.out.println(produto.getIdProduto()+" - "+produto.getNomeProduto());				
        		}
        	}
        	
        	System.out.println("=====================");
        	System.out.println("Selecione o produto");
            opcao = sc.nextInt();
            
            if(opcao != 0 ){
            	boolean estaNaLista = false;
            	Produto produtoSelecionado = new Produto();
            	//verifica se a opcao selecionada existe na lista            	
            	for(Produto produto: Teste.produtos){
            		if(produto.getIdProduto() == opcao && produto.getCategoria().getIdCategoria() == categoria.getIdCategoria()){
            			estaNaLista = true;
            			produtoSelecionado = produto;
            			break; 
            		}
            	}
            	
            	if(estaNaLista == true){
            		System.out.println("Digite a QUANTIDADE desejada");
            		this.itens.add(new Item_pedido(produtoSelecionado, sc.nextInt()));
            		System.out.println("Produto adicionado com sucesso.");
            		visualizarItens();
            	}else if(estaNaLista == false){
            		System.out.println("O produto selecionado não existe na lista");            		           		
            	}
            }else{
                System.out.println("Listagem de produtos encerrada");            	
            }
        	
        }while(opcao != 0);		
	}
	
	/*
	 * Informações sobre um pedido
	 * */
	public void visualizarPedido(){
		System.out.println("=====================");
		System.out.println("INFORMAÇÕES DO PEDIDO");
		System.out.println("=====================");
		System.out.println("ID: "+getIdPedido());
		System.out.println("CATEGORIA: "+getCategoria().getIdCategoria());	
		System.out.println("DESCRIÇÃO: "+getDescricao());
		System.out.println("VALOR DESEJADO: "+getValorPedido());
		System.out.println("PRAZO IDEAL: "+getPrazoIdeal());
		System.out.print("GRAU DE URGÊNCIA: ");
		switch(getGrauUrgencia()){
        	case 1:
        		System.out.println("POUCO URGENTE");
        		break;
        	case 2:
        		System.out.println("URGENTE");
        		break;
        	case 3:
                System.out.println("CASO DE VIDA OU MORTE");		
        		break;        		
        }
		visualizarItens();
		if(isPedidoEncerrado()){
			System.out.println("PEDIDO ENCERRADO");			
		}else{
			System.out.println("PEDIDO EM ABERTO");						
		}
	}
	
	/*
	 * Mostra o resumo do pedido (PRODUTOS E QUANTIDADES)
	 * */
	public void visualizarItens(){
		System.out.println("================");
		System.out.println("Resumo do pedido");
		System.out.println("================");
		for (Item_pedido item : this.itens) {
			System.out.println("QTD: "+item.getQuantidade()+" | "+item.getProduto().getNomeProduto());
		}
	}
	
	
	/*
	 * Implementa a interface->IdAutoIncremento
	 * */
	public void gerarIdAutomatico(){
		if(Teste.pedidos.isEmpty()){
			setIdPedido(1);
		}else{
			int lastId = Teste.pedidos.get(Teste.pedidos.size() - 1).idPedido + 1;			
			setIdPedido(lastId);			
		}
	} 	
	
	/*
	 * GET's e SET's
	 * */
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public int getPrazoIdeal() {
		return prazoIdeal;
	}

	public void setPrazoIdeal(int prazoIdeal) {
		this.prazoIdeal = prazoIdeal;
	}

	public int getGrauUrgencia() {
		return grauUrgencia;
	}

	public void setGrauUrgencia(int grauUrgencia) {
		this.grauUrgencia = grauUrgencia;
	}

	public boolean isPedidoEncerrado() {
		return pedidoEncerrado;
	}

	public void setPedidoEncerrado(boolean pedidoEncerrado) {
		this.pedidoEncerrado = pedidoEncerrado;
	}		
}
