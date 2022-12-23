package br.com.bm.corretora.api.service;

import br.com.bm.corretora.api.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

	Cliente findById(Long id);

	List<Cliente> findAll();
}
