package br.com.gama.bankline.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.gama.bankline.DTO.LoginDTO;
import br.com.gama.bankline.DTO.MensagemResponseDTO;
import br.com.gama.bankline.DTO.NovaSenhaDTO;
import br.com.gama.bankline.DTO.SenhaTemporariaDTO;
import br.com.gama.bankline.DTO.SessaoDTO;
import br.com.gama.bankline.exception.DataBaseException;
import br.com.gama.bankline.exception.TokenExpiradoException;
import br.com.gama.bankline.model.Conta;
import br.com.gama.bankline.model.Usuario;
import br.com.gama.bankline.repository.ContaRepository;
import br.com.gama.bankline.repository.UsuarioRepository;
import br.com.gama.bankline.security.JWTAuthorizationFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginService {
	
	private PasswordEncoder encoder;

	private UsuarioRepository usuarioRepository;

	private ContaRepository contaRepository;

	private JWTService jwtService;
	
	

	public SessaoDTO autenticarUsuario(LoginDTO loginDTO) {

		Optional<Usuario> usuario = usuarioRepository.findByLogin(loginDTO.getLogin());

		if (usuario.isPresent()) {
			
			Usuario usuarioEncontrado = usuario.get();
			
			boolean verificacaoSenha = encoder.matches(loginDTO.getSenha(), usuarioEncontrado.getSenha());
			
			if (verificacaoSenha) {

				Conta conta = contaRepository.findByNumero(usuarioEncontrado.getLogin());
				return retornaSessao(conta, usuarioEncontrado);
			}
		
		}
		
		throw new DataBaseException("Login e/ou senha inválidos");

	}

	private SessaoDTO retornaSessao(Conta conta, Usuario usuario) {

		SessaoDTO sessao = new SessaoDTO();

		sessao.setDataInicio(new Date(System.currentTimeMillis()));
		sessao.setDataFim(new Date(System.currentTimeMillis() + jwtService.retornaDataExpiracaoToken()));
		sessao.setConta(conta);
		sessao.setLogin(usuario.getLogin());
		sessao.setUsuario(usuario);
		sessao.setToken(JWTAuthorizationFilter.PREFIX + TokenGerado(sessao));

		return sessao;

	}

	public SessaoDTO alterarSenha(NovaSenhaDTO novaSenhaDTO) {

		Optional<Usuario> usuario = usuarioRepository.findByLogin(novaSenhaDTO.getLogin());

		if (usuario.isPresent()) {
			
			Usuario usuarioEncontrado = usuario.get();
			
			if (novaSenhaDTO.getSenhaTemporaria().equals(usuarioEncontrado.getSenhaTemporaria())) {
				
				usuarioEncontrado.setSenha(novaSenhaDTO.getSenha());

				Usuario usuarioAlterado = usuarioRepository.save(usuarioEncontrado);

				Conta conta = contaRepository.findByNumero(usuarioEncontrado.getLogin());

				return retornaSessao(conta, usuarioAlterado);
			}

		} 
			throw new DataBaseException("Login e/ou email inválidos");

	}

	public MensagemResponseDTO solicitarSenhaTemporaria(SenhaTemporariaDTO novaSenhaDTO) {

		Optional<Usuario> usuario = usuarioRepository.findByLoginAndEmail(novaSenhaDTO.getLogin(),novaSenhaDTO.getEmail());

		if (usuario.isPresent()) {
			Usuario usuarioEncontrado = usuario.get();
			usuarioEncontrado.setSenhaTemporaria("senhaPadrao");

			Usuario usuarioAlterado = usuarioRepository.save(usuarioEncontrado);

			return criarMensagemResponse(usuarioAlterado.getSenhaTemporaria(), "Senha Temporária: ");
		} else 
			throw new DataBaseException("Login e/ou email inválidos");
	}

	
	private MensagemResponseDTO criarMensagemResponse(String senhaTemporaria, String mensagem) {
		return MensagemResponseDTO.builder().mensagem(mensagem + senhaTemporaria).build();
	}
	
	private String TokenGerado(SessaoDTO sessao) {
		String role = "ROLE_U";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

		String token = Jwts.builder().setSubject(sessao.getLogin())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(sessao.getDataInicio()).setExpiration(sessao.getDataFim())
				.signWith(SignatureAlgorithm.HS512, JWTAuthorizationFilter.SECRET.getBytes()).compact();

		return token;
	}

	/*private boolean validarToken(String token) throws TokenExpiradoException {
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

	}*/

}
