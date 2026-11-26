package modelo;

import java.time.LocalDate;



public class Cobranca {
	private int codCobranca;
	private double valorTotal;
	private LocalDate dataVencimenDate;
	private LocalDate dataPagamentoDate;
	private boolean pago;
	private int codPedido;
	private String nomeCliente;
	
	//getters
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public int getCodPedido() {
		return codPedido;
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
	public double getValorTotal() {
		return valorTotal;
	}
	
	
	//setters
	public void setCodCobranca(int codCobranca) {
		this.codCobranca = codCobranca;
	}
	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}
	
	public LocalDate getDataVencimento() {
		return dataVencimenDate;
	}
	public LocalDate getDataPagamentoDate() {
		return dataPagamentoDate;
	}
	public void setDataPagamentoDate(LocalDate dataPagamentoDate) {
		this.dataPagamentoDate = dataPagamentoDate;
	}
	public void setDataVencimenDate(LocalDate dataVencimenDate) {
		this.dataVencimenDate = dataVencimenDate;
	}
	public void setValorTotal(double valorTotal) {
		if(valorTotal < 0) {
			throw new IllegalArgumentException("o valor da cobrança não pode ser menor que 0!");
		}
		this.valorTotal = valorTotal;
	}
	public Cobranca(int codCobranca,double valorTotal, LocalDate dataVencimenDate) {
		this.codCobranca = codCobranca;
		setValorTotal(valorTotal);
		this.dataVencimenDate = dataVencimenDate;
	}
}
