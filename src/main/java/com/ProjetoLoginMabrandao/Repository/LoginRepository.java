package com.ProjetoLoginMabrandao.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProjetoLoginMabrandao.Entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByUsername(String username);
}
