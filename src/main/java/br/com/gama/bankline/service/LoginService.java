package br.com.gama.bankline.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.LoginDTO;
import br.com.gama.bankline.DTO.SessaoDTO;
import br.com.gama.bankline.exception.DataBaseException;
import br.com.gama.bankline.exception.LoginException;
import br.com.gama.bankline.exception.TokenExpiradoException;
import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.ContaRepository;
import br.com.gama.bankline.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginService {

	private UsuarioRepository usuarioRepository;

	private ContaRepository contaRepository;

	private JWTService jwtService;

	public SessaoDTO autenticarUsuario(LoginDTO LoginDTO) {

		Usuario usuario = usuarioRepository.findByLogin(LoginDTO.getLogin());

		if (usuario == null) {
			throw new DataBaseException("Usuário e/ou senha inválidos");
		}

		if (LoginDTO.getSenha().equals(usuario.getSenha())) {

			Conta conta = contaRepository.findByNumero(usuario.getLogin());

			return retornaSessaoDto(conta);
		} else {
			throw new LoginException("Login e/ou senha inválidos");
		}

	}

	private SessaoDTO retornaSessaoDto(Conta conta) {

		SessaoDTO sessao = new SessaoDTO();
					
		sessao.setDataInicio(new Date(System.currentTimeMillis()));
		sessao.setDataFim(new Date(System.currentTimeMillis() + jwtService.retornaDataExpiracaoToken()));
		sessao.setConta(conta);
		sessao.setToken(jwtService.gerarToken(conta.getUsuario()));

		return sessao;
	
	}

	public boolean validarToken(String token) throws TokenExpiradoException {
		Claims claims = jwtService.decodificarToken(token);
		if (claims != null) {
			String usuario = claims.getSubject();
			Date dataFim = claims.getExpiration();
			Date dataAtual = new Date(System.currentTimeMillis());

			if (usuario != null && dataFim != null && dataAtual.before(dataFim)) {
				return true;
			} else {
				throw new TokenExpiradoException("Não autorizado");
			}
		}

		return false;

	}

}
