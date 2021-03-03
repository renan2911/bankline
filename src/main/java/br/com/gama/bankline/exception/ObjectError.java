package br.com.gama.bankline.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ObjectError {
	
	private final String mensagem;
    private final String campo;
    private final Object parametro;
}
