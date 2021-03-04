package br.com.gama.bankline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.DTO.LancamentoDTO;
import br.com.gama.bankline.DTO.LancamentoResponseDTO;
import br.com.gama.bankline.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
	@Autowired
	private LancamentoService lancamentoService;
	
	@PostMapping
	public ResponseEntity<LancamentoResponseDTO> realizarLancamento(@RequestBody LancamentoDTO lancamentoDTO) {
		return new ResponseEntity<LancamentoResponseDTO>(lancamentoService.salvarLancamento(lancamentoDTO), HttpStatus.CREATED);
	}
}
