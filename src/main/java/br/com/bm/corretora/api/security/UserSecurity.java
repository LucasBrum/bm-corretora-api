package br.com.bm.corretora.api.security;

import br.com.bm.corretora.api.enums.PerfilEnum;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class UserSecurity implements UserDetails {

	@Serial
	private static final long serialVersionUID = -39450817347996990L;

	private Long id;

	private String email;
	private String senha;

	private Collection<? extends GrantedAuthority> authorities;

	public UserSecurity(Long id, String email, String senha, Set<PerfilEnum> perfilEnums) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfilEnums.stream().map(p -> new SimpleGrantedAuthority(p.getDescricao())).collect(Collectors.toSet());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
