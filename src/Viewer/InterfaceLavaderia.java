
package Viewer;

import controle.*;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Servico;
import modelo.Cobranca;
import modelo.Irregular;
import modelo.Mensalista;

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
            System.out.println("4 - Listar cobranças de um cliente");
            System.out.println("5 - Pagar pendências");
            System.out.println("6 - Listar clientes e pedidos");
            System.out.println("7 - Sair");
            System.out.println("\nEscolha uma opção:");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
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

                    int valorMensalidade = 100;
                    Cliente c;

                    if (tipo == 1) {
                        c = new Irregular(nome, cpf, telefone, endereco);
                    } else {
                        c = new Mensalista(nome, cpf, telefone, endereco,
                                sistema_controle.gproximoCodPlano(), valorMensalidade);
                    }

                    sistema_controle.CadastrarCliente(c);

                    System.out.println("\n[SUCESSO] Cliente cadastrado!");
                    System.out.println(c.ficha());
                    break;

                case 2:
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
                    registrarNovoPedido(sistema_controle, scanner);
                    break;

                case 4:
                    listarCobrancasCliente(sistema_controle, scanner);
                    break;

                case 5:
                    pagarPendencias(sistema_controle, scanner);
                    break;

                case 6:
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
        Pedido pedido = new Pedido(
                hojeDate, hojeDate.plusDays(7), clienteEncontrado.getNome());

        if (controle.ListarServicos().isEmpty()) {
            controle.CadastrarServico(new Servico(1, "Lavar e Passar", 25.00));
        }

        List<Servico> servicos = controle.ListarServicos();

        System.out.println("\nServiços Disponíveis:");

        for (Servico s : servicos) {
            System.out.println("ID: " + s.getCodServico()
                    + " | " + s.getDescricao()
                    + " - R$ " + s.getValorUnidade());
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

        if (servicoEncontrado != null && qtd > 0) {

            controle.AdicionarServicoAoPedido(
                    pedido, servicoEncontrado, qtd);

            controle.CriarPedido(pedido);

            double total = pedido.calcularValorTotal(clienteEncontrado);

            Cobranca cobranca = new Cobranca(
                    pedido.getCodPedido(),
                    clienteEncontrado,
                    total,
                    hojeDate.plusMonths(1));

            // Vincula a cobrança ao pedido.
            cobranca.setCodPedido(pedido.getCodPedido());

            controle.GerarCobranca(cobranca);

            System.out.println("\n[SUCESSO] Pedido e Cobrança no valor de R$ "
                    + total + " gerados com sucesso!");

        } else if (qtd <= 0) {
            System.out.println("\n[ERRO] A quantidade deve ser maior que zero!");
        } else {
            System.out.println("\n[ERRO] Serviço não encontrado!");
        }
    }

    private static void listarCobrancasCliente(Controle controle, Scanner scanner) {

        System.out.println("\n--- COBRANÇAS DE UM CLIENTE ---");
        System.out.print("Informe o CPF do cliente: ");

        String cpf = scanner.nextLine();

        Cliente clienteEncontrado = null;

        for (Cliente cliente : controle.ListarClientes()) {
            if (cliente.getCpf() != null
                    && cliente.getCpf().equalsIgnoreCase(cpf)) {
                clienteEncontrado = cliente;
                break;
            }
        }

        if (clienteEncontrado == null) {
            System.out.println("[ERRO] Cliente não encontrado.");
            return;
        }

        System.out.println("\nCobranças de: " + clienteEncontrado.getNome());

        boolean encontrou = false;

        for (Cobranca cobranca : controle.ListaCobranca()) {

            if (cobranca.getCliente() != null
                    && cobranca.getCliente().getCpf() != null
                    && cobranca.getCliente().getCpf()
                    .equalsIgnoreCase(clienteEncontrado.getCpf())) {

                encontrou = true;

                System.out.println(
                        "Pedido ID: " + cobranca.getCodPedido()
                        + " | Valor: R$ " + cobranca.getValorTotal()
                        + " | Vencimento: " + cobranca.getDataVencimento()
                        + " | Status: " + (cobranca.isPago() ? "Paga" : "Pendente"));
            }
        }

        if (!encontrou) {
            System.out.println("Esse cliente não possui cobranças registradas.");
        }
    }

    private static void pagarPendencias(Controle controle, Scanner scanner) {

        System.out.println("\n--- PAGAR PENDÊNCIAS ---");

        List<Cobranca> cobrancas = controle.ListaCobranca();
        boolean encontrouPendente = false;

        for (Cobranca cobranca : cobrancas) {

            if (!cobranca.isPago()) {

                encontrouPendente = true;

                String nomeCliente = cobranca.getCliente() == null
                        ? "Cliente não informado"
                        : cobranca.getCliente().getNome();

                System.out.println(
                        "Pedido ID: " + cobranca.getCodPedido()
                        + " | Cliente: " + nomeCliente
                        + " | Valor: R$ " + cobranca.getValorTotal()
                        + " | Vencimento: " + cobranca.getDataVencimento());
            }
        }

        if (!encontrouPendente) {
            System.out.println("Não há pendências para pagar.");
            return;
        }

        System.out.print("Informe o código do pedido da pendência que deseja pagar: ");
        int codPedido = Integer.parseInt(scanner.nextLine());

        Cobranca cobrancaEncontrada = null;

        for (Cobranca cobranca : cobrancas) {

            if (cobranca.getCodPedido() == codPedido
                    && !cobranca.isPago()) {

                cobrancaEncontrada = cobranca;
                break;
            }
        }

        if (cobrancaEncontrada != null) {

            controle.PagarCobranca(cobrancaEncontrada);

            System.out.println("\n[SUCESSO] Pendência do pedido "
                    + codPedido + " paga!");

        } else {
            System.out.println("\n[ERRO] Pendência não encontrada ou já paga.");
        }
    }
}
