package modelo;

import java.time.LocalDate;

public class Mensalista extends Cliente{
	private int codPlano;
	private double valorMensalidade;
	private LocalDate ultimaParcelaPagaDate;
	private LocalDate dataAdesaoDate;
	
	public LocalDate getDataAdesaoDate() {
		return dataAdesaoDate;
	}

	public void setDataAdesaoDate(LocalDate dataAdesaoDate) {
		this.dataAdesaoDate = dataAdesaoDate;
	}

	public Mensalista(String nome, String cpf, String telefone, String enderecoString, int codPlano,double valorMensalidade) {
		super(nome, cpf, telefone, enderecoString);
		setCodPlano(codPlano);
		setValorMensalidade(valorMensalidade);;
		setDataAdesaoDate(LocalDate.now());
	}

	public int getCodPlano() {
		return codPlano;
	}


	public void setCodPlano(int codPlano) {
		this.codPlano = codPlano;
	}


	public double getValorMensalidade() {
		return valorMensalidade;
	}


	public void setValorMensalidade(double valorMensalidade) {
		if(valorMensalidade<0) {
			throw new IllegalArgumentException("valor mensalidade não pode ser negativo!");
		}
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
		return "================== mensalista ==================\n" +
	               "Nome: " + getNome()  +
	               " CPF: " + getCpf() + "\n" +
	               "Telefone: " + getTelefone()  +
	               " Mensalidade:" + getValorMensalidade() + 
	               "\n==============================================\n" 
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
	

