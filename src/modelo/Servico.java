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
	public void setValorUnidade(double valorUnidade) {
		if(valorUnidade<0) {
			throw new IllegalArgumentException("valor unidade de serviço não pode ser negativa!");
		}
		this.valorUnidade = valorUnidade;
		
	}
	
	//Construtor
	public Servico(int codServico, String descricao, double valorUnidade) {
		setCodServico(codServico);
		setDescricao(descricao);
		setValorUnidade(valorUnidade);
	}
	
	//Método que retonar a ficha dos Serviços
	public String ficha() {
		return "================== Serviço ==================\n" +
	               "Código do serviço: " + getCodServico()  +
	               " Descrição: " + getDescricao() + "\n" +
	               " Valor unidade " + getValorUnidade()  + 
	               "\n==============================================\n" 
	               ;
	}
	
}
	
