package modelo;

import java.time.LocalDate;

public class Cobranca {
	private int codCobranca;
	private double valorTotal;
	private LocalDate dataVencimenDate;
	private LocalDate dataPagamentoDate;
	private Cliente cliente;
	private boolean pago;
	private int codPedido;
	
	
	
	public int getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	public LocalDate getDataVencimenDate() {
		return dataVencimenDate;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	public int getCodCobranca() {
		return codCobranca;
	}
	public void setCodCobranca(int codCobranca) {
		this.codCobranca = codCobranca;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDate getDataVencimento() {
		return dataVencimenDate;
	}
	public void setDataVencimenDate(LocalDate dataVencimenDate) {
		this.dataVencimenDate = dataVencimenDate;
	}
	public LocalDate getDataPagamentoDate() {
		return dataPagamentoDate;
	}
	public void setDataPagamentoDate(LocalDate dataPagamentoDate) {
		this.dataPagamentoDate = dataPagamentoDate;
	}
	public Cobranca(int codCobranca, Cliente cliente,double valorTotal, LocalDate dataVencimenDate) {
		this.codCobranca = codCobranca;
		this.cliente = cliente;
		this.valorTotal = valorTotal;
		this.dataVencimenDate = dataVencimenDate;
	}
}
