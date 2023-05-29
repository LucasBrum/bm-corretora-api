package br.com.bm.corretora.api.enums;

import java.util.List;
import java.util.stream.Stream;

public enum ProdutoEnum {
    AUTO("Auto"),
    AUTO_FROTA("Auto Frota"),
    CARTAO_PORTO("Cartão Porto"),
    CELULAR("Celular"),
    CONDOMINIO("Condomínio"),
    CONSORCIO("Consórcio"),
    DENTAL("Dental"),
    EMPRESARIAL("Empresarial"),
    ENGENHARIA("Engenharia"),
    ODONTO_PME("Odonto PME"),
    RC_ROFISSIONAL("RC Profissional"),
    RESIDENCIAL("Residencial"),
    SAUDE_PME("Saude PME"),
    VIAGEM("Viagem"),
    VIDA("Vida"),
    VIDA_EMPRESARIAL("Vida Empresarial"),
    VIDA_INDIVIDUAL("Vida Individual"),
    VIDA_PME("Vida PME");

    private final String nome;

    public String getNome() {
        return nome;
    }

    ProdutoEnum(String nome) {
        this.nome = nome;
    }

    public static List<String> getNomes() {
        return Stream.of(ProdutoEnum.values()).map(ProdutoEnum::getNome).toList();
    }

}