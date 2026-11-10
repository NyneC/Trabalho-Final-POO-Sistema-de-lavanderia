package modelo;

import java.time.LocalDate;

public class Pedido {
	private int codPedido;
	private LocalDate dataPedidoDate;
	private LocalDate dataEntregaDate;
	private boolean entregue;
	
	
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
	
	//pedido
	public Pedido(int codPedido, LocalDate dataPedidoDate, LocalDate dataEntregaDate, boolean entregue) {
		super();
		this.codPedido = codPedido;
		this.dataPedidoDate = dataPedidoDate;
		this.dataEntregaDate = dataEntregaDate;
		this.entregue = entregue;
	}
	
	//criar o codDoCliente 
	
}
