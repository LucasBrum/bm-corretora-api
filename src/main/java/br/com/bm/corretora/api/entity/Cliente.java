package br.com.bm.corretora.api.entity;

import br.com.bm.corretora.api.dto.ClienteDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 200, nullable = false)
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