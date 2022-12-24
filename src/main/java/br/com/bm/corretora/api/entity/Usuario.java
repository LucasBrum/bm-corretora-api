package br.com.bm.corretora.api.entity;

import br.com.bm.corretora.api.dto.UsuarioDTO;
import br.com.bm.corretora.api.enums.PerfilEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

	@Serial private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@CPF
	@Column(unique = true)
	private String cpf;

	@Column(unique = true)
	private String email;

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
