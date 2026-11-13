package Viewer;

import controle.*;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Servico;
import modelo.Cobranca;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class InterfaceLavaderia {
    
    public static void main(String[] args) {
    
        Controle sistema_controle = new Controle();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU_LAVANDERIA =====");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar serviço");
            System.out.println("3 - Registrar pedido");
            System.out.println("4 - Consultar cobranças");
            System.out.println("5 - Registrar pagamento");
            System.out.println("6 - Listar clientes e pedidos");
            System.out.println("7 - Sair");
            System.out.println("\nEscolha uma opção:");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado
            
            switch (opcao) {
                case 1:
                    // CADASTRAR CLIENTE
                    System.out.println("\n--- CADASTRAR CLIENTE ---");
                    System.out.println("Tipo:");
                    System.out.println("1 - Avulso");
                    System.out.println("2 - Mensalista");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
    
                    System.out.println("CPF (000.000.000-00):");
                    String cpf = scanner.nextLine();
    
                    System.out.println("Nome:");
                    String nome = scanner.nextLine();
    
                    System.out.println("Telefone:");
                    String telefone = scanner.nextLine();
    
                    System.out.println("Endereço:");
                    String endereco = scanner.nextLine();
    
                    Cliente c = new Cliente(nome, cpf, telefone, endereco);
                    sistema_controle.CadastrarCliente(c);
                    
                    System.out.println("\n[SUCESSO] Cliente cadastrado!");
                    System.out.println(c.ficha());
                    break;
            
                case 2:
                    // CADASTRAR SERVIÇO
                    System.out.println("\n--- CADASTRAR SERVIÇO ---");
                    System.out.println("Código do Serviço (número):");
                    int codServ = Integer.parseInt(scanner.nextLine());

                    System.out.println("Descrição do Serviço:");
                    String descServ = scanner.nextLine();

                    System.out.println("Valor por Unidade (R$):");
                    double valorServ = Double.parseDouble(scanner.nextLine());

                    Servico servico = new Servico(codServ, descServ, valorServ);
                    sistema_controle.CadastrarServico(servico);

                    System.out.println("\n[SUCESSO] Serviço cadastrado com sucesso!");
                    break;

                case 3:
                    // REGISTRAR PEDIDO
                    registrarNovoPedido(sistema_controle, scanner);
                    break;
    
                case 4:
                    // CONSULTAR COBRANÇAS
                    System.out.println("\n--- CONSULTAR COBRANÇAS ---");
                    List<Cobranca> cobrancas = sistema_controle.ListaCobranca();
                    
                    if (cobrancas.isEmpty()) {
                        System.out.println("Nenhuma cobrança registrada no sistema.");
                    } else {
                        for (Cobranca cob : cobrancas) {
                            // Se houver método de exibição ou toString na classe Cobranca, ajuste conforme necessário
                            System.out.println("Pedido ID: " + cob.getCodPedido() + 
                                               " | Cliente: " + cob.getCliente().getNome() + 
                                               " | Valor: R$ " + cob.getValorTotal() + 
                                               " | Vencimento: " + cob.getDataVencimento());
                        }
                    }
                    break;
    
                case 5:
                    // REGISTRAR PAGAMENTO DE COBRANÇA
                    System.out.println("\n--- REGISTRAR PAGAMENTO ---");
                    List<Cobranca> listaCobrancasPeding = sistema_controle.ListaCobranca();
                    
                    if (listaCobrancasPeding.isEmpty()) {
                        System.out.println("Não há cobranças para pagar.");
                        break;
                    }

                    System.out.println("Informe o Código do Pedido da cobrança que deseja pagar:");
                    int codPedBusca = Integer.parseInt(scanner.nextLine());

                    Cobranca cobrancaEncontrada = null;
                    for (Cobranca cob : listaCobrancasPeding) {
                        if (cob.getCodPedido() == codPedBusca) {
                            cobrancaEncontrada = cob;
                            break;
                        }
                    }

                    if (cobrancaEncontrada != null) {
                        sistema_controle.PagarCobranca(cobrancaEncontrada);
                        System.out.println("\n[SUCESSO] Pagamento registrado para o pedido " + codPedBusca);
                    } else {
                        System.out.println("\n[ERRO] Cobrança não encontrada para este código de pedido.");
                    }
                    break;
                
                case 6:
                    // LISTAR CLIENTES E PEDIDOS
                    System.out.println("\n--- LISTA DE CLIENTES ---");
                    List<Cliente> clientes = sistema_controle.ListarClientes();
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    } else {
                        for (Cliente cliente : clientes) {
                            System.out.println(cliente.ficha());
                        }
                    }

                    System.out.println("\n--- LISTA DE PEDIDOS ---");
                    List<Pedido> pedidos = sistema_controle.ListarPedidos();
                    if (pedidos.isEmpty()) {
                        System.out.println("Nenhum pedido registrado.");
                    } else {
                        for (Pedido pedido : pedidos) {
                            System.out.println(pedido.ficha());
                        }
                    }
                    break;
    
                case 7:
                    System.out.println("\nEncerrando Sistema...");
                    break;
    
                default:
                    System.out.println("\n[ERRO] Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 7);

        scanner.close();
    }
    
    private static void registrarNovoPedido(Controle controle, Scanner scanner) {
        System.out.println("\n--- REGISTRAR NOVO PEDIDO ---");
        List<Cliente> clientes = controle.ListarClientes();

        if (clientes.isEmpty()) {
            System.out.println("[ERRO] Cadastre pelo menos um cliente antes de registrar um pedido!");
            return;
        }

        System.out.print("CPF do Cliente para o pedido: ");
        String cpfCliente = scanner.nextLine();

        Cliente clienteEncontrado = null;
        for (Cliente c : clientes) {
            if (cpfCliente.equalsIgnoreCase(c.getCpf())) {
                clienteEncontrado = c;
                break;
            }
        }

        if (clienteEncontrado == null) {
            System.out.println("[ERRO] Cliente não encontrado!");
            return;
        }

        LocalDate hojeDate = LocalDate.now();
        Pedido pedido = new Pedido(hojeDate, hojeDate.plusDays(7), clienteEncontrado);
        
        // Garante pelo menos 1 serviço padrão cadastrado para teste fácil se a lista estiver vazia
        if (controle.ListarServicos().isEmpty()) {
            controle.CadastrarServico(new Servico(1, "Lavar e Passar", 25.00));
        }

        List<Servico> servicos = controle.ListarServicos();
        System.out.println("\nServiços Disponíveis:");
        for (Servico s : servicos) {
            System.out.println("ID: " + s.getCodServico() + " | " + s.getDescricao() + " - R$ " + s.getValorUnidade());
        }

        System.out.print("Cod do Serviço desejado: ");
        int codServico = Integer.parseInt(scanner.nextLine());

        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(scanner.nextLine());

        Servico servicoEncontrado = null;
        for (Servico s : servicos) {
            if (s.getCodServico() == codServico) {
                servicoEncontrado = s;
                break;
            }
        }

        if (servicoEncontrado != null) {
            controle.AdicionarServicoAoPedido(pedido, servicoEncontrado, qtd);
            controle.CriarPedido(pedido);

            // Calcula o valor total e gera a cobrança vinculada ao cliente
            double total = pedido.calcularValorTotal();
            controle.GerarCobranca(new Cobranca(pedido.getCodPedido(), clienteEncontrado, total, hojeDate.plusMonths(1)));

            System.out.println("\n[SUCESSO] Pedido e Cobrança no valor de R$ " + total + " gerados com sucesso!");
        } else {
            System.out.println("\n[ERRO] Serviço não encontrado!");
        }
    }
}