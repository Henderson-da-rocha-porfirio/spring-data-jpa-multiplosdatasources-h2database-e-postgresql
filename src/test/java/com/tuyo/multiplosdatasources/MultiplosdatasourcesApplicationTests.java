package com.tuyo.multiplosdatasources;

import com.tuyo.multiplosdatasources.cupom.entities.Cupom;
import com.tuyo.multiplosdatasources.cupom.repos.CupomRepo;
import com.tuyo.multiplosdatasources.produto.entities.Produto;
import com.tuyo.multiplosdatasources.produto.repos.ProdutoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MultiplosdatasourcesApplicationTests {

	@Autowired
	CupomRepo cupomRepo;

	@Autowired
	ProdutoRepo produtoRepo;

	@Test
	void testSaveCupom() {
		Cupom coupon = new Cupom();
		coupon.setCodigo("SUPERVENDASS");
		coupon.setDesconto(new BigDecimal(20));
		coupon.setExpDate("02/22/2022"); 					// não importa a ordem ou a existência. Será gerado automaticamente.
		System.out.println(cupomRepo.save(coupon));
	}

	@Test
	void testSaveProduto() {
		Produto produto = new Produto();
		produto.setNome("NOTEBOOK DELL");
		produto.setDescricao("Its Cool");
		produto.setPreco(new BigDecimal(2000));
		produto.setCupomCodigo("SUPERVENDA");
		System.out.println(produtoRepo.save(produto));
	}

}
