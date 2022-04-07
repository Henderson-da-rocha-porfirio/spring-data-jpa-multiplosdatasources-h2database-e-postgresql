package com.tuyo.multiplosdatasources.produto.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco
				+ ", cupomCodigo=" + cupomCodigo + "]";
	}

	private String nome;
	private String descricao;
	private BigDecimal preco;
	@Transient
	private String cupomCodigo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescription() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getCupomCodigo() {
		return cupomCodigo;
	}

	public void setCupomCodigo(String cupomCodigo) {
		this.cupomCodigo = cupomCodigo;
	}
}
