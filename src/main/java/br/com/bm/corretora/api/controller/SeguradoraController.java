package br.com.bm.corretora.api.controller;

import br.com.bm.corretora.api.enums.SeguradoraEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/seguradoras")
public class SeguradoraController {

    @GetMapping
    public List<SeguradoraEnum> listarSeguradoras() {

        return Arrays.stream(SeguradoraEnum.values()).toList();
    }
}