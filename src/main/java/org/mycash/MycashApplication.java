package org.mycash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MycashApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycashApplication.class, args);
	}
	
	@GetMapping
	public String hello(@RequestParam("nome") String nome ) {
		return "Olá! " + nome;
	}

}
