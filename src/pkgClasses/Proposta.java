package pkgClasses;

public class Proposta implements IdAutoIncremento{
	private int idProposta;
	private int idPedido;
	private Fornecedor fornecedor;
	private String descricao;
	private double valorProposta;
	private int prazoEntrega;
	private boolean propostaAceita;
	
	public Proposta(){
		gerarIdAutomatico();
	}

	/*
	 * Informações sobre um pedido
	 * */
	public void visualizarProposta(){
		System.out.println("=====================");
		System.out.println("INFORMAÇÕES DA PROPOSTA");
		System.out.println("=====================");
		System.out.println("ID PROPOSTA: "+getIdProposta());
		System.out.println("ID PEDIDO: "+getIdPedido());
		System.out.println("FORNECEDOR: "+fornecedor.getNome()+" | "+fornecedor.getCnpj());
		System.out.println("DESCRIÇÃO: "+getDescricao());
		System.out.println("VALOR DA PROPOSTA: "+getValorProposta());
		System.out.println("PRAZO DE ENTREGA: "+getPrazoEntrega());
		
		if(isPropostaAceita()){
			System.out.println("PROPOSTA ACEITA");			
		}else{
			System.out.println("PROPOSTA EM ABERTO");						
		}
	}	
	
	/*
	 * Implementa a interface->IdAutoIncremento
	 * */
	public void gerarIdAutomatico(){
		if(Teste.propostas.isEmpty()){
			setIdProposta(1);
		}else{
			int lastId = Teste.propostas.get(Teste.propostas.size() - 1).idProposta + 1;			
			setIdProposta(lastId);			
		}
	} 	
	/*
	 * GET's e SET's
	 * */	
	public int getIdProposta() {
		return idProposta;
	}
	public void setIdProposta(int idProposta) {
		this.idProposta = idProposta;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorProposta() {
		return valorProposta;
	}
	public void setValorProposta(double valorProposta) {
		this.valorProposta = valorProposta;
	}
	public int getPrazoEntrega() {
		return prazoEntrega;
	}
	public void setPrazoEntrega(int prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	public boolean isPropostaAceita() {
		return propostaAceita;
	}
	public void setPropostaAceita(boolean propostaAceita) {
		this.propostaAceita = propostaAceita;
	}
}
