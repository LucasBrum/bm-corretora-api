package br.com.bm.corretora.api.service;

import br.com.bm.corretora.api.dto.ProdutoDTO;
import br.com.bm.corretora.api.entity.Produto;
import br.com.bm.corretora.api.enums.ProdutoEnum;
import br.com.bm.corretora.api.enums.SeguradoraEnum;
import br.com.bm.corretora.api.repository.ProdutoRepository;
import br.com.bm.corretora.api.service.impl.ClienteServiceImpl;
import br.com.bm.corretora.api.service.impl.ProdutoServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTests {

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ProdutoRepository produtoRepository;

    private ProdutoDTO produtoDTO;

    private Produto produto;

    @BeforeEach
    public void setup() {
        produtoDTO = ProdutoDTO.builder()
                .agenciamentoPorcentagem(2.0)
                .coCorretagem(Boolean.FALSE)
                .comissaoVendaPorcentagem(1.0)
                .dataVigencia(LocalDate.now())
                .seguradora(SeguradoraEnum.PORTO.getNome())
                .tipo(ProdutoEnum.AUTO.getNome())
                .valorComissaoReceber(new BigDecimal(100.0))
                .valorPremioLiquido(new BigDecimal(200.0))
                .build();

        produto = new Produto(produtoDTO);

    }

    @Test
    @DisplayName("Criar Produto")
    public void testCreateProduto() {
        BDDMockito.given(produtoRepository.save(produto)).willReturn(produto);

        Produto produtoCriado = produtoService.create(produtoDTO);

        Assertions.assertThat(produtoCriado).isNotNull();

    }

    @Test
    @DisplayName("Buscar Produto pelo Id")
    public void testFindProdutoById() {
        BDDMockito.given(produtoRepository.findById(1L)).willReturn(Optional.ofNullable(produto));

        Produto produtoCriado = produtoService.findById(1L);

        Assertions.assertThat(produtoCriado).isNotNull();

    }

    @Test
    @DisplayName("Buscar todos os Produtos")
    public void testFindAllProdutos() {
        List<Produto> produtoList = new ArrayList<>();
        produtoList.add(produto);
        BDDMockito.given(produtoRepository.findAll()).willReturn(produtoList);

        List<Produto> produtosEncontrados = produtoService.findAll();

        Assertions.assertThat(produtosEncontrados).isNotNull();
        Assertions.assertThat(produtosEncontrados.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("Atualizar Produto pelo Id")
    public void testUpdate() {
        BDDMockito.given(produtoRepository.findById(1L)).willReturn(Optional.ofNullable(produto));

        produtoDTO.setSeguradora(SeguradoraEnum.ALFA.getNome());

        Produto produtoEncontrado = produtoService.update(1L, produtoDTO);

        Assertions.assertThat(produtoEncontrado).isNotNull();
        Assertions.assertThat(produtoEncontrado.getSeguradora()).isEqualTo(SeguradoraEnum.ALFA.getNome());

    }

}