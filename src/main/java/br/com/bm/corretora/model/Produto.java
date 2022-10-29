package br.com.bm.corretora.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}