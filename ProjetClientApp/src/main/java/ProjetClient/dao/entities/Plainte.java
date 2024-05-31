package ProjetClient.dao.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Plainte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlainte;
    private Date datePlainte;
    private String detail;
    @ManyToOne
    private Achat achat;
}
