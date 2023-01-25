package br.com.bm.corretora.api.dto;

import br.com.bm.corretora.api.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {

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

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo Data de Nascimento é obrigatório.")
	private LocalDate dataNascimento;

	//private List<Produto> produtos = new ArrayList<>();

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.dataNascimento = cliente.getDataNascimento();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		//this.produtos = cliente.getProdutos();
	}
}
