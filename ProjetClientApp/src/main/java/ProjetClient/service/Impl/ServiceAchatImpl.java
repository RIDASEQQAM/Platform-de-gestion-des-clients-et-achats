package ProjetClient.service.Impl;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ProjetClient.dao.entities.Achat;
import ProjetClient.dao.repositories.IGestionAchat;
import ProjetClient.service.Iservice.IServiceAchat;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ServiceAchatImpl implements IServiceAchat{

	//@Autowired
private final IGestionAchat ac;
	@Override
	public void ajouterAchat(Achat a) {
		// TODO Auto-generated method stub
		ac.save(a);
	}

	@Override
	public void supprimerAchat(Integer id) {
		// TODO Auto-generated method stub
		Optional<Achat> a =ac.findById(id);
		if(a.isEmpty()) {
			throw new RuntimeException("Achat not found");	
		}
		else {
			ac.deleteById(id);
		}
	}

	@Override
	public Achat rechercherAchat(Integer id) {
		// TODO Auto-generated method stub
		Optional<Achat> a=ac.findById(id);
		if(a.isEmpty()) {
			throw new RuntimeException("Client not found");	
		}
		else 
		return a.get();
	}
	

	@Override
	public void modifierAchat(Achat a) {
		// TODO Auto-generated method stub
		ac.save(a);
	}

	@Override
	public List<Achat> rechercherAchatParMotCle(String motCle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Achat> listerAchat(int numPage) {
		// TODO Auto-generated method stub
		return ac.findAll(PageRequest.of(numPage, 2));
	}

}
