
package Viewer;

import modelo.Cliente;
import modelo.Pedido;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controle.Controle;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListarPedidos extends JFrame {

	private JTextArea lista = new JTextArea();

    public ListarPedidos() {

    	
    	lista.setEditable(false);//faz com q a pessoa n possa editar os campos
		
		JScrollPane listaScrollPane = new JScrollPane(lista);//faz a parte de scroll do mouse 
		
		List<Pedido> listaPedido = Controle.getInstance().ListarPedidos();//vetor para salvar os clientes q seão exibidos
		
		StringBuilder listaCompletaBuilder = new StringBuilder();//cria como se fosse um editor de texto para poder juntar as fichas dos clientes
		//para percorrer e add no "ditor de texto"
		for(Pedido pedido:listaPedido) {
			listaCompletaBuilder.append(pedido.ficha());
		}
		
		lista.setText(listaCompletaBuilder.toString());//envia o testo editado pelo bulder para a lista 
		
		lista.setCaretPosition(0);
		
		JPanel principalJPanel = new JPanel();//painel para poder mostrar lista para as pessoas
		principalJPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));//padrao tudo de listar vai ter isso
		principalJPanel.add(listaScrollPane,BorderLayout.CENTER);//add o lista no painel
		
		
		this.add(principalJPanel);//add painel na janela 
        this.setTitle("Lista de pedidos");//nome da janela
        this.setSize(400, 450);
        this.setLocationRelativeTo(null);//para o painel ser centralizado
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//dispo_on_close fecha so a janela atual
		
	}

}
