package pkgClasses;
import java.util.ArrayList;
import java.util.Scanner;

public class Teste{
	
	public static ArrayList<Usuario> usuarios;
	public static ArrayList<Fornecedor> fornecedores;
	public static ArrayList<Categoria> categorias;
	public static ArrayList<Pedido> pedidos;
	public static ArrayList<Proposta> propostas;
	public static ArrayList<Produto> produtos;
	public static Fornecedor fornecedorLogado;
	
	public static void main(String args[]){
		Teste.iniciaRegistros();
		Teste execucao = new Teste();
		execucao.menuPrincipal();
	}
	
	/*
	 * Chama o menu principal
	 **/
	public void menuPrincipal(){
		escreverCabecalho("MENU PRINCIPAL");
		System.out.println("Digite a opção desejada");
		System.out.println("1 - Entrar como usuário");
		System.out.println("2 - Entrar como fornecedor");
		System.out.println("3 - Solicitar cadastro de fornecedor");
		
		Scanner sc = new Scanner(System.in);
		int opcao = sc.nextInt();
		switch(opcao){
			case 1:
				login(1);
				break;
			case 2:
				login(2);				
				break;
			case 3:
				cadastrarFornecedor();
		        menuPrincipal();				
				break;				
			default:
				menuPrincipal();				
				break;
			
		}
	}

	/*
	 * LOGIN / Função para entrar dados
	 * */	
	public void login(int opcao){
		escreverCabecalho("TELA DE LOGIN");
		Scanner sc = new Scanner(System.in);            
        System.out.println("Digite o login ");
        String login = sc.nextLine();        
        
        System.out.println("Digite a senha ");
        String senha = sc.nextLine();
        
        Object usuarioLogado = selecionaUsuarioPorLogin(login, senha, opcao);        	
        if(usuarioLogado == null){
        	System.out.println("Login ou senha inválidos, por favor tente novamente");
        	login(opcao);
        }else{
        	System.out.println("Logado com sucesso");
        	if(opcao == 1){
        		menuUsuario();
        	}else if(opcao == 2){
        		fornecedorLogado = (Fornecedor)usuarioLogado;
        		menuFornecedor();
        	}
        }
	}
	
