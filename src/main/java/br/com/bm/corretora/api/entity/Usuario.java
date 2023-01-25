package br.com.bm.corretora.api.entity;

import br.com.bm.corretora.api.dto.UsuarioDTO;
import br.com.bm.corretora.api.enums.PerfilEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Pessoa {

	@Serial private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String senha;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	public Usuario(Long id) {
		super();
		addPerfil(PerfilEnum.COLABORADOR);
	}

	public Usuario(UsuarioDTO usuarioDTO) {
		super();
		this.id = usuarioDTO.getId();
		this.nome = usuarioDTO.getNome();
		this.cpf = usuarioDTO.getCpf();
		this.telefone = usuarioDTO.getTelefone();
		this.email = usuarioDTO.getEmail();
		this.senha = usuarioDTO.getSenha();
		addPerfil(PerfilEnum.COLABORADOR);
	}

	public Set<PerfilEnum> getPerfis() {
		return perfis.stream().map(p -> PerfilEnum.toEnum(p)).collect(Collectors.toSet());
	}

	public void addPerfil(PerfilEnum perfilEnum) {
		this.perfis.add(perfilEnum.getCodigo());
	}
}
