package br.com.bm.corretora.api.service;

import br.com.bm.corretora.api.dto.UsuarioDTO;
import br.com.bm.corretora.api.entity.Usuario;
import br.com.bm.corretora.api.exception.DataIntegrityViolationException;
import br.com.bm.corretora.api.repository.UsuarioRepository;
import br.com.bm.corretora.api.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTests {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    private UsuarioDTO usuarioDTO;

    private Usuario usuario;

    @BeforeEach
    public void setup() {
        usuarioDTO = UsuarioDTO.builder()
                .cpf("64484299054")
                .email("alexsilva@gmail.com")
                .nome("Alex Silva")
                .telefone("2198574433")
                .senha("123456789")
                .build();

        usuario = new Usuario(usuarioDTO);
    }

    @Test
    @DisplayName("Criar Usuario")
    public void testCreateUsuario() {
        given(usuarioRepository.save(any())).willReturn(usuario);
        given(encoder.encode(anyString())).willReturn("senhaPasswordTest");
        Usuario usuarioCriado = usuarioService.create(usuarioDTO);

        assertThat(usuarioCriado).isNotNull();
        assertThat(usuarioCriado.getEmail()).isEqualTo("alexsilva@gmail.com");

    }

    @Test
    @DisplayName("Tenta Criar Usuario com CPF invalido")
    public void testCreateUsuarioThrowExceptionCpfInvalido() {
        usuario.setId(1L);
        given(usuarioRepository.findById(1L)).willReturn(Optional.ofNullable(usuario));
        given(usuarioRepository.findByCpf(anyString())).willReturn(Optional.ofNullable(usuario));

        Usuario usuarioEncontrado = usuarioService.findById(1L);

        DataIntegrityViolationException dataIntegrityViolationException;
        dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            this.usuarioService.create(usuarioDTO);
        });

       assertThat(dataIntegrityViolationException.getMessage()).isEqualTo("CPF já cadastrado no sistema!");

    }

    @Test
    @DisplayName("Tenta Criar Usuario com E-mail invalido")
    public void testCreateUsuarioThrowExceptionEmailInvalido() {
        usuario.setId(1L);
        given(usuarioRepository.findById(1L)).willReturn(Optional.ofNullable(usuario));
        given(usuarioRepository.findByEmail(anyString())).willReturn(Optional.ofNullable(usuario));

        Usuario usuarioEncontrado = usuarioService.findById(1L);

        DataIntegrityViolationException dataIntegrityViolationException;
        dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            this.usuarioService.create(usuarioDTO);
        });

        assertThat(dataIntegrityViolationException.getMessage()).isEqualTo("E-mail já cadastrado no sistema!");

    }

    @Test
    @DisplayName("Buscar Usuario pelo Id")
    public void testFindUsuarioById() {
        given(usuarioRepository.findById(1L)).willReturn(Optional.ofNullable(usuario));

        Usuario usuarioCriado = usuarioService.findById(1L);

        assertThat(usuarioCriado).isNotNull();
        assertThat(usuarioCriado.getEmail()).isEqualTo("alexsilva@gmail.com");

    }

    @Test
    @DisplayName("Buscar todos os Usuarios")
    public void testFindAllUsuarios() {
        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(usuario);
        given(usuarioRepository.findAll()).willReturn(usuarioList);

        List<Usuario> usuariosEncontrados = usuarioService.findAll();

        assertThat(usuariosEncontrados).isNotNull();
        assertThat(usuariosEncontrados.size()).isEqualTo(1);

    }

    @Test
    @DisplayName("Atualizar Usuário")
    public void testUpdateUsuario() {
        usuario.setId(1L);
        given(usuarioRepository.findById(1L)).willReturn(Optional.ofNullable(usuario));
        Usuario usuarioEncontrado = usuarioService.findById(1L);

        usuarioEncontrado.setNome("Alex Silva 123");

        usuarioDTO = new UsuarioDTO(usuarioEncontrado);

        given(usuarioRepository.save(usuarioEncontrado)).willReturn(usuarioEncontrado);

        Usuario usuarioAtualizado = usuarioService.update(usuarioEncontrado.getId(), usuarioDTO);

        assertThat(usuarioAtualizado).isNotNull();
        assertThat(usuarioAtualizado.getNome()).isEqualTo("Alex Silva 123");
    }

    @Test
    @DisplayName("Deletar Usuário")
    public void testDeleteUsuario() {
        usuario.setId(1L);
        given(usuarioRepository.findById(1L)).willReturn(Optional.ofNullable(usuario));
        Usuario usuarioEncontrado = usuarioService.findById(1L);

        usuarioEncontrado.setNome("Alex Silva 123");

        usuarioDTO = new UsuarioDTO(usuarioEncontrado);

        doNothing().when(usuarioRepository).deleteById(usuarioEncontrado.getId());

        usuarioService.delete(usuarioEncontrado.getId());

       verify(usuarioRepository, times(1)).deleteById(usuarioEncontrado.getId());
    }

}