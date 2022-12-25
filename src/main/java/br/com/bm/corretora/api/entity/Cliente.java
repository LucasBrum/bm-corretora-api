package br.com.bm.corretora.api.entity;

import br.com.bm.corretora.api.dto.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 400, nullable = false)
    private String nome;

    @Column(length = 13, nullable = false)
    private String telefone;

    @Column(length = 200, nullable = false)
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Produto> produtos = new ArrayList<>();

    public Cliente(ClienteDTO clienteDTO) {
        super();
        this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.dataNascimento = clienteDTO.getDataNascimento();
        this.email = clienteDTO.getEmail();
        this.telefone = clienteDTO.getTelefone();
        //this.produtos = clienteDTO.getProdutos();
    }

}