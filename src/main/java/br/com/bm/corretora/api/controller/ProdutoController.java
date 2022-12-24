package br.com.bm.corretora.api.controller;

import br.com.bm.corretora.api.dto.ProdutoDTO;
import br.com.bm.corretora.api.entity.Produto;
import br.com.bm.corretora.api.enums.ProdutoEnum;
import br.com.bm.corretora.api.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/categorias")
    public List<ProdutoEnum> listarCategorias() {

        return Arrays.stream(ProdutoEnum.values()).toList();
    }


    @GetMapping(value = "{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);

        return ResponseEntity.ok().body(new ProdutoDTO(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<Produto> produtoList = produtoService.findAll();
        List<ProdutoDTO> produtoDTOList = produtoList.stream().map(produto -> new ProdutoDTO(produto)).toList();

        return ResponseEntity.ok().body(produtoDTOList);
    }

    public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoService.create(produtoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }



}