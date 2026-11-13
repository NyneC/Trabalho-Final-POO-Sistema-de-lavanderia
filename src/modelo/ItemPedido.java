package modelo;

import java.util.SortedMap;

public class ItemPedido {
	private int quantidade;
	private double valorTotal;
	private Servico servico;
	
	//Quantidade
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	//ValorTotal
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	//ItemPedido
	public ItemPedido(Servico servico,int quantidade) {
		super();
		this.servico = servico;
		this.quantidade = quantidade;
		this.valorTotal = valorTotalServiço();
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
	
	
}
