package com.api.produtos.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.produtos.model.ProdutoModel;
import com.api.produtos.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produto")
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		ProdutoModel produto = produtoRepository.getOne(id);
		
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		produtoRepository.delete(produto);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> buscar(@PathVariable Long id){
		ProdutoModel produto = produtoRepository.getOne(id);
		
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoModel> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoModel produto){
		ProdutoModel produtoExistente = produtoRepository.getOne(id);
		
		if(produtoExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(produto, produtoExistente, "id");
		produtoExistente = produtoRepository.save(produtoExistente);
		
		return ResponseEntity.ok(produtoExistente);
	}
}
