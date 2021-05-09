package com.ensah.core.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.genericdao.common.EntityNotFoundException;

/**
 * Gestionnaires globales des exceptions
 * 
 * @author T. BOUDAA
 *
 */

@ControllerAdvice
public class GlobalErrorHandler {

	// Gestionnaire d'exceptions spécifique. Il interceptera les exceptions de type
	// EntityNotFoundException
	@ExceptionHandler
	public ResponseEntity<PersonRestError> handleException(EntityNotFoundException ex) {

		// Création de l'erreur sous forme d'un POJO. Jackson en arrière plan s'occupera
		// de traduire le POJO en JSON
		PersonRestError pError = new PersonRestError();
		pError.setDescription("An error occurred while searching by id.");
		pError.setIdError(-1);
		pError.setErrorName("Entity Not Found");
		pError.setHttpStatuts(HttpStatus.NOT_FOUND.value());

		// L'erreur à retourner comme réponse HTTP
		return new ResponseEntity<>(pError, HttpStatus.NOT_FOUND);

	}

	// Gestionnaire d'exceptions générique. Il interceptera les exceptions de type
	// Exception (Toutes les exceptions sauf celles de type Error JVM)
	@ExceptionHandler
	public ResponseEntity<PersonRestError> handleException(Exception ex) {

		// Création de l'erreur sous forme d'un POJO. Jackson en arrière plan s'occupera
		// de traduire le POJO en JSON
		PersonRestError pError = new PersonRestError();
		pError.setDescription("Bad request of the service.");
		pError.setIdError(-1);
		pError.setErrorName("Bad Request");
		pError.setHttpStatuts(HttpStatus.BAD_REQUEST.value());

		// L'erreur à retourner comme réponse HTTP
		return new ResponseEntity<>(pError, HttpStatus.BAD_REQUEST);
	}

}
