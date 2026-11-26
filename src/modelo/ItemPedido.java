package modelo;

public class ItemPedido {
	private int quantidade;
	private double valorTotal;
	private Servico servico;
	
	//Quantidade
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		if(quantidade<1) {
			throw new IllegalArgumentException("Quantidade de itemPedido não pode ser negativo!");
		}
		this.quantidade = quantidade;
	}
	
	//ValorTotal
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		if(valorTotal<1) {
			throw new IllegalArgumentException("valor total de itemPedido não pode ser negativo!");
		}
		this.valorTotal = valorTotal;
	}
	
	//ItemPedido
	public ItemPedido(Servico servico,int quantidade) {
		super();
		this.servico = servico;
		this.quantidade = quantidade;
		 setValorTotal(valorTotalServiço());
	}
	
	//calcular o valor total do serviço 
	public double valorTotalServiço(){
		double soma = servico.getValorUnidade() * getQuantidade();
		
		return soma;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	   public String ficha() {
			return 
					"\n++++++++++++++++++++++++++++++\n"+
		               "Serviço: " + servico.getDescricao()  +
		               " Quantidade: " + getQuantidade() + "+\n" +
		               " Valor total: " + getValorTotal()  +
		              "\n++++++++++++++++++++++++++++++"
		               ;
	   }
	
}
