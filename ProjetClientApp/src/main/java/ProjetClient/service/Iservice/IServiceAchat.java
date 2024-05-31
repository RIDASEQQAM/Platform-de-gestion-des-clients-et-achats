package ProjetClient.service.Iservice;

import java.util.List;

import org.springframework.data.domain.Page;

import ProjetClient.dao.entities.Achat;



public interface IServiceAchat  {
	public void ajouterAchat(Achat a) ;
	public void supprimerAchat(Integer id);
	public Achat rechercherAchat(Integer id);
	public void modifierAchat(Achat a);
	public List<Achat> rechercherAchatParMotCle(String motCle);
	public Page<Achat> listerAchat(int numPage);
	}
