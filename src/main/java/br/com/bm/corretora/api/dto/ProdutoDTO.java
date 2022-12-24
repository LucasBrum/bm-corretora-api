package br.com.bm.corretora.api.dto;

import br.com.bm.corretora.api.entity.Cliente;
import br.com.bm.corretora.api.entity.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO implements Serializable {

	@Serial private static final long serialVersionUID = 1L;

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

	private Cliente cliente;

	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.tipo = produto.getTipo();
		this.seguradora = produto.getSeguradora();
		this.coCorretagem = produto.getCoCorretagem();
		this.dataVigencia = produto.getDataVigencia();
		this.valorPremioLiquido = produto.getValorPremioLiquido();
		this.comissaoVendaPorcentagem = produto.getComissaoVendaPorcentagem();
		this.valorComissaoReceber = produto.getValorComissaoReceber();
		this.agenciamentoPorcentagem = produto.getAgenciamentoPorcentagem();
		this.cliente = produto.getCliente();
	}
}
