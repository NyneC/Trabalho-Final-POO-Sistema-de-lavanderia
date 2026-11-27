package modelo;

public class ItemPedido {
	private int quantidade;
	private double valorTotal;
	private Servico servico;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		if (quantidade < 1) {
			throw new IllegalArgumentException("A quantidade do item deve ser maior que zero!");
		}
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		if (valorTotal < 0) {
			throw new IllegalArgumentException("O valor total do item não pode ser negativo!");
		}
		this.valorTotal = valorTotal;
	}

	public ItemPedido(Servico servico, int quantidade) {
		setServico(servico);
		setQuantidade(quantidade);
		setValorTotal(valorTotalServiço());
	}

	public double valorTotalServiço() {
		return servico.getValorUnidade() * getQuantidade();
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		if (servico == null) {
			throw new IllegalArgumentException("O serviço do item do pedido não pode ser nulo!");
		}
		this.servico = servico;
	}

	public String ficha() {
		return "\n++++++++++++++++++++++++++++++\n" +
				"Serviço: " + servico.getDescricao() +
				" Quantidade: " + getQuantidade() + "\n" +
				" Valor total: " + getValorTotal() +
				"\n++++++++++++++++++++++++++++++";
	}
}