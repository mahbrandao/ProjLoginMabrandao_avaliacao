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

import com.ProjetoLoginMabrandao.Entity.Estoque;
import com.ProjetoLoginMabrandao.Service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	@Autowired
    private final EstoqueService EstoqueService;

    public EstoqueController(EstoqueService EstoqueService) {
        this.EstoqueService = EstoqueService;
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Estoque> buscarEstoqueId(@PathVariable Long id) {
    	Estoque Estoque = EstoqueService.buscarEstoquePorId(id);
        if (Estoque != null) {
            return ResponseEntity.ok(Estoque);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Estoque>> buscarTodosEstoque() {
        List<Estoque> estoque = EstoqueService.buscarTodosEstoque();
        return ResponseEntity.ok(estoque);
    }

 
    @PostMapping
    public ResponseEntity<Estoque> salvaEstoque(@RequestBody Estoque estoque) {
        Estoque saveEstoque = EstoqueService.salvarEstoque(estoque);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveEstoque);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Estoque> alteraEstoque(@PathVariable Long id, @RequestBody Estoque Estoque) {
        Estoque atualizaEstoque= EstoqueService.atualizarEstoque(id, Estoque);
        if (atualizaEstoque!= null) {
            return ResponseEntity.ok(atualizaEstoque);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Estoque> apagaEstoque(@PathVariable Long id) {
        boolean apagaEstoque= EstoqueService.apagarEstoque(id);
        if (apagaEstoque) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
}