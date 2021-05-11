package org.mycash.web.dto.error;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

public class ApiError {

	private HttpStatus status;
	private LocalDateTime dataHora;
	private String mensagem;
	private List<String> subErros = new ArrayList<>();

	private ApiError() {
        dataHora = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach((fe) -> this.addSubErro(fe));
    }

	private void addSubErro(FieldError fe) {
		this.subErros.add(fe.getDefaultMessage());
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<String> getSubErros() {
		return subErros;
	}

	public void setSubErros(List<String> subErros) {
		this.subErros = subErros;
	}

}
