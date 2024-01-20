package br.com.bm.corretora.api.dto;

import br.com.bm.corretora.api.entity.Usuario;
import br.com.bm.corretora.api.enums.PerfilEnum;
import br.com.bm.corretora.api.util.Messages;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

	@Serial private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "O campo Nome é obrigatório.")
	private String nome;

	@NotNull(message = "O campo CPF é obrigatório.")
	private String cpf;

	@NotNull(message = "O campo Telefone é obrigatório.")
	private String telefone;

	@NotNull(message = "O campo E-mail é obrigatório.")
	private String email;

	@NotNull(message = "O campo Senha é obrigatório.")
	private String senha;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo Data de nascimento é obrigatório.")
	private LocalDate dataNascimento;

	private Set<Integer> perfis = new HashSet<>();

	public UsuarioDTO() {
		super();
		addPerfil(PerfilEnum.COLABORADOR);
	}

	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.telefone = usuario.getTelefone();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.dataNascimento = usuario.getDataNascimento();
		this.perfis = usuario.getPerfis().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
		addPerfil(PerfilEnum.COLABORADOR);

	}

	public Set<PerfilEnum> getPerfis() {
		return perfis.stream().map(p -> PerfilEnum.toEnum(p)).collect(Collectors.toSet());
	}

	public void addPerfil(PerfilEnum perfilEnum) {
		this.perfis.add(perfilEnum.getCodigo());
	}


}
