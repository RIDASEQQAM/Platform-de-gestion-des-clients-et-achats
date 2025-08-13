package ProjetClient.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetClient.dao.entities.Achat;

public interface IGestionAchat extends JpaRepository<Achat, Integer>{

}
