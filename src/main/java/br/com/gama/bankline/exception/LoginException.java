package br.com.gama.bankline.exception;

public class LoginException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public LoginException(String mensagem) {
		super(mensagem);
	}
	
	public LoginException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
