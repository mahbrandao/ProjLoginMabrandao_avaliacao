package com.ProjetoLoginMabrandao.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ProjetoLoginMabrandao.Entity.Login;
import com.ProjetoLoginMabrandao.Repository.LoginRepository;

@Service
public class LoginService {
	final private LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public List<Login> buscarTodosLogins() {
		return loginRepository.findAll();
	}

	public Login buscarLoginsPorId(Long id) {
		Optional<Login> hospede = loginRepository.findById(id);
		return hospede.orElse(null);
	}

	public Login salvarLogins(Login atLogin) {
		return loginRepository.save(atLogin);
	}

	public Login alterarLogin(Long id, Login alterarP) {
		Optional<Login> existeLogin = loginRepository.findById(id);
		if (existeLogin.isPresent()) {
			Login login = existeLogin.get();
			BeanUtils.copyProperties(alterarP, login, "id");
			return loginRepository.save(login);
		}
		return null;
	}

	public Boolean apagarLogin(Long id) {
		Optional<Login> exeLogin = loginRepository.findById(id);
		if (exeLogin.isPresent()) {
			loginRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Login autheticate(String username, String password) {
		Login user = loginRepository.findByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
}
