
package Viewer;

import modelo.Pedido;
import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;

import controle.Controle;

public class CadastrarPedidoPainel extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtDataPedido;
    private JTextField txtDataEntrega;
    private JTextField txtNomeDono;

    private Controle lavanderiaControle;

    public CadastrarPedidoPainel(Controle controle) {

        lavanderiaControle = controle;

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));

        painel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        painel.add(new JLabel("Código do pedido:"));
        txtCodigo = new JTextField();
        painel.add(txtCodigo);

        painel.add(new JLabel("Data do pedido (AAAA-MM-DD):"));
        txtDataPedido = new JTextField();
        painel.add(txtDataPedido);

        painel.add(new JLabel("Data da entrega (AAAA-MM-DD):"));
        txtDataEntrega = new JTextField();
        painel.add(txtDataEntrega);

        painel.add(new JLabel("Nome do dono:"));
        txtNomeDono = new JTextField();
        painel.add(txtNomeDono);

        JButton btnCadastrar = new JButton("Cadastrar");

        painel.add(new JLabel(""));
        painel.add(btnCadastrar);

        btnCadastrar.addActionListener(e -> cadastrar());

        setTitle("Cadastrar Pedido");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(painel);
    }

    private void cadastrar() {

        try {

            int codigo = Integer.parseInt(txtCodigo.getText());

            LocalDate dataPedido =
                LocalDate.parse(txtDataPedido.getText());

            LocalDate dataEntrega =
                LocalDate.parse(txtDataEntrega.getText());

            String nome = txtNomeDono.getText();

            if (nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Informe o nome do dono do pedido!");
                return;
            }

            Pedido pedido = new Pedido(
                dataPedido,
                dataEntrega,
                nome
            );

            pedido.setCodPedido(codigo);

            // Aqui futuramente vamos adicionar ao Controle

            JOptionPane.showMessageDialog(this,
                "Pedido cadastrado com sucesso!");

            limparCampos();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                "Verifique os dados informados!\n" +
                "Utilize a data no formato AAAA-MM-DD.");
        }
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtDataPedido.setText("");
        txtDataEntrega.setText("");
        txtNomeDono.setText("");
    }
}
