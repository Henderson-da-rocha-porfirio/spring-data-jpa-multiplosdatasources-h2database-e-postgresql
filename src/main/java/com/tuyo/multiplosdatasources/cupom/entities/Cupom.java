package com.tuyo.multiplosdatasources.cupom.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Cupom {

	@Override
	public String toString() {
		return "Cupom [id=" + id + ", codigo=" + codigo + ", desconto=" + desconto + ", expDate=" + expDate + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private BigDecimal desconto;
	@Column(name = "exp_date")
	private String expDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
}
