package ProjetClient.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetClient.dao.entities.Client;


public interface IGestionClient extends JpaRepository<Client, Integer> {
	List<Client> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
    Optional<Client> findByEmail(String email);
    

}
