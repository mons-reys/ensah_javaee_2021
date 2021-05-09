package com.ensah.core.web.rest;

/**
 * Erreur HTTP sous forme d'un POJO
 * 
 * @author Tarik BOUDAA
 *
 */
public class PersonRestError {

	private int idError;
	private int httpStatuts;
	private String errorName;
	private String description;

	public int getIdError() {
		return idError;
	}

	public void setIdError(int idError) {
		this.idError = idError;
	}

	public int getHttpStatuts() {
		return httpStatuts;
	}

	public void setHttpStatuts(int httpStatuts) {
		this.httpStatuts = httpStatuts;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
