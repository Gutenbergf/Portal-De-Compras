package pkgClasses;

import java.util.ArrayList;
import java.util.Scanner;

public class Fornecedor implements IdAutoIncremento {
	private int idFornecedor;
	private String nome;
	private String cnpj;
	private ArrayList<Categoria> categoria;
	private String login;
	private String senha;
	private boolean ativo;
	
	public Fornecedor (){
		gerarIdAutomatico();		
	}
	
	public Fornecedor(String nome, String login, String senha, boolean status){
		gerarIdAutomatico();
		setNome(nome);
		setLogin(login);
		setSenha(senha);
		setAtivo(status);
	}
	
	public void visualizarFornecedor(){
		System.out.println("=========================");
		System.out.println("INFORMAÇÕES DO FORNECEDOR");
		System.out.println("=========================");
		System.out.println("ID FORNECEDOR: "+getIdFornecedor());
		System.out.println("ID FORNECEDOR: "+getNome());
		System.out.println("ID FORNECEDOR: "+getCnpj());
		
		System.out.println("CATEGORIAS ATENDIDAS");
		for(Categoria categoria : getCategoria()){
			System.out.println(categoria.getNomeCategoria());
		}
		if(isAtivo()){
			System.out.println("FORNECEDOR ATIVO");			
		}else{
			System.out.println("FORNECEDOR INATIVO");			
		}				
	}
	
	public void addCategoria(){
		this.categoria = new ArrayList<Categoria>();
		Scanner sc = new Scanner(System.in);
        int opcao = 1;
		do{
        	System.out.println("Selecione as categorias (0 p/ parar a seleção)");
			//Listagem de categorias
        	for(Categoria categoria: Teste.categorias){
		        System.out.println(categoria.getIdCategoria()+" - "+categoria.getNomeCategoria());				
			}
        	
        	System.out.println("=====================");
        	System.out.println("Selecione a categoria ");
            opcao = sc.nextInt();
            
            if(opcao != 0 ){
            	boolean estaNaLista = false;
            	boolean jaAdicionado = false;
            	Categoria categoriaSelecionada = new Categoria();
            	//verifica se a opcao selecionada existe na lista            	
            	for(Categoria categoria: Teste.categorias){
            		if(categoria.getIdCategoria() == opcao){
            			estaNaLista = true;
            			categoriaSelecionada = categoria;
            			break; 
            		}
            	}
            	//verifica se ja foi adicionado            	
            	for(Categoria categoria: this.categoria){
            		if(categoria.getIdCategoria() == opcao){
            			jaAdicionado = true;
            			break; 
            		}
            	}
            	
            	if(estaNaLista == true && jaAdicionado == false){
            		this.categoria.add(categoriaSelecionada);
            		System.out.println("Categoria adicionada com sucesso - "+categoriaSelecionada.getNomeCategoria());            		
            	}else if(estaNaLista == false){
            		System.out.println("A categoria selecionada não existe na lista");            		           		
            	}else if(jaAdicionado == true){
            		System.out.println("A categoria selecionada já foi adicionada anteriormente");            		           		            		
            	}
            }else{
                System.out.println("Listagem de categorias encerrada");            	
            }
        	
        }while(opcao != 0);		
	}	
	/*
	 * Implementa a interface->IdAutoIncremento
	 * */
	public void gerarIdAutomatico(){
		if(Teste.fornecedores.isEmpty()){
			setIdFornecedor(1);
		}else{
			int lastId = Teste.fornecedores.get(Teste.fornecedores.size() - 1).idFornecedor + 1;			
			setIdFornecedor(lastId);			
		}
	} 
	
	/*
	 * GET's e SET's
	 * */		
	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public ArrayList<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(ArrayList<Categoria> categoria) {
		this.categoria = categoria;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}		
}
