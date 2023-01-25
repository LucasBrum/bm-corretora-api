package br.com.bm.corretora.api.enums;

import java.util.stream.Stream;

public enum SeguradoraEnum {
    ALFA("ALFA"),
    ALIRO("ALIRO"),
    ALLIANZ("ALLIANZ"),
    AZUL("AZUL"),
    AZUL_SEGUROS("AZUL SEGUROS"),
    BERKLEY("BERKLEY"),
    BRADESCO("BRADESCO"),
    CANOPUS("CANOPUS"),
    CLUBCARD_ODONTOPREV("CLUBCARD ODONTOPREV"),
    GOLDEN_CROSS("GOLDEN CROSS"),
    HDI("HDI"),
    ICATU("ICATU"),
    INTERMEDICA("INTERMEDICA"),
    LIBERTY("LIBERTY"),
    MAG("MAG"),
    MAPFRE("MAPFRE"),
    METLIFE("METLIFE"),
    PORTO("PORTO"),
    SOMPO("SOMPO"),
    SOMPO_SEGUROS("SOMPO SEGUROS"),
    SUHAI("SUHAI"),
    SULAMERICA("SULAMERICA"),
    TOKIO("TOKIO"),
    UNIMED_RJ("UNIMEDRJ");

   private final String nome;

   public String getNome() {
       return nome;
   }

   SeguradoraEnum(String nome) {
       this.nome = nome;
   }

   public String[] getNomes() {
       return Stream.of(SeguradoraEnum.values()).map(SeguradoraEnum::getNome).toArray(String[]::new);
   }

}
