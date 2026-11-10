package modelo;

public class Cliente {
	private String nome;
	private String cpf;
	private String telefone;
	private String enderecoString;
	
	//CPF
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	//Telefone
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	//Endereço
	public String getEnderecoString() {
		return enderecoString;
	}
	public void setEnderecoString(String enderecoString) {
		this.enderecoString = enderecoString;
	}
	
	//construtor
	public Cliente(String cpf, String telefone, String enderecoString) {
		super();
		this.cpf = cpf;
		this.telefone = telefone;
		this.enderecoString = enderecoString;
	}
	
	
	
}
   