package br.com.gama.bankline.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.DTO.DataResponseDTO;
import br.com.gama.bankline.DTO.LancamentoDTO;
import br.com.gama.bankline.DTO.LancamentoResponseDTO;
import br.com.gama.bankline.DTO.PlanoContaDTO;
import br.com.gama.bankline.DTO.PlanoContaResponseDTO;
import br.com.gama.bankline.service.LancamentoService;
import br.com.gama.bankline.service.PlanoContaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/lancamentos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LancamentoController {

	
	private LancamentoService lancamentoService;

	private PlanoContaService planoContaService;
	
	
	@GetMapping("/planos-conta/{login}")
	@ResponseStatus(HttpStatus.OK)
	public DataResponseDTO lerPlanosConta(@PathVariable(value = "login") String login) {
		return planoContaService.lerPlanoConta(login);
	}
	

	@PostMapping("/planos-conta")
	public ResponseEntity<PlanoContaResponseDTO> criarPlanosConta(@RequestBody @Valid PlanoContaDTO planoContaDTO) {
		return new ResponseEntity<PlanoContaResponseDTO>(planoContaService.salvarPlanoConta(planoContaDTO),
				HttpStatus.CREATED);
	}

	@PostMapping
	public ResponseEntity<LancamentoResponseDTO> realizarLancamento(@RequestBody @Valid LancamentoDTO lancamentoDTO) {
		return new ResponseEntity<LancamentoResponseDTO>(lancamentoService.salvarLancamento(lancamentoDTO),
				HttpStatus.CREATED);
	}
}
