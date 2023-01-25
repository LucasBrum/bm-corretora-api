package br.com.bm.corretora.api.enums;

import lombok.Getter;

@Getter
public enum PerfilEnum {
	ADMIN(0, "ROLE_ADMIN"),
	COLABORADOR(1, "ROLE_COLABORADOR");

	private Integer codigo;
	private String descricao;

	private PerfilEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static PerfilEnum toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (PerfilEnum perfilEnum : PerfilEnum.values()) {
			if (codigo.equals(perfilEnum.getCodigo())) {
				return perfilEnum;
			}
		}

		throw new IllegalArgumentException("Perfil inválido.");
	}

}
