package com.tuyo.multiplosdatasources.produto.repos;

import com.tuyo.multiplosdatasources.produto.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepo extends JpaRepository<Produto, Long> {

}
