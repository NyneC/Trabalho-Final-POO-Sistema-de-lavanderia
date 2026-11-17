package modelo;

import java.util.*;

public abstract class Cliente {
	private String nome;
	private String cpf;
	private String telefone;
	private String enderecoString;
	private List<Cobranca> pendencias = new ArrayList<>();
	private double saldoDevido;
	
	
	public double getSaldoDevido() {
		return saldoDevido;
	}

	public void setSaldoDevido(double saldoDevido) {
		if(saldoDevido < 0) {
			throw new IllegalArgumentException("saldoDevido do cliente não pode ser negativo!");
		}
		this.saldoDevido = saldoDevido;
		
	}

	public void addCobranca(Cobranca zeCobranca) {
		pendencias.add(zeCobranca);
	}
	
	public List<Cobranca> getPendencias() {
		return pendencias;
	}
	public double calculaTotalDevido() {
		double total = 0;
		for(Cobranca zeCobranca: pendencias) {
			total+=zeCobranca.getValorTotal();
		}
		return total;
	}
	
	
	
	//CPF
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		String cpfNumeros = cpf.replaceAll("[-.]", "");
	  
		if(cpfNumeros.length() != 11) {
			throw new IllegalArgumentException("cpf invalido!");
		}
		this.cpf = cpf;
		
		
	    
	}
	
	//Telefone
	public String getTelefone() {
		return telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if (nome.equals("")) {
	        throw new IllegalArgumentException("O nome  não pode ficar vazio!");
	    }
		this.nome = nome;
	}
	
	public void setTelefone(String telefone) {
		
		String teleNumeros = telefone.replaceAll("[-.]", "");
		  
		if(teleNumeros.length() != 11) {
			throw new IllegalArgumentException("telefone invalido!");
		
		} 
		this.telefone = teleNumeros;
	    
	}
	
	//Endereço
	public String getEndereco() {
		return enderecoString;
	}
	public void setEnderecoString(String enderecoString) {
		if(enderecoString.equals("")) {
			throw new IllegalArgumentException("Endereço não pode estar vazio!");
		}
		this.enderecoString = enderecoString;
	}
	
	//construtor
	public Cliente(String nome,String cpf, String telefone, String enderecoString) {
		setNome(nome);
		setCpf(cpf);
		setTelefone(telefone);
		setEnderecoString(enderecoString);;
	}
	
	//clientes ficha
	public abstract String ficha();
	abstract double Desconto();
	
	
}
   