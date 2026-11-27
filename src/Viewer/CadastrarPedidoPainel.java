package Viewer;

import modelo.Cliente;
import modelo.Cobranca;
import modelo.Pedido;
import modelo.Servico;
import controle.Controle;
import excecoes.OperacaoLavanderiaException;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;

public class CadastrarPedidoPainel extends JFrame {

    private JTextField txtCpfCliente;
    private JTextField txtCodServico;
    private JTextField txtQuantidade;
    private JTextArea txtAreaServicos;

    public CadastrarPedidoPainel() {
        

        List<Cliente> clientes = Controle.getInstance().ListarClientes();
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Cadastre pelo menos um cliente antes de registrar um pedido!",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            dispose();
            return;
        }

        setTitle("Registrar Novo Pedido");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Usando o painel principal da janela (getContentPane)
        setLayout(new BorderLayout(10, 10));

        // Painel do Formulário (Centro)
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelFormulario.add(new JLabel("CPF do Cliente:"));
        txtCpfCliente = new JTextField();
        painelFormulario.add(txtCpfCliente);

        painelFormulario.add(new JLabel("Cod. do Serviço:"));
        txtCodServico = new JTextField();
        painelFormulario.add(txtCodServico);

        painelFormulario.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        painelFormulario.add(txtQuantidade);

        JButton btnCadastrar = new JButton("Registrar Pedido");
        painelFormulario.add(new JLabel("")); 
        painelFormulario.add(btnCadastrar);

        add(painelFormulario, BorderLayout.CENTER);

        // Painel inferior para mostrar os serviços disponíveis (Sul)
        JPanel painelServicos = new JPanel(new BorderLayout());
        painelServicos.setBorder(BorderFactory.createTitledBorder("Serviços Disponíveis (Consulte o Código)"));
        
        txtAreaServicos = new JTextArea();
        txtAreaServicos.setEditable(false);
        carregarServicosNaTela();
        
        JScrollPane scrollPane = new JScrollPane(txtAreaServicos);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        painelServicos.add(scrollPane, BorderLayout.CENTER);
        
        add(painelServicos, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(e -> cadastrar());
    }

    private void carregarServicosNaTela() {
        StringBuilder sb = new StringBuilder();
        List<Servico> servicos = Controle.getInstance().ListarServicos();
        for (Servico s : servicos) {
            sb.append("ID: ").append(s.getCodServico())
              .append(" | ").append(s.getDescricao())
              .append(" - R$ ").append(String.format("%.2f", s.getValorUnidade()))
              .append("\n");
        }
        txtAreaServicos.setText(sb.toString());
    }

    private void cadastrar() {
        try {
            String cpf = txtCpfCliente.getText().trim();
            if (cpf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe o CPF do cliente!");
                return;
            }

            Cliente clienteEncontrado = null;
            for (Cliente c : Controle.getInstance().ListarClientes()) {
                if (cpf.equalsIgnoreCase(c.getCpf())) {
                    clienteEncontrado = c;
                    break;
                }
            }

            if (clienteEncontrado == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado com o CPF informado!");
                return;
            }

            int codServico = Integer.parseInt(txtCodServico.getText().trim());
            int qtd = Integer.parseInt(txtQuantidade.getText().trim());

            if (qtd <= 0) {
                JOptionPane.showMessageDialog(this, "A quantidade deve ser maior que zero!");
                return;
            }

            Servico servicoEncontrado = null;
            for (Servico s : Controle.getInstance().ListarServicos()) {
                if (s.getCodServico() == codServico) {
                    servicoEncontrado = s;
                    break;
                }
            }

            if (servicoEncontrado == null) {
                JOptionPane.showMessageDialog(this, "Serviço não encontrado com o código informado!");
                return;
            }

            LocalDate hojeDate = LocalDate.now();
            Pedido pedido = Controle.getInstance().CriarPedido(hojeDate, hojeDate, cpf);
            
            Controle.getInstance().AdicionarServicoAoPedido(pedido, servicoEncontrado, qtd);

            double total = pedido.calcularValorTotal(clienteEncontrado);

            Cobranca cobranca = new Cobranca(
                    pedido.getCodPedido(),
                    total,
                    hojeDate.plusMonths(1)
            );
            cobranca.setCodPedido(pedido.getCodPedido());
            Controle.getInstance().GerarCobranca(cobranca);

            JOptionPane.showMessageDialog(this,
                    "[SUCESSO] Pedido e Cobrança no valor de R$ " + String.format("%.2f", total) + " gerados com sucesso!");

            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O código do serviço e a quantidade devem ser números inteiros válidos!");
        } catch (OperacaoLavanderiaException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar o pedido: " + ex.getMessage());
        }
    }
}