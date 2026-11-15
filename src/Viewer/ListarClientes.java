package Viewer;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controle.*;
import modelo.Cliente;
public class ListarClientes extends JFrame {

	private Controle lavanderiaControle;
	private JTextArea lista = new JTextArea();
	
	public ListarClientes(Controle controle) {
		lavanderiaControle = controle;
		lista.setEditable(false);
		
		JScrollPane listaScrollPane = new JScrollPane(lista);
		
		List<Cliente> listaClientes = lavanderiaControle.ListarClientes();
		
		StringBuilder listaCompletaBuilder = new StringBuilder();
		for(Cliente zeCliente:listaClientes) {
			listaCompletaBuilder.append(zeCliente.ficha());
		}
		
		lista.setText(listaCompletaBuilder.toString());
		
		lista.setCaretPosition(0);
		
		JPanel principalJPanel = new JPanel();
		principalJPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		principalJPanel.add(listaScrollPane,BorderLayout.CENTER);
		
		
		this.add(principalJPanel);
        this.setTitle("Clientes Cadastrados");
        this.setSize(400, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}
