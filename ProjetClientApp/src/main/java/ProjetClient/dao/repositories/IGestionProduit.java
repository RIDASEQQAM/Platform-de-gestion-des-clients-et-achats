package ProjetClient.dao.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetClient.dao.entities.Produit;

public interface IGestionProduit extends JpaRepository<Produit, Integer>{
	List<Produit> findByNomContainingIgnoreCaseOrTypeContainingIgnoreCase(String nom, String type);




}
