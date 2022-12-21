package br.com.bm.corretora.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tipo;

    private String seguradora;

    private Boolean coCorretagem;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVigencia;

    private BigDecimal valorPremioLiquido;

    private Double comissaoVendaPorcentagem;

    private BigDecimal valorComissaoReceber;

    private Double agenciamentoPorcentagem;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}