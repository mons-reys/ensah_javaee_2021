package com.ensah.core.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Cette classe n'est pas pesistante

//Classe qui embale UserAccount et qui hérite de UserDetails 
// pour répondre aux specifications de Spring Security qui travaille avec
//UserDetails (Voir la méthode loadUserbyusername de la classe CustomAuthentificationService)
public class UserPrincipal implements UserDetails {

	//Le compte utilisateur (classe persistante à gérer par ORM)
	private UserAccount user;

	@Override
	public String toString() {
		return "MyUserPrincipal [user=" + user + "]";
	}

	public UserPrincipal(UserAccount user) {
		this.user = user;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	
	//TODO : Cette méthode définie le role de l'utilisateur
	// si vous changer la conception de vos classe User/Role il faut la mettre à jour
	// Les Role doivent être créer sous forme d'une collection de type GrantedAuthority
	// Dans notre conception l'utilisateur a un et un seul role, ainsi il suffit de créer 
	// un objet de type GrantedAuthority avec le role définit par user.getRole().getRoleName()
	// donc enfin la collection des roles de l'utilisateur contient un seul role sous forme 
	// de GrantedAuthority
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> arrayAuths = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getRole().getRoleName());
		arrayAuths.add(auth);
		return arrayAuths;
	}

	
	//TODO : les méthodes suivantes à adapter si vous changer la conception des classes
	//Sinon vous pouvez les laisser telles quelles 
	public String getFirstName() {
		return user.getPerson().getFirstName();
	}

	public String getLastName() {
		return user.getPerson().getLastName();
	}

	public String getEmail() {
		return user.getPerson().getEmail();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

}