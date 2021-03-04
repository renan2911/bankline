package br.com.gama.bankline.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gama.bankline.DTO.DashBoardDTO;
import br.com.gama.bankline.service.DashBoardService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/dashboard")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DashBoardController {
	private DashBoardService dashboardService;
	
//	@ResponseStatus(HttpStatus.OK)
//	@GetMapping("/{login}")
//	public DashBoardDTO getDashboard(@PathVariable(value = "login") String login) {
//		return dashboardService.listarDashBoard(login);
//	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{login}/{dataInicio}/{dataFim}")
	public DashBoardDTO getDashboardbyDates(@PathVariable(value = "login") String login, @PathVariable(required = false, value = "dataInicio") String dataInicioStr, @PathVariable(required = false, value = "dataFim") String dataFimStr) {
		return dashboardService.listarDashBoardporData(login, dataInicioStr, dataFimStr);
	}
}
