package ProjetClient.service.Impl;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ProjetClient.dao.entities.Produit;
import ProjetClient.dao.repositories.IGestionProduit;
import ProjetClient.service.Iservice.IServiceProduit;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ServiceProduitImpl implements IServiceProduit{

	//@Autowired
private final IGestionProduit pr;
	@Override
	public void ajouterProduit(Produit p) {
		// TODO Auto-generated method stub
		pr.save(p);
	}

	@Override
	public void supprimerPr(Integer id) {
		// TODO Auto-generated method stub
		Optional<Produit> p=pr.findById(id);
		if(p.isEmpty()) {
			throw new RuntimeException("Produit not found");	
		}
		else {
			pr.deleteById(id);
		}
	}

	@Override
	public Produit rechercherPr(Integer id) {
		Optional<Produit> p=pr.findById(id);
		if(p.isEmpty()) {
			throw new RuntimeException("Produit not found");	
		}
		else 
		return p.get();
	}

	@Override
	public void modifierPr(Produit p) {
		// TODO Auto-generated method stub
		pr.save(p);

		
	}

	@Override
	public List<Produit> rechercherprParMotCle(String motCle) {
		return pr.findByNomContainingIgnoreCaseOrTypeContainingIgnoreCase(motCle,motCle);

	}

	@Override
	public Page<Produit> listerPr(int numPage) {
		// TODO Auto-generated method stub
		return pr.findAll(PageRequest.of(numPage, 2));
	}

	@Override
	public List<Produit> getAllProduits() {
		return pr.findAll();
	}

	@Override
	public Optional<Produit> findProduitById(Integer produitId) {
		Optional<Produit> produit = pr.findById(produitId);
        return Optional.of(produit.orElseThrow(() -> new RuntimeException("Produit not found with id: " + produitId)));
	}
	
	
}
