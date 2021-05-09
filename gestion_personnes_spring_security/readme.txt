
************************************
	Création de la base de données
************************************

1- Créer un nouveau schéma nommé : dbTestCours  (s'il existe déjà il faut le re-créer) 
2- Vérifier que votre serveur ne contient aucune autre application
3- Démarrer l'application et vérifier que les tables sont bien crées
4- Arreter l'application 
5- Insréer les données suivantes:

		INSERT INTO `person` (`idPersonne`, `address`, `age`, `email`, `firstName`, `gender`, `lastName`, `nationalIdNumber`, `state`) VALUES
		(1, NULL, 36, 't.bo121@yahoo.fr', 'BOUDAA', 'male', 'Tarik', 'AA12345678', 'Morocco');
		INSERT INTO `role` (`id`, `roleName`) VALUES
		(1, 'ROLE_ADMIN'),
		(2, 'ROLE_USER');
		INSERT INTO `useraccount` (`id`, `accountNonExpired`, `accountNonLocked`, `credentialsNonExpired`, `enabled`, `lastAccessDate`, `password`, `username`, `idPerson`, `role_id`) VALUES
		(1, b'1', b'1', b'1', b'1', NULL, '{bcrypt}$2a$10$vsjRwUSUi2B/jlw4ATXEN.B2IIcVY8ourImFZnWcW1tLz3G6mLfpe', 'admin', 1, 1);


6- Demarrer l'application puis se connecter avec 
Login : admin
pass : admin 

