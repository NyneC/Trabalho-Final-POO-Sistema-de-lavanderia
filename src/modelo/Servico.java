package modelo;

public class Servico {
	private int codServico;
	private String descricao;
	private int valorUnidade;
	
	
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
	public int getValorUnidade() {
		return valorUnidade;
	}
	public void setValorUnidade(int valorUnidade) {
		this.valorUnidade = valorUnidade;
	}
	
	
	public Servico(int codServico, String descricao, int valorUnidade) {
		this.codServico = codServico;
		this.descricao = descricao;
		this.valorUnidade = valorUnidade;
	}
	
	
}
	
