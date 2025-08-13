package ProjetClient.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ProjetClient.dao.entities.Plainte;

public interface IGestionPlaintes extends JpaRepository<Plainte, Integer>{

}
