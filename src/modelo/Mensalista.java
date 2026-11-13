package modelo;

import java.time.LocalDate;

public class Mensalista extends Cliente{
	private int codPlano;
	private int valorMensalidade;
	private LocalDate vencimentoMensaDate;
	
	
	public Mensalista(String nome, String cpf, String telefone, String enderecoString, int codPlano,int valorMensalidade, LocalDate vencimentoMensaDate) {
		super(nome, cpf, telefone, enderecoString);
		this.codPlano = codPlano;
		this.valorMensalidade = valorMensalidade;
		this.vencimentoMensaDate = vencimentoMensaDate;
	}

	public int getCodPlano() {
		return codPlano;
	}


	public void setCodPlano(int codPlano) {
		this.codPlano = codPlano;
	}


	public int getValorMensalidade() {
		return valorMensalidade;
	}


	public void setValorMensalidade(int valorMensalidade) {
		this.valorMensalidade = valorMensalidade;
	}


	public LocalDate getVencimentoMensaDate() {
		return vencimentoMensaDate;
	}


	public void setVencimentoMensaDate(LocalDate vencimentoMensaDate) {
		this.vencimentoMensaDate = vencimentoMensaDate;
	}
	
	
}
	

