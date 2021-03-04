package br.com.gama.bankline.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{
	
	
	@Bean
	public PasswordEncoder Encoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	private static final String[] SWAGGER_ALL = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
			"/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**" };
	
	@Override
    public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers(SWAGGER_ALL).permitAll()
		.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers(HttpMethod.POST, "/login/alterarSenha").permitAll()
		.antMatchers(HttpMethod.POST, "/login/solicitarSenha").permitAll()
		.anyRequest().authenticated()
       	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().headers().frameOptions().sameOrigin();
	}
	
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	      return source;
	  }
}
