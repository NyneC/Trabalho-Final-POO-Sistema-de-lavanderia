package Viewer;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.*;
import controle.*;
import excecoes.OperacaoLavanderiaException;

public class CadastroClientePainel extends JFrame {


     JTextField txtNome = new JTextField();
     JTextField txtCpf = new JTextField();
     JTextField txtTelefone = new JTextField();
     JTextField txtEndereco = new JTextField();
     JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Avulso (Irregular)", "Mensalista"});
     JButton btnSalvar = new JButton("Cadastrar Cliente");
     JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

     public  CadastroClientePainel(){
    	 
    	 
    	 //exibe para o usuario e pega a entrada
    	 painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	     painel.add(new JLabel("Tipo de Cliente:"));
	     painel.add(cbTipo);
	     painel.add(new JLabel("Nome:"));
	     painel.add(txtNome);
	     painel.add(new JLabel("CPF:"));
	     painel.add(txtCpf);
	     painel.add(new JLabel("Telefone:"));
	     painel.add(txtTelefone);
	     painel.add(new JLabel("Endereço:"));
	     painel.add(txtEndereco);
	     painel.add(new JLabel()); // Espaço vazio
	     painel.add(btnSalvar);
	     
	     //ouvinte
	     btnSalvar.addActionListener(e -> criarCliente());
	     
	     this.add(painel);
	     this.setTitle("Cadastro Cliente");
	     this.setSize(400,350);
	     this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     }
     private void criarCliente() {
    	    try {
    	        String nome = txtNome.getText().trim();
    	        String cpf = txtCpf.getText().trim();
    	        String telefone = txtTelefone.getText().trim();
    	        String endereco = txtEndereco.getText().trim();

    	        if (nome.isEmpty() || cpf.isEmpty()) {
    	            JOptionPane.showMessageDialog(this,
    	                    "Preencha pelo menos Nome e CPF do cliente!",
    	                    "Atenção", JOptionPane.WARNING_MESSAGE);
    	            return;
    	        }

    	        Cliente cliente = Controle.getInstance().CadastrarCliente(
    	                cbTipo.getSelectedIndex(), nome, cpf, telefone, endereco, 100);

    	        JOptionPane.showMessageDialog(this,
    	                cliente.ficha() + "\nCriado com sucesso");

    	        txtNome.setText("");
    	        txtCpf.setText("");
    	        txtTelefone.setText("");
    	        txtEndereco.setText("");
    	    } catch (OperacaoLavanderiaException | IllegalArgumentException e) {
    	        JOptionPane.showMessageDialog(this,
    	                e.getMessage(),
    	                "Erro", JOptionPane.ERROR_MESSAGE);
    	    }
    	}

}
