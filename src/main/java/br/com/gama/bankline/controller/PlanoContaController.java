package br.com.gama.bankline.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.DTO.DataResponseDTO;
import br.com.gama.bankline.DTO.MensagemResponseDTO;
import br.com.gama.bankline.DTO.PlanoContaDTO;
import br.com.gama.bankline.service.PlanoContaService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/lancamentos/planos-conta")
public class PlanoContaController {
	
	private PlanoContaService planoContaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DataResponseDTO CriarPlanosConta(@RequestBody @Valid PlanoContaDTO planoContaDTO) {
		return planoContaService.salvarPlanoConta(planoContaDTO);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.FOUND)
	public DataResponseDTO LerPlanosConta(@RequestBody @Valid PlanoContaDTO planoContaDTO) {
		return planoContaService.LerPlanoConta();
	}
	
	

}
