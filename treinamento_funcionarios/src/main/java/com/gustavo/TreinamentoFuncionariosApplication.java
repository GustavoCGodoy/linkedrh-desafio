package com.gustavo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.gustavo.controller.CursoController;

@SpringBootApplication
public class TreinamentoFuncionariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreinamentoFuncionariosApplication.class, args);
	}

}
