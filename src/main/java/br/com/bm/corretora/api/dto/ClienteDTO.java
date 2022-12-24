package br.com.bm.corretora.api.dto;

import br.com.bm.corretora.api.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	private String telefone;
	private String email;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	//private List<Produto> produtos = new ArrayList<>();

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.dataNascimento = cliente.getDataNascimento();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
		//this.produtos = cliente.getProdutos();
	}
}
