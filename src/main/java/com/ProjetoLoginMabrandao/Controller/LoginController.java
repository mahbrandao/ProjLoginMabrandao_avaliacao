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

import com.ProjetoLoginMabrandao.Entity.Login;
import com.ProjetoLoginMabrandao.Service.LoginService;

@RestController
@RequestMapping("/users")
public class LoginController {

	@Autowired
	private LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Login> buscarLoginId(@PathVariable Long id) {
		Login login = loginService.buscarLoginsPorId(id);
		if (login != null) {
			return ResponseEntity.ok(login);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Login>> buscarTodosLogin() {
		List<Login> logins = loginService.buscarTodosLogins();
		return ResponseEntity.ok(logins);
	}

	@PostMapping("/")
	public ResponseEntity<Login> salvaLogin(@RequestBody Login logins) {
		Login saveLogins = loginService.salvarLogins(logins);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveLogins);
	}

	@PostMapping("/auth")
	public ResponseEntity<Login> authenticate(@RequestBody Login loginDetails) {
		Login authenticatedUser = loginService.autheticate(loginDetails.getUsername(), loginDetails.getPassword());

		if (authenticatedUser != null) {
			// Retorna 200 OK com os dados do usuário (sem a senha) se a autenticação for
			// bem-sucedida
			authenticatedUser.setPassword(null); // Nunca retorne a senha para o front-end
			return ResponseEntity.ok(authenticatedUser);
		}
		// Retorna 401 Unauthorized se as credenciais estiverem incorretas
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Login> alteraLogins(@PathVariable Long id, @RequestBody Login login) {
		Login atualizaLogin = loginService.alterarLogin(id, login);
		if (atualizaLogin != null) {
			return ResponseEntity.ok(atualizaLogin);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Login> apagaLogin(@PathVariable Long id) {
		boolean apagaLogin = loginService.apagarLogin(id);
		if (apagaLogin) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
