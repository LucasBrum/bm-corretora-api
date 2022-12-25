package br.com.bm.corretora.api.service;

import br.com.bm.corretora.api.dto.ClienteDTO;
import br.com.bm.corretora.api.entity.Cliente;
import br.com.bm.corretora.api.repository.ClienteRepository;
import br.com.bm.corretora.api.service.impl.ClienteServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                .dataNascimento(LocalDate.of(1980, 02, 25))
                .telefone("2199931474")
                .build();

        cliente = new Cliente(clienteDTO);
    }

    @Test
    @DisplayName("Criar Cliente")
    public void testCreateCliente() {
        BDDMockito.given(clienteRepository.save(cliente)).willReturn(cliente);

        Cliente clienteCriado = clienteService.create(clienteDTO);

        Assertions.assertThat(clienteCriado).isNotNull();
        Assertions.assertThat(clienteCriado.getEmail()).isEqualTo("andersonsilva@gmail.com");

    }

    @Test
    @DisplayName("Buscar Cliente pelo Id")
    public void testFindClienteById() {
        BDDMockito.given(clienteRepository.findById(1L)).willReturn(Optional.ofNullable(cliente));

        Cliente clienteCriado = clienteService.findById(1L);

        Assertions.assertThat(clienteCriado).isNotNull();
        Assertions.assertThat(clienteCriado.getEmail()).isEqualTo("andersonsilva@gmail.com");

    }

    @Test
    @DisplayName("Buscar todos os Clientes")
    public void testFindAllClientes() {
        List<Cliente> clienteList = new ArrayList<>();
        clienteList.add(cliente);
        BDDMockito.given(clienteRepository.findAll()).willReturn(clienteList);

        List<Cliente> clientesEncontrados = clienteService.findAll();

        Assertions.assertThat(clientesEncontrados).isNotNull();
        Assertions.assertThat(clientesEncontrados.size()).isEqualTo(1);

    }
}