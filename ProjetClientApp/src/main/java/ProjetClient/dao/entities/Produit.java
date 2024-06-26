package ProjetClient.dao.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produit {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdProduit;
	@Size(min = 2, max = 50)
	private String nom;
	@Size(min = 2, max = 50)
	private String type;
	private int quantite;
    private Double prix;
	@ManyToMany
    private List<Achat> achats;
 
}
