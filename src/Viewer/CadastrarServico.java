package Viewer;

import java.awt.GridLayout;
import java.security.PublicKey;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controle.Controle;
import modelo.Cliente;
import modelo.Irregular;
import modelo.Mensalista;
import modelo.Servico;

public class CadastrarServico extends JFrame{
	
    JTextField codServicoField = new JTextField();
    JTextField descricaoJTextField = new JTextField();
    JTextField valorServicoJTextField = new JTextField();
    JButton salvarJButton = new JButton("Cadastrar Serviço");
    JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));
    Controle lavanderiaControle ;

    
    //Construtor
    public CadastrarServico(Controle controle){
   	 lavanderiaControle = controle;
   	 
   	 //exibe para o "dono" cadastrar o novo serviço
   	 painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	     painel.add(new JLabel("Código do serviço:"));
	     painel.add(codServicoField);
	     painel.add(new JLabel("Descrição:"));
	     painel.add(descricaoJTextField);
	     painel.add(new JLabel("Valor do serviço:"));
	     painel.add(valorServicoJTextField);

	     painel.add(new JLabel()); // Espaço vazio
	     painel.add(salvarJButton);
	     
	     salvarJButton.addActionListener(e -> criarCliente());
	     
	     this.add(painel);
	     this.setTitle("Cadastro Serviço");
	     this.setSize(400,350);
	     this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void criarCliente() {
   	 
	
   	 try {
	     int codServico = Integer.parseInt(codServicoField.getText());
	     String descricaoServico = descricaoJTextField.getText();
	     double valorServico = Double.parseDouble(valorServicoJTextField.getText());
	     
	     Servico servico;
	     
	     servico = new Servico(codServico, descricaoServico, valorServico);
	     
		     lavanderiaControle.CadastrarServico(servico);
		     JOptionPane.showMessageDialog(this,cliente.ficha() + "\n Criado com sucesso");
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"erro",JOptionPane.ERROR_MESSAGE);
		}
   	 
	     
    }
}
