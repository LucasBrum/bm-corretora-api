package br.com.bm.corretora.api.controller;

import br.com.bm.corretora.api.enums.ProdutoEnum;
import br.com.bm.corretora.api.repository.ProdutoRepository;
import br.com.bm.corretora.api.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/categorias")
    public List<ProdutoEnum> listarCategorias() {

        return Arrays.stream(ProdutoEnum.values()).toList();
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }
}