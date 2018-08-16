package com.api.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.produtos.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{

}
