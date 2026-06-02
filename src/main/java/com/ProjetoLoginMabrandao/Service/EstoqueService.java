package com.ProjetoLoginMabrandao.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ProjetoLoginMabrandao.Entity.Estoque;
import com.ProjetoLoginMabrandao.Repository.EstoqueRepository;

@Service
public class EstoqueService {

	final private EstoqueRepository estoqueRepository;

	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}

	public List<Estoque> buscarTodosEstoques() {
		return estoqueRepository.findAll();
	}

	public Estoque buscarEstoquesPorId(Long id) {
		Optional<Estoque> hospede = estoqueRepository.findById(id);
		return hospede.orElse(null);
	}

	public Estoque salvarEstoques(Estoque atEstoque) {
		return estoqueRepository.save(atEstoque);
	}

	public Estoque alterarEstoque(Long id, Estoque alterarP) {
		Optional<Estoque> existeEstoque = estoqueRepository.findById(id);
		if (existeEstoque.isPresent()) {
			Estoque estoque = existeEstoque.get();
			BeanUtils.copyProperties(alterarP, estoque, "id");
			return estoqueRepository.save(estoque);
		}
		return null;
	}

	public Boolean apagarEstoque(Long id) {
		Optional<Estoque> exeEstoque = estoqueRepository.findById(id);
		if (exeEstoque.isPresent()) {
			estoqueRepository.deleteById(id);
			return true;
		}
		return false;
	}
}

