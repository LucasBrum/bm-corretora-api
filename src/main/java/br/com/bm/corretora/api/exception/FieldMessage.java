package br.com.bm.corretora.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable {

	@Serial private static final long serialVersionUID = 6027066942644929750L;

	private String fieldName;
	private String message;

}
