package br.com.gama.bankline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.repository.ContaRepository;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired(required=true)
	ContaRepository contaRepository;
	
	@GetMapping
	public List<Conta> lista(){
		return contaRepository.listar();
	}
}
