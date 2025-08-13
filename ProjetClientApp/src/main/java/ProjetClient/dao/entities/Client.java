package ProjetClient.dao.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idClient;
	@NotNull
	@Size(min = 2, max = 50)
    private String nom;
    
	@NotNull
	@Size(min = 2, max = 50)
    private String prenom;
    private String adresse;
    
    @NotNull
    private String motDePasse;
	@NotNull
	@Email
    private String email;
	
    private String telephone;
	@ManyToOne
    private Admin admin;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Achat> achats;
    
    }
