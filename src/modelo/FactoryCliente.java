package modelo;

public class FactoryCliente  {
	
	//cria um mensalista
	public static Cliente createMensalista(
						String nome, String cpf, String telefone, 
						String enderecoString, int codPlano,double valorMensalidade) {
		return new Mensalista(nome, cpf, telefone, enderecoString,
								codPlano, valorMensalidade);
	}
	
	
	//cria um mensalista
	public static Cliente createIregularCliente(String nome, String cpf, String telefone, 
						String enderecoString) {
		return new Irregular(nome, cpf, telefone, enderecoString);
	}
}
