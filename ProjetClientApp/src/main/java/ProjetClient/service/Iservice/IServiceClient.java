package ProjetClient.service.Iservice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import ProjetClient.dao.entities.Client;


public interface IServiceClient  {
	public void ajouterCl(Client c) ;
	public void supprimerCl(Integer id);
	public Client rechercherCl(Integer id);
	public void modifierCl(Client cl);
	//public List<Client> listerCl();
	public List<Client> rechercherParMotCle(String motCle);
	public Page<Client> listerCl(int numPage);
	public Optional<Client> findByEmail(String email);
	public Optional<Client> findById(Integer id);

}
