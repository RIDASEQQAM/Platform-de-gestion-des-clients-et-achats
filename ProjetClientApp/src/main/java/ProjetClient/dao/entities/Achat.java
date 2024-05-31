package ProjetClient.dao.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Achat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAchat;
    private Date dateAchat;
    private double montantTotal;
    @ManyToOne
    private Client client;
    @ManyToMany(mappedBy = "achats", fetch = FetchType.LAZY)
    private List<Produit> produitsAchetes;
    @OneToMany(mappedBy = "achat", fetch = FetchType.LAZY)
    private List<Plainte> plaintes;
}
