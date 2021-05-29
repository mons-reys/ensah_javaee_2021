package com.ensah.core.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensah.core.bo.Etudiant;
import com.ensah.core.bo.Person;
import com.ensah.core.services.IPersonService;
import com.genericdao.common.EntityNotFoundException;


@RestController
public class MyRestController {


	@PostMapping("/helloRest")
	 public String sayHelloEnsah(@RequestBody Etudiant etd){
		
		
		
	     return "Hello " + etd.getNom();
	}

//	@GetMapping("/persons/{idPerson}")
//	public Person getPersonById(@PathVariable int idPerson) {
//
//		
//	}

//	@PostMapping("/persons")
//	public Person addPerson(@RequestBody Person person) {
//
//		// En cas ou un id a été envoyé on le rend égale à null
//		// pour éviter une mise à jour par erreur
//		person.setIdPersonne(null);
//
//		personService.addPerson(person);
//
//		return person;
//	}

	
}
