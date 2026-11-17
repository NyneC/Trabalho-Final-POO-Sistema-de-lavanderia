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
    public void CadastrarCliente(Cliente c){

    	if (c != null) {
            clientes.add(c); // .add(c): Inserção no fim do vetor dinâmico (aloca espaço automaticamente)
            salvarNoArquivo(); // Salva no disco imediatamente
        }

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
    public void PagarCobranca(Cobranca c){

    	if (c != null) {
    		LocalDate hoje = LocalDate.now();
            c.setPago(true); // Altera o status do boolean para true
            c.setDataPagamentoDate(hoje);
            if (c.getCliente() instanceof Mensalista) {
                Mensalista m = (Mensalista) c.getCliente();
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
   
    private void carregarDoArquivo()  {
        File arquivo = new File(Historico);
        if (!arquivo.exists()) {
            return; // Se o arquivo ainda não existe (primeira execução), sai sem erro
        }

        try (Scanner leitor = new Scanner(arquivo)) {
            String secaoAtual = "";
            
            //faz o ponteiro ler linha a linha do arquivo 
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                
                //sempre q encontrar o cabeçalho de uma seção faz o pontero avançar para os dados
                if (linha.equals("---CLIENTES---") || linha.equals("---SERVICOS---")) {
                    secaoAtual = linha;//atualiza a seção atual 
                    continue; // Pula a linha do cabeçalho
                }
 
                //faz um vetor de string q lê e armazena dados te encontrar um ;
                String[] partes = linha.split(";");
                
                
                //se a seão atual for clinetes começa a add na memoria ram 
                if (secaoAtual.equals("---CLIENTES---") && partes.length >= 4) {
                	
                	//lê cada parte do aruqivo txt e guarda em uma variavel
                    String tipo = partes[0];
                    String cpf = partes[1];     
                    String nome = partes[2];
                    String telefone = partes[3];
                    String enderecoString= partes[4];

                    if (tipo.equals("M")) {
                    	double valor = Double.parseDouble(partes[5]); // atof() do C
                    	int codPlano = Integer.parseInt(partes[6]); 
                        clientes.add(new Mensalista(nome, cpf, telefone, enderecoString,codPlano,valor));
                         } else {
                        	 clientes.add(new Irregular(nome, cpf, telefone, enderecoString));
                         }
                } else if (secaoAtual.equals("---SERVICOS---") && partes.length >= 3) {
                    int id = Integer.parseInt(partes[0]);
                    String nome = partes[1];
                    double preco = Double.parseDouble(partes[2]);
                    servicos.add(new Servico(id, nome, preco));
                }
            }
        } catch (Exception e) {
            // Se houver erro de parsing na leitura, inicia com listas limpas
        	e.printStackTrace();
        	throw new IllegalArgumentException("erro ao ler dados clientes");
        }
    }

}