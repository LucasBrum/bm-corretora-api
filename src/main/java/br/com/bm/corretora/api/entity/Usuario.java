package br.com.bm.corretora.api.entity;

import br.com.bm.corretora.api.enums.PerfilEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Usuario {

	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private Set<Integer> perfis = new HashSet<>();
	private LocalDate dataCriacao = LocalDate.now();

	public Usuario(Long id) {
		super();
		addPerfil(PerfilEnum.COLABORADOR);
	}

	public Usuario(Long id, String nome, String cpf, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
	}

	public Set<PerfilEnum> getPerfis() {
		return perfis.stream().map(p -> PerfilEnum.toEnum(p)).collect(Collectors.toSet());
	}

	public void addPerfil(PerfilEnum perfilEnum) {
		this.perfis.add(perfilEnum.getCodigo());
	}
}
