package ProjetClient.presentation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ProjetClient.dao.entities.Client;
import ProjetClient.dao.repositories.IGestionClient;

@Service
public class ClientDetailservice implements UserDetailsService{

	@Autowired
	private IGestionClient ic;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Optional<Client> client = ic.findByEmail(username);
	    if (client.isPresent()) {
	        var userObj = client.get();
	        return User.builder()
	                .username(userObj.getEmail())
	                .password(userObj.getMotDePasse()) 
	                .roles("CLIENT") 
	                .build();
	    } else {
	        throw new UsernameNotFoundException(username);
	    }
	}
	}

