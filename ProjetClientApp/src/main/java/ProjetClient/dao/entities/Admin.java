package ProjetClient.dao.entities;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Admin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAdmin;
    private String nom;
    private String prenom; 
    private String email;
    private String motDePasse;
    private String roleE;
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<Client> client; 
}
