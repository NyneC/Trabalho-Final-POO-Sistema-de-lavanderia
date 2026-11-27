package Viewer;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controle.Controle;
import excecoes.OperacaoLavanderiaException;
import modelo.Servico;

public class CadastrarServico extends JFrame {

    JTextField codServicoField = new JTextField();
    JTextField descricaoJTextField = new JTextField();
    JTextField valorServicoJTextField = new JTextField();
    JButton salvarJButton = new JButton("Cadastrar Serviço");
    JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));

    // Construtor
    public CadastrarServico() {
        // exibe para o "dono" cadastrar o novo serviço
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.add(new JLabel("Código do serviço:"));
        painel.add(codServicoField);
        painel.add(new JLabel("Descrição:"));
        painel.add(descricaoJTextField);
        painel.add(new JLabel("Valor do serviço:"));
        painel.add(valorServicoJTextField);

        painel.add(new JLabel()); // Espaço vazio
        painel.add(salvarJButton);

        salvarJButton.addActionListener(e -> cadastrarServico());

        this.add(painel);
        this.setTitle("Cadastro Serviço");
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void cadastrarServico() {
        try {
            int codServico = Integer.parseInt(codServicoField.getText().trim());
            String descricaoServico = descricaoJTextField.getText().trim();
            double valorServico = Double.parseDouble(valorServicoJTextField.getText().trim().replace(",", "."));

            if (descricaoServico.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Informe a descrição do serviço!", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Servico servico = Controle.getInstance().CadastrarServico(codServico, descricaoServico, valorServico);

            JOptionPane.showMessageDialog(this, servico.ficha() + "\nCriado com sucesso");

            codServicoField.setText("");
            descricaoJTextField.setText("");
            valorServicoJTextField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código e valor do serviço devem ser números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (OperacaoLavanderiaException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}