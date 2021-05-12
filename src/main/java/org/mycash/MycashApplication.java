package org.mycash;

import org.mycash.service.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MycashApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MycashApplication.class, args);
		
		UsuarioService usuService = context.getBean(UsuarioService.class);
		usuService.registraUsuarioAdmin("admin@mycash.com", "admin");
	}
	
	@GetMapping
	public String hello(@RequestParam("nome") String nome ) {
		return "Olá! " + nome;
	}
	
	

}
