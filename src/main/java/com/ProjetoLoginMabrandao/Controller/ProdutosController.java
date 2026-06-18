package com.ProjetoLoginMabrandao.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProjetoLoginMabrandao.Entity.Produtos;
import com.ProjetoLoginMabrandao.Service.ProdutosService;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosService produtosService;

	public ProdutosController(ProdutosService produtosService) {
		this.produtosService = produtosService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produtos> buscarProdutosId(@PathVariable Long id) {
		Produtos produtos = produtosService.buscarProdutossPorId(id);
		if (produtos != null) {
			return ResponseEntity.ok(produtos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Produtos>> buscarTodosProdutos() {
	    List<Produtos> produtos = produtosService.buscarTodosProdutoss();
	    return ResponseEntity.ok(produtos);
	}

	@PostMapping
	public ResponseEntity<Produtos> salvaProdutos(@RequestBody Produtos produtos) {
	    Produtos saveProdutos = produtosService.salvarProdutoss(produtos);
	    return ResponseEntity.status(HttpStatus.CREATED).body(saveProdutos);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produtos> alteraProdutoss(@PathVariable Long id, @RequestBody Produtos produtos) {
		Produtos atualizaProdutos = produtosService.alterarProdutos(id, produtos);
		if (atualizaProdutos != null) {
			return ResponseEntity.ok(atualizaProdutos);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Produtos> apagaProdutos(@PathVariable Long id) {
		boolean apagaProdutos = produtosService.apagarProdutos(id);
		if (apagaProdutos) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
