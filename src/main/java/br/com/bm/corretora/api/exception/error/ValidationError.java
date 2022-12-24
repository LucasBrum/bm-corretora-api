package br.com.bm.corretora.api.exception.error;

import br.com.bm.corretora.api.exception.FieldMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ValidationError extends StandardError{

	@Serial private static final long serialVersionUID = -3200262177178130328L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
		this.errors = errors;
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
}
