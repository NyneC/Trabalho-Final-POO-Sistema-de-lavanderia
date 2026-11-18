package Viewer;
import controle.*;

import java.awt.GridLayout;

import javax.swing.*;


public class TelaMenu extends JFrame {
	private Controle lavanderiaControle = new Controle();
	
	private JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
    private JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
    private JButton btnCadastrarPedido = new JButton("Cadastrar Pedido");
    private JButton listaClientesButton= new JButton("Lista Clientes");
    private JButton listarPedidosButton= new JButton("Listar Pedidos");
    private JButton btnSair = new JButton("Sair");
    private JButton algoaquiButton = new JButton("algo aqui");
    private JButton maisButtonBotaoButton = new JButton("Mais foibafb");
    
    public TelaMenu() {
        // Espaçamento interno das bordas
        painel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        // Adiciona os botões no painel
        painel.add(btnCadastrarCliente);
        painel.add(btnCadastrarPedido);
        painel.add(listaClientesButton);
        painel.add(listarPedidosButton);
        painel.add(btnSair);
        painel.add(algoaquiButton);
        painel.add(maisButtonBotaoButton);

        // Ouvintes 
        btnCadastrarCliente.addActionListener(e -> abrirCadastroCliente());
        listaClientesButton.addActionListener(e -> abrirListaCLientes());
        btnCadastrarPedido.addActionListener(e -> abrirCadastrarPedido());
        listarPedidosButton.addActionListener(e -> abrirListarPedidos());
        btnSair.addActionListener(e -> System.exit(0));

        // Configurações da janela principal
        this.add(painel);
        this.setTitle("Menu Principal - Lavanderia");
        this.setSize(980,800);
        this.setLocationRelativeTo(null); // Centraliza na tela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        try {
			
        	lavanderiaControle.ControleLavanderia();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),"erro",JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void abrirCadastroCliente() {
        CadastroClientePainel telaCadastro = new CadastroClientePainel(lavanderiaControle);
        telaCadastro.setVisible(true);
    }
    
    private void abrirListaCLientes() {
    	ListarClientes telaListarClientes = new ListarClientes(lavanderiaControle);
    	telaListarClientes.setVisible(true);
    }
    private void abrirCadastrarPedido() {
        CadastrarPedidoPainel telaCadastrar = new CadastrarPedidoPainel(lavanderiaControle);
        telaCadastrar.setVisible(true);
    }
    
    private void abrirListarPedidos() {
    	ListarPedidos telaListarPedidos = new ListarPedidos(lavanderiaControle);
    	telaListarPedidos.setVisible(true);
    }
    

    // Método principal para iniciar o programa
    public static void main(String[] args) {
        TelaMenu menu = new TelaMenu();
        menu.setVisible(true);
    }

}
