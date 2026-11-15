package modelo;

import java.time.LocalDate;

public class Mensalista extends Cliente{
	private int codPlano;
	private int valorMensalidade;
	private LocalDate ultimaParcelaPagaDate;
	private LocalDate dataAdesaoDate;
	
	public LocalDate getDataAdesaoDate() {
		return dataAdesaoDate;
	}

	public void setDataAdesaoDate(LocalDate dataAdesaoDate) {
		this.dataAdesaoDate = dataAdesaoDate;
	}

	public Mensalista(String nome, String cpf, String telefone, String enderecoString, int codPlano,int valorMensalidade) {
		super(nome, cpf, telefone, enderecoString);
		this.codPlano = codPlano;
		this.valorMensalidade = valorMensalidade;
		this.dataAdesaoDate = LocalDate.now();
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


	
	
	public LocalDate getUltimaParcelaPagaDate() {
		return ultimaParcelaPagaDate;
	}

	public void setUltimaParcelaPagaDate(LocalDate ultimaParcelaPagaDate) {
		this.ultimaParcelaPagaDate = ultimaParcelaPagaDate;
	}

	@Override
	public String ficha() {
		return "======================\n" +
	               "Nome: " + getNome()  +
	               "CPF: " + getCpf() + "\n" +
	               "Telefone: " + getTelefone()  +
	               "Mensalidade:" + getValorMensalidade()+  "\n" 
	               ;
	}
	
	public String fichaompleta() {
		
		return ficha() + "ultima Mensalidade: " + getUltimaParcelaPagaDate(); 
		
	}
	
	@Override
	double Desconto() {
		return 0.5;
		
	}
	
	
}
	