	/*
	 * LOGIN / Valida os dados
	 * */	
	public Object selecionaUsuarioPorLogin(String login, String senha, int opcao){		   
		Object usuarioSelecionado = null;
		//Procura pelo usuario
		if(opcao == 1){
			for(Usuario usuario: Teste.usuarios){
			    if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)){
			    	usuarioSelecionado = usuario;
			    	break;
				}	
			}			
		}else if(opcao == 2){
			for(Fornecedor fornecedor: Teste.fornecedores){
			    if (fornecedor.getLogin().equals(login) && fornecedor.getSenha().equals(senha) && fornecedor.isAtivo()){
			    	usuarioSelecionado = fornecedor;
			    	break;
				}	
			}			
		}
		return usuarioSelecionado;
	}

	/*
	 * Cadastrar fornecedor
	 * */	
	public void cadastrarFornecedor(){
		escreverCabecalho("CADASTRAR FORNECEDOR");
		
		Scanner sc = new Scanner(System.in); 
		Fornecedor novoFornecedor = new Fornecedor();
                
        System.out.println("Digite o NOME do fornecedor ");
        novoFornecedor.setNome(sc.nextLine());
        
        System.out.println("Digite o CNPJ do fornecedor ");
        novoFornecedor.setCnpj(sc.nextLine());

        System.out.println("Digite o LOGIN do fornecedor ");
        novoFornecedor.setLogin(sc.next());

        System.out.println("Digite a SENHA do fornecedor ");
        novoFornecedor.setSenha(sc.next());
        
        int opcao = 1;
        novoFornecedor.addCategoria();
        
        Teste.fornecedores.add(novoFornecedor);
        System.out.println("Fornecedor cadastrado com sucesso");            	
	}
	
	
	/*
	 * Chama o menu do usuario
	 **/	
	public void menuUsuario(){
		escreverCabecalho("MENU DO USUÁRIO");		
		System.out.println("Digite a opção desejada");
		System.out.println("1 - Fazer pedido");
		System.out.println("2 - Ver meus pedidos");
		System.out.println("3 - Ver propostas para meus pedidos");
		System.out.println("4 - Autorizar fornecedor");
		System.out.println("5 - Cadastrar categoria");		
		System.out.println("6 - Sair");
		
		Scanner sc = new Scanner(System.in);
		int opcao = sc.nextInt();
		switch(opcao){
		case 1:
			fazerPedido();
			break;
		case 2:
			escreverCabecalho("MEUS PEDIDOS");
			if(Teste.pedidos.isEmpty()){
				System.out.println("Você ainda não fez pedidos.");
			}else{
				listarPedidos();
			}
			menuUsuario();			
			break;
		case 3:
			escreverCabecalho("LISTA DE PROPOSTAS");
			if(Teste.propostas.isEmpty()){
				System.out.println("Você não recebeu propostas ainda.");
			}else{
				listarPropostas();
				System.out.println("DIGITE O CÓDIGO DA PROPOSTA PARA ACEITAR. 0 P/ VOLTAR AO MENU");
				opcao = sc.nextInt();
				if(opcao != 0){
					aceitarProposta(opcao);
				}else{
					menuUsuario();
				}
			}
			menuUsuario();			
			break;
		case 4:
			listarFornecedores();
			System.out.println("Selecione através do ID do FORNECEDOR");
			opcao = sc.nextInt();
			autorizarFornecedor(opcao);
			menuUsuario();			
			break;	
		case 5:
			cadastrarCategoria();
	        menuUsuario();			
			break;
		case 6:
			menuPrincipal();			
			break;			
		default:
			System.out.println("Opção inválida");
			menuUsuario();
			break;
		}		
	}
	
	/*
	 * Fazer pedido
	 * */
	public void fazerPedido(){
		escreverCabecalho("FAZER PEDIDO");
		Scanner sc = new Scanner(System.in);
		Pedido novoPedido = new Pedido();
		
		novoPedido.escolherCategoria();

        novoPedido.addItens(novoPedido.getCategoria());
        		
        System.out.println("Digite a DESCRIÇÃO do pedido");
        novoPedido.setDescricao(sc.nextLine());
        
        System.out.println("Digite o VALOR DESEJADO");
        novoPedido.setValorPedido(sc.nextDouble());

        System.out.println("Digite o PRAZO IDEAL em numero de dias (Exemplo 1, 2...)");
        novoPedido.setPrazoIdeal(sc.nextInt());        

        System.out.println("Digite o GRAU DE URGÊNCIA");
        System.out.println("1 para POUCO URGENTE");
        System.out.println("2 para URGENTE");
        System.out.println("3 para CASO DE VIDA OU MORTE");
        novoPedido.setGrauUrgencia(sc.nextInt());          

        Teste.pedidos.add(novoPedido);        
        
        System.out.println("Pedido cadastrado com sucesso");
        novoPedido.visualizarPedido();
        
        menuUsuario();
	}
		
	/*
	 * Cadastrar categoria
	 * */		
	public void cadastrarCategoria(){
		escreverCabecalho("CADASTRAR CATEGORIA");
		
		Scanner sc = new Scanner(System.in); 
		Categoria novaCategoria = new Categoria();
				
		System.out.println("Digite o NOME da categoria");
		novaCategoria.setNomeCategoria(sc.nextLine());
		
		System.out.println("Categoria adicionada com sucesso");
        Teste.categorias.add(novaCategoria);
        listarCategorias();
	}
	
	/*
	 * Menu do fornecedor
	 * */
	public void menuFornecedor(){
		escreverCabecalho("MENU DO FORNECEDOR");
		
		System.out.println("Digite a opção desejada");		
		System.out.println("1 - Consultar pedidos");
		System.out.println("2 - Ver minhas propostas");
		System.out.println("3 - Fazer proposta");				
		System.out.println("4 - Sair");
		
		Scanner sc = new Scanner(System.in);
		int opcao = sc.nextInt();
		switch (opcao){
			case 1:
				escreverCabecalho("LISTAGEM DE PEDIDOS");
				if(Teste.pedidos.isEmpty()){
					System.out.println("Não existem pedidos cadastrados");
				}else{
					listarPedidos(Teste.fornecedorLogado);
				}
				menuFornecedor();
				break;
			case 2:
				escreverCabecalho("LISTAGEM DE PROPOSTAS");				
				
				if(Teste.propostas.isEmpty()){
					System.out.println("Não existem propostas cadastradas");
				}else{
					listarPropostas(Teste.fornecedorLogado);
				}
				menuFornecedor();				
				break;
			case 3:
				fazerProposta();
				menuFornecedor();				
				break;
			case 4:
				Teste.fornecedorLogado = null;
				menuPrincipal();				
				break;				
			default:
				System.out.println("Opção inválida");
				menuFornecedor();
				break;
		}
	}
	
	
	/*
	 * Fazer proposta
	 * */
	public void fazerProposta(){
		escreverCabecalho("FAZER PROPOSTA");
		
		if(Teste.pedidos.isEmpty()){
			System.out.println("Não existem pedidos, volte quando houver algum pedido.");
		}else{	
				
			System.out.println("Selecione o pedido para o qual você deseja enviar uma proposta");
			listarPedidos();
			System.out.println("Selecione através do ID do PEDIDO");

			Proposta novaProposta = new Proposta();
			novaProposta.setFornecedor(Teste.fornecedorLogado);
			
			Scanner sc = new Scanner(System.in);
			int idPedido = sc.nextInt();
			sc.nextLine();
			
			//Verifica se o pedido existe
			boolean estaNaLista = false;
			Pedido pedidoSelecionado;
	    	for(Pedido pedido: Teste.pedidos){
	    		if(pedido.getIdPedido() == idPedido){
	    			estaNaLista = true;
	    			pedidoSelecionado = pedido;
	    			break; 
	    		}
	    	}
	    	
	    	if(estaNaLista){
	    		novaProposta.setIdPedido(idPedido);
	    			    		
	    		System.out.println("Digite a DESCRIÇÃO de sua proposta");
	    		novaProposta.setDescricao(sc.nextLine());
	    		
	    		System.out.println("Digite o VALOR da proposta");
	    		novaProposta.setValorProposta(sc.nextDouble());
	
	    		System.out.println("Digite o PRAZO DE ENTREGA");
	    		novaProposta.setPrazoEntrega(sc.nextInt());
	    		
	    		Teste.propostas.add(novaProposta);
	    		System.out.println("Proposta cadastrada com sucesso");
	    	}else{
	    		System.out.println("Esse pedido não existe, tente novamente");
	    		fazerProposta();
	    	}
		}
	}

	/*
	 * Listar pedidos
	 * */	
	public void listarPedidos(){
		for(Pedido pedido : Teste.pedidos){
			pedido.visualizarPedido();
		}
	}
	
	/*
	 * Listar pedidos
	 * */	
	public void listarPedidos(Fornecedor fornecedor){
		for(Categoria categoriaFornecedor : fornecedor.getCategoria()){
			for(Pedido pedido : Teste.pedidos){
				if(categoriaFornecedor.getIdCategoria() == pedido.getCategoria().getIdCategoria()){
					pedido.visualizarPedido();
				}
			}
		}
	}	
	
	/*
	 * Listar propostas
	 * */		
	public void listarPropostas(){
		for(Proposta proposta : Teste.propostas){
			proposta.visualizarProposta();
		}		
	}

	/*
	 * Listar propostas para fornecedores que possuem categorias cadastradas
	 * */			
	public void listarPropostas(Fornecedor fornecedor){
		for(Proposta proposta : Teste.propostas){
			if(proposta.getFornecedor().getIdFornecedor() == fornecedor.getIdFornecedor()){
				proposta.visualizarProposta();
			}
		}		
	}
	/*
	 * Listar fornecedor
	 * */
	public void listarFornecedores(){
		for(Fornecedor fornecedor : Teste.fornecedores){
			fornecedor.visualizarFornecedor();			
		}
	}
	/*
	 * Usuário aceita proposta de fornecedor
	 * */
	public void aceitarProposta(int opcao){
		boolean estaNaLista = false;
		Proposta propostaSelecionada = new Proposta();
		for(Proposta proposta : Teste.propostas){
			if(proposta.getIdProposta() == opcao){
				proposta.setPropostaAceita(true);
				propostaSelecionada = proposta;
				estaNaLista = true;
				break;
			}
		}
		
		for(Pedido pedido : Teste.pedidos){
			if(pedido.getIdPedido() == propostaSelecionada.getIdPedido()){
				pedido.setPedidoEncerrado(true);
			}
		}

		
		if(estaNaLista){
			System.out.println("A proposta foi aceita.");
		}
	}

	public void autorizarFornecedor(int opcao){
		boolean estaNaLista = false;
		for(Fornecedor fornecedor : Teste.fornecedores){
			if(fornecedor.getIdFornecedor() == opcao){
				fornecedor.setAtivo(true);
				estaNaLista = true;
				break;
			}
		}
		
		if(estaNaLista){
			System.out.println("Fornecedor autorizado.");
		}		
	}
	
		
	/*
	 * Listar categorias
	 * */	
	public void listarCategorias(){
		for(Categoria categoria : Teste.categorias){
			System.out.println(categoria.getIdCategoria()+" - "+categoria.getNomeCategoria());
		}
	}	
	
	/*
	 * Inicia registros ao iniciar o sistema
	 * */
	public static void iniciaRegistros(){
		//Inicia o ArrayList usuarios
		Teste.usuarios = new ArrayList<Usuario>();
		Teste.usuarios.add(new Usuario("admin", "admin"));
		
		//Inicia o ArrayList fornecedores
		Teste.fornecedores = new ArrayList<Fornecedor>();
		Teste.fornecedores.add(new Fornecedor("Brolin", "brolin", "1234", true));		
		Teste.fornecedores.add(new Fornecedor("Jonathan", "joninha", "1234", true));		
		Teste.fornecedores.add(new Fornecedor("Gutenberg", "gutim", "1234", true));		

		//Inicia o ArrayList categorias
		Teste.categorias = new ArrayList<Categoria>();
		Teste.categorias.add(new Categoria("Material de limpeza"));
		Teste.categorias.add(new Categoria("Material de escritório"));		
		Teste.categorias.add(new Categoria("Segurança"));		
		Teste.categorias.add(new Categoria("Alimentos"));
		
		//Adiciono categorias aos fornecedores cadastrados
		Teste.fornecedores.get(0).setCategoria(Teste.categorias);
		Teste.fornecedores.get(1).setCategoria(Teste.categorias);
		Teste.fornecedores.get(2).setCategoria(Teste.categorias);

		//Inicia o ArrayList produtos
		Teste.produtos = new ArrayList<Produto>();
		Teste.produtos.add(new Produto(Teste.categorias.get(0), "Detergente"));
		Teste.produtos.add(new Produto(Teste.categorias.get(0), "Sabão"));
		Teste.produtos.add(new Produto(Teste.categorias.get(0), "Vassoura"));
		Teste.produtos.add(new Produto(Teste.categorias.get(0), "Rodo"));
		Teste.produtos.add(new Produto(Teste.categorias.get(0), "Balde"));
		Teste.produtos.add(new Produto(Teste.categorias.get(1), "Resma de folha"));
		Teste.produtos.add(new Produto(Teste.categorias.get(1), "Caneta"));
		Teste.produtos.add(new Produto(Teste.categorias.get(1), "Papel ofício"));
		Teste.produtos.add(new Produto(Teste.categorias.get(1), "Lápis"));
		Teste.produtos.add(new Produto(Teste.categorias.get(1), "Borracha"));
		Teste.produtos.add(new Produto(Teste.categorias.get(2), "Sensor de calor"));
		Teste.produtos.add(new Produto(Teste.categorias.get(2), "Alarme"));
		Teste.produtos.add(new Produto(Teste.categorias.get(2), "Arama farpado"));
		Teste.produtos.add(new Produto(Teste.categorias.get(2), "Câmera de segurança"));
		Teste.produtos.add(new Produto(Teste.categorias.get(2), "Cerca elétrica"));
		Teste.produtos.add(new Produto(Teste.categorias.get(3), "KIT - Sexta básica"));		
		Teste.produtos.add(new Produto(Teste.categorias.get(3), "Feijão"));		
		Teste.produtos.add(new Produto(Teste.categorias.get(3), "Arroz"));		
		Teste.produtos.add(new Produto(Teste.categorias.get(3), "Carne - bovino"));		
		Teste.produtos.add(new Produto(Teste.categorias.get(3), "Frango"));		
		
		//Inicia o ArrayList pedidos
		Teste.pedidos = new ArrayList<Pedido>();

		//Inicia o ArrayList propostas
		Teste.propostas = new ArrayList<Proposta>();
	}
	
	/*
	 * Elabora o cabeçalho para os menus
	 * */
	public void escreverCabecalho(String texto){
		System.out.print("\n\n");
		for(int i = 0; i < texto.length(); i++){
			System.out.print("=");
		}
		System.out.print("\n");
		System.out.println(texto);		
		for(int i = 0; i < texto.length(); i++){
			System.out.print("=");
		}
		System.out.print("\n");
		
	}
	

}