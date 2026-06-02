package com.ProjetoLoginMabrandao.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ProjetoLoginMabrandao.Entity.Produtos;
import com.ProjetoLoginMabrandao.Repository.ProdutosRepository;

@Service
public class ProdutosService {
	final private ProdutosRepository produtosRepository;

	public ProdutosService(ProdutosRepository produtosRepository) {
		this.produtosRepository = produtosRepository;
	}

	public List<Produtos> buscarTodosProdutoss() {
		return produtosRepository.findAll();
	}

	public Produtos buscarProdutossPorId(Long id) {
		Optional<Produtos> hospede = produtosRepository.findById(id);
		return hospede.orElse(null);
	}

	public Produtos salvarProdutoss(Produtos atProdutos) {
		return produtosRepository.save(atProdutos);
	}

	public Produtos alterarProdutos(Long id, Produtos alterarP) {
		Optional<Produtos> existeProdutos = produtosRepository.findById(id);
		if (existeProdutos.isPresent()) {
			Produtos produtos = existeProdutos.get();
			BeanUtils.copyProperties(alterarP, produtos, "id");
			return produtosRepository.save(produtos);
		}
		return null;
	}

	public Boolean apagarProdutos(Long id) {
		Optional<Produtos> exeProdutos = produtosRepository.findById(id);
		if (exeProdutos.isPresent()) {
			produtosRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
