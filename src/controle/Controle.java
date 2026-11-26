package controle;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import modelo.*;

public class Controle {
	private static final String Historico = "dados.txt";
	
    private List<Cliente> clientes = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();
    private List<Cobranca> cobrancas = new ArrayList<>();
    
    private int CodPedido = 0;
    private int CodPlano = 0;
    
    public int proximoCodPedido() {
        return CodPedido++;
    }

    public int gproximoCodPlano() {
        return CodPlano++;
    }
    
    public void ControleLavanderia()  {
        carregarDoArquivo(); // Carrega os dados salvos no dados.txt para a memória RAM
    }

    //Funções cliente
    public Cliente CadastrarCliente( int tipo,String nome, String cpf, String telefone, 
			String enderecoString, double valorMensalidade){

    	Cliente c;
    	if(tipo == 1) {
    		c = FactoryCliente.createMensalista(nome, cpf, telefone, enderecoString, gproximoCodPlano(), valorMensalidade);
    	} else {
			c = FactoryCliente.createIregularCliente(nome, cpf, telefone, enderecoString);
		}
    	
    	if (c != null) {
            clientes.add(c); // .add(c): Inserção no fim do vetor dinâmico (aloca espaço automaticamente)
            salvarNoArquivo(); // Salva no disco imediatamente
        }

    	return c;
    }
    public List<Cliente> ListarClientes(){

        return clientes;

    }
    //Funções serviço
    public List<Servico> ListarServicos(){

        return servicos;

    }
    public void CadastrarServico(Servico s) {
        if (s != null) {
            servicos.add(s);
            salvarNoArquivo();
        }
    }
    //Funções pedido
    public void CriarPedido(Pedido p){

    	if (p != null) {
    		p.setCodPedido(proximoCodPedido());
            pedidos.add(p);
            salvarNoArquivo();
        }

    }
    public void AdicionarServicoAoPedido(Pedido p,Servico s,int qtd){
    	
    	if (p != null && s != null && qtd > 0) {
            p.addItem(new ItemPedido(s, qtd)); // Chama a função interna do próprio Pedido
            salvarNoArquivo();
        }

    }
    public List<Pedido> ListarPedidos(){

        return pedidos;

    }
    //Funções cobrança
    public void GerarCobranca(Cobranca c){

    	if (c != null) {
            cobrancas.add(c);
            salvarNoArquivo();
        }

    }
    public List<Cobranca> ListaCobranca(){

        return cobrancas;

    }
    public void PagarCobranca(Cobranca c, Cliente zeCliente){

    	if (c != null) {
    		LocalDate hoje = LocalDate.now();
            c.setPago(true); // Altera o status do boolean para true
            c.setDataPagamentoDate(hoje);
            if (zeCliente instanceof Mensalista) {
                Mensalista m = (Mensalista) zeCliente;
                m.setUltimaParcelaPagaDate(hoje);
            }
            
            salvarNoArquivo();
        }

    }
    
    public double pendencias(Cliente zeCliente) {
    	double total= 0 ;
    	for(Cobranca zeCobranca:zeCliente.getPendencias()) {
    		total+=zeCobranca.getValorTotal();
    	}
    	zeCliente.setSaldoDevido(total);
    	return total;
    }
    
    
    
    
    
    //ARQUIVOS
    private void salvarNoArquivo() {
        // 'try-with-resources': Abre o arquivo e fecha automaticamente no final (como dar fclose)
        try (PrintWriter pw = new PrintWriter(new File(Historico))) {
            pw.println("---CLIENTES---");
            for (Cliente c : clientes) {
                // 'instanceof': Pergunta em tempo de execução "Esse cliente c é do tipo Mensalista?"
                String tipo = (c instanceof Mensalista) ? "M" : "A";
                // Casting: ((Mensalista) c) avisa o Java "trate a variável c como Mensalista para acessar getValorMensalidade()"
                double valor = (c instanceof Mensalista) ? ((Mensalista) c).getValorMensalidade() : 0.0;
                int codPlano = (c instanceof Mensalista) ? ((Mensalista) c).getCodPlano():0;
                // Grava uma linha de texto separada por ';' (formato CSV/Texto)
                pw.println(tipo + ";" + c.getCpf() + ";" + c.getNome() + ";" + c.getTelefone() + ";" + c.getEndereco()+ ";"  + valor+ ";" + codPlano );
            }
            
            pw.println("---SERVICOS---");
            for (Servico s : servicos) {
                pw.println(s.getCodServico() + ";" + s.getDescricao() + ";" + s.getValorUnidade());
            }
        } catch (Exception e) {
            // Em caso de erro de I/O, apenas ignora para manter simples no ambiente acadêmico
        }
    }
   
    private void carregarDoArquivo() {
        File arquivo = new File(Historico);
        if (!arquivo.exists()) {
            return; // Se o arquivo ainda não existe, sai sem erro
        }

        try (Scanner leitor = new Scanner(arquivo)) {
            String secaoAtual = "";

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();

                if (linha.isEmpty()) {
                    continue;
                }

                // Identifica qual seção está sendo lida
                if (linha.equals("---CLIENTES---") || linha.equals("---SERVICOS---")) {
                    secaoAtual = linha;
                    continue;
                }

                String[] dados = linha.split(";");

                if (secaoAtual.equals("---CLIENTES---") && dados.length >= 7) {
                    String tipo = dados[0];
                    String cpf = dados[1];
                    String nome = dados[2];
                    String telefone = dados[3];
                    String endereco = dados[4];
                    double valorMensalidade = Double.parseDouble(dados[5]);
                    int codPlano = Integer.parseInt(dados[6]);

                    Cliente c;
                    if (tipo.equals("M")) {
                        // Usa a Factory para recriar o Mensalista
                        c = FactoryCliente.createMensalista(nome, cpf, telefone, endereco, codPlano, valorMensalidade);
                        
                        // Mantém o contador de planos atualizado para não gerar códigos repetidos
                        if (codPlano >= CodPlano) {
                            CodPlano = codPlano + 1;
                        }
                    } else {
                        // Usa a Factory para recriar o cliente Irregular
                        c = FactoryCliente.createIregularCliente(nome, cpf, telefone, endereco);
                    }

                    clientes.add(c);

                } else if (secaoAtual.equals("---SERVICOS---") && dados.length >= 3) {
                    int codServico = Integer.parseInt(dados[0]);
                    String descricao = dados[1];
                    double valorUnidade = Double.parseDouble(dados[2]);

                    servicos.add(new Servico(codServico, descricao, valorUnidade));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
    }

}