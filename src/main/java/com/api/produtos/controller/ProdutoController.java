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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST Produtos")
@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@ApiOperation(value="Retorna uma lista de todos os produtos cadastrados")
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<ProdutoModel> listaProdutos() {
		Iterable<ProdutoModel> listaProdutos = produtoRepository.findAll();
		
		return listaProdutos;
	}
	
	@ApiOperation(value="Salva um produto na base de dados")
	@PostMapping
	public ProdutoModel salvarProduto(@RequestBody @Valid ProdutoModel produto) {
		return produtoRepository.save(produto);
	}
	
	@ApiOperation(value="Deleta um produto pelo id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		ProdutoModel produto = produtoRepository.getOne(id);
		
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		produtoRepository.delete(produto);
		
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Busca um produto pelo id")
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> buscar(@PathVariable Long id){
		ProdutoModel produto = produtoRepository.getOne(id);
		
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(produto);
	}
	
	@ApiOperation(value="Atualiza um produto pelo id")
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
