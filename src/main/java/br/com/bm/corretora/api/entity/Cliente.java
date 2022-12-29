package br.com.bm.corretora.api.entity;

import br.com.bm.corretora.api.dto.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
public class Cliente extends Pessoa {

	@Serial private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Produto> produtos = new ArrayList<>();

	public Cliente(Long id, List<Produto> produtos) {
		this.id = id;
		this.produtos = produtos;
	}

	public Cliente(Long id, String nome, String cpf, String email, String senha, Long id1, List<Produto> produtos) {
		super(id, nome, cpf, email, senha);
	}

	public Cliente(ClienteDTO clienteDTO) {
		super();
		this.id = clienteDTO.getId();
		this.nome = clienteDTO.getNome();
		this.dataNascimento = clienteDTO.getDataNascimento();
		this.email = clienteDTO.getEmail();
		this.telefone = clienteDTO.getTelefone();
	}

}