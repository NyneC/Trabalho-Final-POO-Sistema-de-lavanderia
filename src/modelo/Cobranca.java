package modelo;

import java.time.LocalDate;

public class Cobranca {
	private int codCobranca;
	private int valorTotal;
	private LocalDate dataVencimenDate;
	private LocalDate dataPagamentoDate;
	
	public int getCodCobranca() {
		return codCobranca;
	}
	public void setCodCobranca(int codCobranca) {
		this.codCobranca = codCobranca;
	}
	public int getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDate getDataVencimenDate() {
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
}
