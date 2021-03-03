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
import br.com.gama.bankline.DTO.PlanoContaDTO;
import br.com.gama.bankline.service.PlanoContaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/lancamentos/planos-conta")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PlanoContaController {

	private PlanoContaService planoContaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PlanoContaDTO> CriarPlanosConta(@RequestBody @Valid PlanoContaDTO planoContaDTO) {
		return ResponseEntity.ok().body(planoContaService.salvarPlanoConta(planoContaDTO));
	}

	@GetMapping("/{login}")
	@ResponseStatus(HttpStatus.OK)
	public DataResponseDTO LerPlanosConta(@PathVariable(value="login") String login) {
		return planoContaService.lerPlanoConta(login);
	}

}
