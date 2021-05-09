package com.ensah.core.services;

import java.util.List;

import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensah.core.bo.Person;
import com.ensah.core.bo.Role;
import com.ensah.core.bo.UserAccount;
import com.ensah.core.dao.IPersonDao;
import com.ensah.core.dao.IRoleDao;
import com.ensah.core.dao.IUserDao;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IPersonDao personDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Role> getAllRoles() {
		return roleDao.getAll();
	}



	public List<UserAccount> getAllAccounts() {
		return userDao.getAll();
	}
	public String createUser(Long idRole, Long idPerson) {

		// récupérer la personne de la base de données
		Person person = personDao.findById(idPerson);

		// Créer le compte
		UserAccount userAccount = new UserAccount();

		// determiner la personne
		userAccount.setPerson(person);

		
		userAccount.setLastAccessDate(null);

		// Affecter le role
		userAccount.setRole(roleDao.findById(idRole));

		// génrer le mot de passe aléatoirement
		String generatedPass = generatePassayPassword();


		// hachage du mot de passe + gain de sel
		String encodedPass = passwordEncoder.encode(generatedPass);

		// affecter ce mot de passe
		userAccount.setPassword(encodedPass);

		
		
		//On construit un login de type "nom+prenom " s'il est dispo
		String login = person.getFirstName() + person.getLastName();

		List<UserAccount> accounts = userDao.getEntityByColValue("UserAccount", "username", login);

		if (accounts == null || accounts.size() == 0) {
			
			userAccount.setUsername(login);
			
			//Créer le compte
			userDao.create(userAccount);
			return generatedPass;
		}

		int i = 0;

		// sinon, on cherche un login de type nom+prenom+"_"+ entier
		while (true) {

			login = person.getFirstName() + person.getLastName() + "_" + i;
			accounts = userDao.getEntityByColValue("UserAccount", "username", login);
			if (accounts == null || accounts.size() == 0) {
				userAccount.setUsername(login);
				
				//Créer le compte
				userDao.create(userAccount);
				return generatedPass;
			}

			i++;
		}
	}

	
	//génère le mot de passe. Il se base sur Passay
	public String generatePassayPassword() {
		CharacterRule digits = new CharacterRule(EnglishCharacterData.Digit);

		PasswordGenerator passwordGenerator = new PasswordGenerator();
		String password = passwordGenerator.generatePassword(10, digits);

		return password;
	}

	

}
