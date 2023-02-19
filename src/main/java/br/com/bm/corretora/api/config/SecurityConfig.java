package br.com.bm.corretora.api.config;

import br.com.bm.corretora.api.security.JWTAuthenticationFilter;
import br.com.bm.corretora.api.security.JWTAuthorizationFilter;
import br.com.bm.corretora.api.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

	private final Environment env;
	private final JWTUtil jwtUtil;
	private final UserDetailsService userDetailsService;
	private final AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	public SecurityConfig(Environment env, JWTUtil jwtUtil, UserDetailsService userDetailsService, AuthenticationConfiguration authenticationConfiguration) {
		this.env = env;
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.authenticationConfiguration = authenticationConfiguration;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		http.cors().and().csrf().disable();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(authenticationConfiguration), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(authenticationConfiguration), jwtUtil, userDetailsService));
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers(HttpMethod.POST, "/api/usuarios").permitAll().anyRequest().authenticated();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
