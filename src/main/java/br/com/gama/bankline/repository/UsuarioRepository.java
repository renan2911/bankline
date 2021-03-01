package br.com.gama.bankline.repository;

import java.util.List;

import br.com.gama.bankline.model.Usuario;

public interface UsuarioRepository {
	
	public List<Usuario> listar();
	public Usuario salvar(Usuario usuario);
}
