package com.ProjetoLoginMabrandao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoLoginMabrandao.Entity.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
