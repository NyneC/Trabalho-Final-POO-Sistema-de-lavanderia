package modelo;

public class Servico {
	private int codServico;
	private String descricao;
	private double valorUnidade;
	
	
	public int getCodServico() {
		return codServico;
	}
	public void setCodServico(int codServico) {
		this.codServico = codServico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValorUnidade() {
		return valorUnidade;
	}
	public void setValorUnidade(int valorUnidade) {
		this.valorUnidade = valorUnidade;
	}
	
	
	public Servico(int codServico, String descricao, double valorUnidade) {
		this.codServico = codServico;
		this.descricao = descricao;
		this.valorUnidade = valorUnidade;
	}
	
	
}
	
