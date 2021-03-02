package br.com.gama.bankline.exception;

public class TokenExpiradoException extends Exception {
	
private static final long serialVersionUID = 1L;
	
	public TokenExpiradoException(String mensagem) {
		super(mensagem);
	}
	
	public TokenExpiradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
