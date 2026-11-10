package modelo;

import java.util.SortedMap;

public class ItemPedido {
	private int quantidade;
	private int valorTotal;
	private Servico servico;
	
	//Quantidade
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	//ValorTotal
	public int getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	//ItemPedido
	public ItemPedido(int quantidade, int valorTotal) {
		super();
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
	}
	
	//calcular o valor total do serviço 
	public void valorTotalServiço(){
		int soma = servico.getValorUnidade() * getQuantidade();
		setValorTotal(valorTotal);	
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	
}
