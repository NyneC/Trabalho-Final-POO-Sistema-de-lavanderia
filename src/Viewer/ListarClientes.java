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

	private JTextArea lista = new JTextArea();
	
	public ListarClientes() {
		
		
		lista.setEditable(false);//faz com q a pessoa n possa editar os campos
		
		JScrollPane listaScrollPane = new JScrollPane(lista);//faz a parte de scroll do mouse 
		
		List<Cliente> listaClientes = Controle.getInstance().ListarClientes();//vetor para salvar os clientes q seão exibidos
		
		StringBuilder listaCompletaBuilder = new StringBuilder();//cria como se fosse um editor de texto para poder juntar as fichas dos clientes
		//para percorrer e add no "ditor de texto"
		for(Cliente zeCliente:listaClientes) {
			listaCompletaBuilder.append(zeCliente.ficha());
		}
		
		lista.setText(listaCompletaBuilder.toString());//envia o testo editado pelo bulder para a lista 
		
		lista.setCaretPosition(0);
		
		JPanel principalJPanel = new JPanel();//painel para poder mostrar lista para as pessoas
		principalJPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));//padrao tudo de listar vai ter isso
		principalJPanel.add(listaScrollPane,BorderLayout.CENTER);//add o lista no painel
		
		
		this.add(principalJPanel);//add painel na janela 
        this.setTitle("Clientes Cadastrados");//nome da janela
        this.setSize(400, 450);
        this.setLocationRelativeTo(null);//para o painel ser centralizado
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//dispo_on_close fecha so a janela atual
		
	}
	
}
