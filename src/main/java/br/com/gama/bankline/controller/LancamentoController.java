package br.com.gama.bankline.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.DTO.LancamentoDTO;
import br.com.gama.bankline.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	public ResponseEntity<List<LancamentoDTO>> getLancamentos(@RequestParam String numeroConta, @RequestParam LocalDate dataInicio,
			@RequestParam LocalDate dataFim) {
		List<LancamentoDTO> collect = lancamentoService.getLancamentosByData(numeroConta, dataFim, dataInicio, dataFim) .stream().map(LancamentoDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok(collect);
	}
}
