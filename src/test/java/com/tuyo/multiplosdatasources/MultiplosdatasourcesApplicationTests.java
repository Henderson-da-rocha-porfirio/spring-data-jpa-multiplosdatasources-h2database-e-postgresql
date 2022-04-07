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
	void testSaveCoupon() {
		Cupom coupon = new Cupom();
		coupon.setCodigo("SUPERVENDA");
		coupon.setDesconto(new BigDecimal(20));
		coupon.setExpDate("22/22/2022");
		System.out.println(cupomRepo.save(coupon));
	}

	@Test
	void testSaveProduto() {
		Produto product = new Produto();
		product.setNome("NOTEBOOK DELL");
		product.setDescricao("Its Cool");
		product.setPreco(new BigDecimal(2000));
		product.setCupomCodigo("SUPERVENDA");
		System.out.println(produtoRepo.save(product));
	}

}
