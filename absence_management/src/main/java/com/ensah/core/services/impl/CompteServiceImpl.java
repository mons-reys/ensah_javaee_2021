package com.ensah.core.services.impl;

import java.util.List;

import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Utilisateur;
import com.ensah.core.bo.Role;
import com.ensah.core.bo.Compte;
import com.ensah.core.dao.IUtilisateurDao;
import com.ensah.core.dao.IRoleDao;
import com.ensah.core.dao.ICompteDao;
import com.ensah.core.services.ICompteService;
import com.ensah.core.utils.ExcelExporter;

@Service
@Transactional
public class CompteServiceImpl implements ICompteService {

	@Autowired
	private ICompteDao userDao;

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IUtilisateurDao personDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Role> getAllRoles() {
		return roleDao.getAll();
	}



	public List<Compte> getAllAccounts() {
		return userDao.getAll();
	}
	public String createUser(Long idRole, Long idPerson) {

		// récupérer la personne de la base de données
		Utilisateur person = personDao.findById(idPerson);

		// Créer le compte
		Compte userAccount = new Compte();

		// determiner la personne
		userAccount.setProprietaire(person);

		

		// Affecter le role
		userAccount.setRole(roleDao.findById(idRole));

		// génrer le mot de passe aléatoirement
		String generatedPass = generatePassayPassword();


		// hachage du mot de passe + gain de sel
		String encodedPass = passwordEncoder.encode(generatedPass);
		

		// affecter ce mot de passe
		userAccount.setPassword(encodedPass);

		
		
		//On construit un login de type "nom+prenom " s'il est dispo
		String login = person.getNom() + person.getPrenom();

		List<Compte> accounts = userDao.getEntityByColValue("Compte", "login", login);

		if (accounts == null || accounts.size() == 0) {
			
			userAccount.setLogin(login);
			
			//Créer le compte
			userDao.create(userAccount);
			return generatedPass;
		}

		int i = 0;

		// sinon, on cherche un login de type nom+prenom+"_"+ entier
		while (true) {

			login = person.getNom() + person.getPrenom() + "_" + i;
			accounts = userDao.getEntityByColValue("Compte", "login", login);
			if (accounts == null || accounts.size() == 0) {
				userAccount.setLogin(login);
				
				//Créer le compte
				userDao.create(userAccount);
				return generatedPass;
			}

			i++;
		}
	}

	
	public ExcelExporter prepareCompteExport(List<Compte> comptes) {
		String[] columnNames = new String[] { "Login", "Rôle", "Nom & Prénom" };
		String[][] data = new String[comptes.size()][3];

		int i = 0;
		for (Compte u : comptes) {
			data[i][0] = u.getLogin();
			data[i][1] = u.getRole().getNomRole();
			data[i][2] = u.getProprietaire().getNom() +" "+ u.getProprietaire().getPrenom();
			i++;
		}

		return new ExcelExporter(columnNames, data, "comptes");

	}
	
	//génère le mot de passe. Il se base sur Passay
	public String generatePassayPassword() {
		CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);

		PasswordGenerator passwordGenerator = new PasswordGenerator();
		String password = passwordGenerator.generatePassword(10, digits);

		return password;
	}

	

}
