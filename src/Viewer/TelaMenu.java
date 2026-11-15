package Viewer;
import controle.*;

import java.awt.GridLayout;

import javax.swing.*;


public class TelaMenu extends JFrame {
	private Controle lavanderiaControle = new Controle();
	
	private JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
    private JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
    private JButton btnOutraOpcao = new JButton("Outra Opção");
    private JButton listaClientesButton= new JButton("Lista Clientes");
    private JButton btnSair = new JButton("Sair");
    
    public TelaMenu() {
        // Espaçamento interno das bordas
        painel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Adiciona os botões no painel
        painel.add(btnCadastrarCliente);
        painel.add(btnOutraOpcao);
        painel.add(listaClientesButton);
        painel.add(btnSair);

        // Ouvintes dos botões
        btnCadastrarCliente.addActionListener(e -> abrirCadastroCliente());
        listaClientesButton.addActionListener(e -> abrirListaCLientes());
        btnSair.addActionListener(e -> System.exit(0));

        // Configurações da janela principal
        this.add(painel);
        this.setTitle("Menu Principal - Lavanderia");
        this.setSize(350, 300);
        this.setLocationRelativeTo(null); // Centraliza na tela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha todo o programa ao clicar no X
    }
    
    private void abrirCadastroCliente() {
        CadastroClientePainel telaCadastro = new CadastroClientePainel(lavanderiaControle);
        telaCadastro.setVisible(true);
    }
    
    private void abrirListaCLientes() {
    	ListarClientes telaListarClientes = new ListarClientes(lavanderiaControle);
    	telaListarClientes.setVisible(true);
    }
    

    // Método principal para iniciar o programa
    public static void main(String[] args) {
        TelaMenu menu = new TelaMenu();
        menu.setVisible(true);
    }

}
