package br.com.bm.corretora.api.controller;

import br.com.bm.corretora.api.dto.ClienteDTO;
import br.com.bm.corretora.api.repository.ClienteRepository;
import br.com.bm.corretora.api.entity.Cliente;
import br.com.bm.corretora.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> listar(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);

        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }
}