package br.com.bm.corretora.api.dto;

import br.com.bm.corretora.api.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

	@Serial private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	private String cpf;
	private String email;
	private String senha;

	private Set<Integer> perfis = new HashSet<>();
	private LocalDate dataCriacao;

	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.perfis = usuario.getPerfis().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = usuario.getDataCriacao();

	}
}
