package modelo;
import java.time.LocalDate;
import java.util.*;

public class Pedido {
	private int codPedido;
	private LocalDate dataPedidoDate;
	private LocalDate dataEntregaDate;
	private boolean entregue;
	private String nomeDonoPedido;
	
	public String getNomeDonoPedido() {
		return nomeDonoPedido;
	}

	public void setNomeDonoPedido(String nomeDonoPedido) {
		this.nomeDonoPedido = nomeDonoPedido;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	private List<ItemPedido> itens;
	
	
	
	public List<ItemPedido> getItens() {
		return itens;
	}
	
	public void setItens(ItemPedido item) {
		this.itens.add(item);
	}
	
	//COdgo Pedido
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	
	//Data pedido
	public LocalDate getDataPedidoDate() {
		return dataPedidoDate;
	}
	public void setDataPedidoDate(LocalDate dataPedidoDate) {
		this.dataPedidoDate = dataPedidoDate;
	}
	
	//data Entregue
	public LocalDate getDataEntregaDate() {
		return dataEntregaDate;
	}
	public void setDataEntregaDate(LocalDate dataEntregaDate) {
		this.dataEntregaDate = dataEntregaDate;
	}
	
	//Entregue
	public boolean isEntregue() {
		return entregue;
	}
	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}
	

	
	
	public Pedido(LocalDate dataPedidoDate, LocalDate dataEntregaDate, String nomeDonoPedido) {
	    setDataPedidoDate(dataPedidoDate);
	    setDataEntregaDate(dataEntregaDate);
	    this.entregue = false;
	    setNomeDonoPedido(nomeDonoPedido);
	    this.itens = new ArrayList<ItemPedido>();
	}

	//guarda os itens do pedido
	public void addItem(ItemPedido item) {
		this.setItens(item);
	}

	//ficha dos pedidos
	public String ficha(){
		return "Código do pedido: " + getCodPedido() + "Data do pedido: " + getDataPedidoDate() + "Data da entrega: " + getDataEntregaDate()
				+ "Dono do pedido: " + getNomeDonoPedido() + "Itens: " + getItens();
	}
	
	// Percorre todos os itens da lista e soma os subtotais.
    public double calcularValorTotal(Cliente zeCliente) {
        double total = 0.0;
        // 'for (ItemPedido item : itens)': Loop "for-each". 
        // Equivale em C a: for(int i=0; i < total_itens; i++) { ItemPedido item = itens[i]; ... }
        for (ItemPedido item : itens) {
            total += item.getValorTotal() - (item.getValorTotal()*zeCliente.Desconto());
        }
        return total;
    }
}
