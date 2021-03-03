package br.com.gama.bankline.DTO;

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
	
	@CPF
	private String cpf;
	
	@Size(min = 2, max = 20, message="O login deve até " + "{max} caracteres.")
	private String login;
	
	@Size(min = 3, max = 100, message = "Nome é obrigatório, deve ter entre {min} e " + "{max} caracteres.")
	private String nome;
	
	@Size(min = 3, max = 100, message = "Email é obrigatório.")
	private String email;
	
	@Size(min=4,max=20,message="A senha é obrigatória, deve ter entre {min} e " + "{max} caracteres.")
	private String senha;
	
	
	public Usuario fromModel(UsuarioDTO usuarioDTO) {
		return new Usuario(usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getCpf(), usuarioDTO.getNome(), usuarioDTO.getEmail());
	}
	
	public static UsuarioDTO fromDTO(Usuario usuario) {
		return new UsuarioDTO(usuario.getLogin(), usuario.getSenha(), usuario.getCpf(), usuario.getNome(), usuario.getEmail());
	}
	
}
