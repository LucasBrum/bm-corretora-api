package br.com.bm.corretora.api.service;

import br.com.bm.corretora.api.dto.ClienteDTO;
import br.com.bm.corretora.api.dto.ProdutoDTO;
import br.com.bm.corretora.api.entity.Cliente;
import br.com.bm.corretora.api.entity.Produto;
import br.com.bm.corretora.api.enums.ProdutoEnum;
import br.com.bm.corretora.api.enums.SeguradoraEnum;
import br.com.bm.corretora.api.exception.DataIntegrityViolationException;
import br.com.bm.corretora.api.repository.ClienteRepository;
import br.com.bm.corretora.api.service.impl.ClienteServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTests {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private ClienteDTO clienteDTO;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        clienteDTO = ClienteDTO.builder()
                .nome("Anderson Silva")
                .email("andersonsilva@gmail.com")
                .cpf("02448370079")
                .dataNascimento(LocalDate.of(1980, 02, 25))
                .telefone("2199931474")
                .build();

        cliente = new Cliente(clienteDTO);
    }

    @Test
    @DisplayName("Criar Cliente")
    public void testCreateCliente() {
        given(clienteRepository.save(cliente)).willReturn(cliente);

        Cliente clienteCriado = clienteService.create(clienteDTO);

        Assertions.assertThat(clienteCriado).isNotNull();
        Assertions.assertThat(clienteCriado.getEmail()).isEqualTo("andersonsilva@gmail.com");

    }

    @Test
    @DisplayName("Tenta Criar Cliente com CPF já cadastrado")
    public void testCreateClienteCPFJaCadastradoException() {
        cliente.setId(1L);
        given(clienteRepository.findByCpf(cliente.getCpf())).willReturn(Optional.ofNullable(cliente));

        DataIntegrityViolationException dataIntegrityViolationException;
        dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            this.clienteService.create(clienteDTO);
        });

        assertThat(dataIntegrityViolationException.getMessage()).isEqualTo("CPF já cadastrado no sistema!");

    }

    @Test
    @DisplayName("Tenta Criar Cliente com Email já cadastrado")
    public void testCreateClienteEmailJaCadastradoException() {
        cliente.setId(1L);
        given(clienteRepository.findByEmail(cliente.getEmail())).willReturn(Optional.ofNullable(cliente));

        DataIntegrityViolationException dataIntegrityViolationException;
        dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            this.clienteService.create(clienteDTO);
        });

        assertThat(dataIntegrityViolationException.getMessage()).isEqualTo("E-mail já cadastrado no sistema!");

    }

    @Test
    @DisplayName("Buscar Cliente pelo Id")
    public void testFindClienteById() {
        given(clienteRepository.findById(1L)).willReturn(Optional.ofNullable(cliente));

        Cliente clienteCriado = clienteService.findById(1L);

        Assertions.assertThat(clienteCriado).isNotNull();
        Assertions.assertThat(clienteCriado.getEmail()).isEqualTo("andersonsilva@gmail.com");

    }

    @Test
    @DisplayName("Buscar todos os Clientes")
    public void testFindAllClientes() {
        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(cliente);
        given(clienteRepository.findAll()).willReturn(clienteList);

        List<Cliente> clientesEncontrados = clienteService.findAll();

        Assertions.assertThat(clientesEncontrados).isNotNull();
        Assertions.assertThat(clientesEncontrados.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("Atualizar Cliente pelo Id")
    public void testUpdateCliente() {
        cliente.setId(1L);
        given(clienteRepository.findById(1L)).willReturn(Optional.ofNullable(cliente));
        given(clienteRepository.save(cliente)).willReturn(cliente);

        Cliente clienteCriado = clienteService.update(1L, clienteDTO);

        Assertions.assertThat(clienteCriado).isNotNull();
        Assertions.assertThat(clienteCriado.getEmail()).isEqualTo("andersonsilva@gmail.com");

    }

    @Test
    @DisplayName("Deletar Cliente pelo Id")
    public void testDeleteCliente() {
        given(clienteRepository.findById(1L)).willReturn(Optional.ofNullable(cliente));

        clienteService.delete(1L);

        verify(clienteRepository, times(1)).deleteById(1L);

    }

    @Test
    @DisplayName("Tenta deletar Cliente com Produtos associados Exception")
    public void testDeleteClienteComProdutosAssociadosException() {
        List<Produto> produtoList = new ArrayList<>();

        Produto produto = Produto.builder()
                .agenciamentoPorcentagem(2.0)
                .coCorretagem(Boolean.FALSE)
                .comissaoVendaPorcentagem(1.0)
                .dataVigencia(LocalDate.now())
                .seguradora(SeguradoraEnum.PORTO.getNome())
                .tipo(ProdutoEnum.AUTO.getNome())
                .valorComissaoReceber(new BigDecimal("100.0"))
                .valorPremioLiquido(new BigDecimal("200.0"))
                .build();

        produtoList.add(produto);
        cliente.setProdutos(produtoList);

        given(clienteRepository.findById(1L)).willReturn(Optional.ofNullable(cliente));


        DataIntegrityViolationException dataIntegrityViolationException;
        dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            clienteService.delete(1L);

        });

        verify(clienteRepository, times(1)).findById(1L);
        assertThat(dataIntegrityViolationException.getMessage()).isEqualTo("O Cliente possui produtos cadastrados.");

    }
}