package br.com.bm.corretora.api.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

	@Serial private static final long serialVersionUID = -6508003506375459820L;

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
