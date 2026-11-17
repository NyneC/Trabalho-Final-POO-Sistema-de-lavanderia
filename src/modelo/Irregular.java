package modelo;

public class Irregular extends Cliente {

	public Irregular(String nome, String cpf, String telefone, String enderecoString) {
		super(nome, cpf, telefone, enderecoString);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String ficha() {
		return "==============================================\n" +
	               "Nome: " + getNome()  +
	               " CPF: " + getCpf() + "\n" +
	               "Telefone: " + getTelefone() + 
	               " Endereço:"+ getEndereco() +"\n" +
	               "==============================================\n";
	}
	
	@Override
	double Desconto() {
		return 0.0;
		
	}
	
}
