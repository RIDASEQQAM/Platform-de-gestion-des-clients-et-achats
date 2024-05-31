package ProjetClient.service.Iservice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import ProjetClient.dao.entities.Produit;


public interface IServiceProduit  {
	public void ajouterProduit(Produit p) ;
	public void supprimerPr(Integer id);
	public Produit rechercherPr(Integer id);
	public void modifierPr(Produit pr);
	//public List<Client> listerCl();
	public List<Produit> rechercherprParMotCle(String motCle);
	public Page<Produit> listerPr(int numPage);
	public List<Produit> getAllProduits();
	public Optional<Produit> findProduitById(Integer produitId);
}
