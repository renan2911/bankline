package br.com.gama.bankline.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroResponseException {

	private final String mensagem;
    private final int codigo;
    private final String status;
    private final List<ObjectError> erros;
}
