package br.com.gama.bankline.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.gama.bankline.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {
		
		private static String secret = "SequenciaCriptografiaToken";
		
		private static Long expiration = 1800000L;
		
		public Long retornaDataExpiracaoToken() {
			return expiration;
		}
		
		
		public String gerarToken(Usuario usuario) {
			return Jwts.builder().setSubject(usuario.getLogin()).setExpiration(new Date(System.currentTimeMillis() + expiration))
					.signWith(SignatureAlgorithm.HS512, secret.getBytes())
					.compact();
		}
		
		public Claims decodificarToken(String token) {

			try {
				return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
}
