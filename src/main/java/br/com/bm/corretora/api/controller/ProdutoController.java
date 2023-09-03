package br.com.bm.corretora.api.controller;

import br.com.bm.corretora.api.dto.ProdutoDTO;
import br.com.bm.corretora.api.dto.UsuarioDTO;
import br.com.bm.corretora.api.entity.Produto;
import br.com.bm.corretora.api.enums.ProdutoEnum;
import br.com.bm.corretora.api.model.response.QuantidadeProdutosPorTipoResponse;
import br.com.bm.corretora.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/categorias")
    public List<String> findCategorias() {

        return ProdutoEnum.getNomes();
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<Produto> produtoList = produtoService.findAll();
        List<ProdutoDTO> produtoDTOList = produtoList.stream().map(produto -> new ProdutoDTO(produto)).toList();

        return ResponseEntity.ok().body(produtoDTOList);
    }

    @GetMapping(value = "/cliente/{idCliente}")
    public ResponseEntity<List<ProdutoDTO>> findProdutosByClienteId(@PathVariable Long idCliente) {
        List<Produto> produtoList = produtoService.findProdutosByClienteId(idCliente);
        List<ProdutoDTO> produtoDTOList = produtoList.stream().map(produto -> new ProdutoDTO(produto)).toList();

        return ResponseEntity.ok().body(produtoDTOList);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoService.create(produtoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {

        Produto produto = produtoService.update(id, produtoDTO);

        return ResponseEntity.ok().body(new ProdutoDTO(produto));
    }

    @GetMapping("/tipo/quantidade")
    public ResponseEntity<List<QuantidadeProdutosPorTipoResponse>> listQuantidadeProdutosPorTipo() {
        List<QuantidadeProdutosPorTipoResponse> artistListResponse = this.produtoService.getQuantidadeProdutosPorTipo();

        return new ResponseEntity<>(artistListResponse, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);

        return ResponseEntity.ok().body(new ProdutoDTO(produto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id) {
        produtoService.delete(id);

        return ResponseEntity.noContent().build();
    }

}