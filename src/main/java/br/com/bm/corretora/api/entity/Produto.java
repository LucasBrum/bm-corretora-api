package br.com.bm.corretora.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tipo;

    private String seguradora;

    private Boolean coCorretagem;

    private LocalDate dataVigencia;

    private BigDecimal valorPremioLiquido;

    private Double comissaoVendaPorcentagem;

    private BigDecimal valorComissaoReceber;

    private Double agenciamentoPorcentagem;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

}