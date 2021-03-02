package br.com.gama.bankline.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.gama.bankline.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	
	@NotNull
	@CPF
	private String cpf;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String login;
	
	@NotNull
	@Size(min = 3, max = 100)
	private String nome;
	
	@NotNull
	@Size(min = 4, max = 10)
	private String senha;
	
	/*
	public UsuarioDTO(Usuario usuario) {
		login = usuario.getLogin();
		senha = usuario.getSenha();
		cpf = usuario.getCpf();
		nome = usuario.getNome();
	}
	
	*/
	
	public Usuario fromModel(UsuarioDTO usuarioDTO) {
		System.out.println(usuarioDTO.getCpf());
		return new Usuario(usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getCpf(), usuarioDTO.getNome());
	}
	
	public static UsuarioDTO fromDTO(Usuario usuario) {
		return new UsuarioDTO(usuario.getLogin(), usuario.getSenha(), usuario.getCpf(), usuario.getNome());
	}
	
}
