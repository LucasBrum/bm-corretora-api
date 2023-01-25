package br.com.bm.corretora.api.entity;

import br.com.bm.corretora.api.dto.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

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

    public Produto(ProdutoDTO produtoDTO) {
        this.id = produtoDTO.getId();
        this.tipo = produtoDTO.getTipo();
        this.seguradora = produtoDTO.getSeguradora();
        this.coCorretagem = produtoDTO.getCoCorretagem();
        this.dataVigencia = produtoDTO.getDataVigencia();
        this.valorPremioLiquido = produtoDTO.getValorPremioLiquido();
        this.comissaoVendaPorcentagem = produtoDTO.getComissaoVendaPorcentagem();
        this.valorComissaoReceber = produtoDTO.getValorComissaoReceber();
        this.agenciamentoPorcentagem = produtoDTO.getAgenciamentoPorcentagem();
    }

}