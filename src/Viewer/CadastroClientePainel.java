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

public class CadastroClientePainel extends JFrame {


     JTextField txtNome = new JTextField();
     JTextField txtCpf = new JTextField();
     JTextField txtTelefone = new JTextField();
     JTextField txtEndereco = new JTextField();
     JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Avulso (Irregular)", "Mensalista"});
     JButton btnSalvar = new JButton("Cadastrar Cliente");
     JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));
     Controle lavanderiaControle ;

     public  CadastroClientePainel(Controle controle){
    	 lavanderiaControle = controle;
    	 
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
	     
	     btnSalvar.addActionListener(e -> criarCliente());
	     
	     this.add(painel);
	     this.setTitle("Cadastro Cliente");
	     this.setSize(400,350);
	     this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     }
     private void criarCliente() {
    	 
	
    	 try {
	     String nome = txtNome.getText();
	     String cpf = txtCpf.getText();
	     String telefone = txtTelefone.getText();
	     String endereco = txtEndereco.getText();
	     Cliente cliente;
	     
	    	 if(cbTipo.getSelectedIndex() == 0) {
		    	 cliente = new Irregular(nome, cpf, telefone, endereco);
		     }else {
		    	 cliente = new Mensalista(nome, cpf, telefone, endereco,lavanderiaControle.gproximoCodPlano(),100);
		     }
		     lavanderiaControle.CadastrarCliente(cliente);
		     JOptionPane.showMessageDialog(this,cliente.ficha() + "\n Criado com sucesso");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"erro",JOptionPane.ERROR_MESSAGE);
		}
	     
     }

}
