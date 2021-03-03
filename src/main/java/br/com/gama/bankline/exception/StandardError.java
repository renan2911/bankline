package br.com.gama.bankline.exception;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestap;
	private Integer status;
	private String mensagem;
	private String path;
	
	public StandardError(Integer status, String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}

}
