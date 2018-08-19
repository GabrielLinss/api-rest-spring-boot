package com.api.produtos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.produtos.model.ProdutoModel;
import com.api.produtos.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<ProdutoModel> listaProdutos() {
		Iterable<ProdutoModel> listaProdutos = produtoRepository.findAll();
		
		return listaProdutos;
	}
	
	@PostMapping
	public ProdutoModel salvarProduto(@RequestBody @Valid ProdutoModel produto) {
		return produtoRepository.save(produto);
	}
	
	@DeleteMapping
	public ProdutoModel deletarProduto(@RequestBody ProdutoModel produto) {
		produtoRepository.delete(produto);
		
		return produto;
	}
}
