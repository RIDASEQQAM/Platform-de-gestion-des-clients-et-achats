package ProjetClient.service.Impl;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ProjetClient.dao.entities.Client;
import ProjetClient.dao.repositories.IGestionClient;
import ProjetClient.service.Iservice.IServiceClient;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ServiceClientImpl implements IServiceClient{

	//@Autowired
private final IGestionClient cr;
	@Override
	public void ajouterCl(Client c) {
		// TODO Auto-generated method stub
		cr.save(c);
	}

	@Override
	public void supprimerCl(Integer id) {
		// TODO Auto-generated method stub
		Optional<Client> c=cr.findById(id);
		if(c.isEmpty()) {
			throw new RuntimeException("Client not found");	
		}
		else {
			cr.deleteById(id);
		}
	}

	@Override
	public Client rechercherCl(Integer id) {
		// TODO Auto-generated method stub
		Optional<Client> c=cr.findById(id);
		if(c.isEmpty()) {
			throw new RuntimeException("Client not found");	
		}
		else 
		return c.get();
	}

	@Override 
	public void modifierCl(Client cl) {
		cr.save(cl);
	}

	/*@Override
	public List<Client> listerCl() {
		
		return cr.findAll();
	}*/

	@Override
	public List<Client> rechercherParMotCle(String motCle) {
		return cr.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(motCle, motCle);
	
	}

	@Override
	public Page<Client> listerCl(int numPage) {
		// TODO Auto-generated method stub
		return cr.findAll(PageRequest.of(numPage, 2));
		}
	@Override
	public Optional<Client> findByEmail(String email) {
        return cr.findByEmail(email);
    }

	@Override
	public Optional<Client> findById(Integer id) {
		// TODO Auto-generated method stub
		return cr.findById(id);
	}

	
}
