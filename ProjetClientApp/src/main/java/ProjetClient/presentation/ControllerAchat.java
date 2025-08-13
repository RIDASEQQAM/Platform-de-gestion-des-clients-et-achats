package ProjetClient.presentation;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ProjetClient.dao.entities.Achat;
import ProjetClient.dao.entities.Client;
import ProjetClient.dao.entities.Produit;
import ProjetClient.service.Iservice.IServiceAchat;
import ProjetClient.service.Iservice.IServiceClient;
import ProjetClient.service.Iservice.IServiceProduit;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@AllArgsConstructor
@Controller
public class ControllerAchat {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAchat.class);

    private final IServiceAchat sa;
    private final IServiceProduit sp;
    private final IServiceClient is;

    @GetMapping("/listerAchat")
    public String getListeAchat(@RequestParam(defaultValue = "0") int numPage, Model model) {
        Page<Achat> listeach = sa.listerAchat(numPage);
        int pageCourante = numPage;
        int totalPage = listeach.getTotalPages();
        model.addAttribute("listeAchats", listeach);
        model.addAttribute("pageCourante", pageCourante);
        model.addAttribute("totalPage", totalPage);
        return "clientvue";
    }

    @GetMapping("/addachat")
    public String getAddAchat(Model model, Principal principal) {
        model.addAttribute("achat", new Achat());
        List<Produit> produits = sp.getAllProduits();
        model.addAttribute("produits", produits);

        // Get the logged-in client
        String email = principal.getName();
        logger.info("Authenticated user email: {}", email); // Log the email
        System.out.println("Authenticated user email: " + email); // Print the email

        Optional<Client> clientOptional = is.findByEmail(email);
        System.out.println("client " + clientOptional); // Print the email

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            model.addAttribute("loggedInClient", client); // Ajoutez loggedInClient au modèle
        } else {
            // Handle client not found
            model.addAttribute("error", "Client not found");
            return "error";
        }

        return "ajouterachat";
    }

    @PostMapping("/insertachat")
    public String insererAchat(@ModelAttribute @Valid Achat a,
                               BindingResult br,
                               Model model,
                               Principal principal,
                               @RequestParam List<String> produits,
                               @RequestParam("idClient") Integer idClient) {
        // Check for validation errors
        if (br.hasErrors()) {
            model.addAttribute("produits", sp.getAllProduits());
            return "ajouterachat";
        } else {
            // Debug log for client ID
            logger.info("Client ID: {}", idClient);

            // Check if client exists
            Optional<Client> clientOptional = is.findById(idClient);
            if (clientOptional.isPresent()) {
                Client client = clientOptional.get();
                logger.info("Client found: {}", client);
                a.setClient(client);

                double montantTotal = 0;
                List<Produit> produitsAchetes = new ArrayList<>();
                for (String produitId : produits) {
                    try {
                        logger.info("Raw product string: {}", produitId);
                        Integer productId = Integer.parseInt(produitId.split("-")[0]);
                        logger.info("Parsed Product ID: {}", productId);

                        // Check if product exists
                        Optional<Produit> produitOptional = sp.findProduitById(productId);
                        if (produitOptional.isPresent()) {
                            Produit produit = produitOptional.get();
                            logger.info("Product found: {}", produit);
                            produitsAchetes.add(produit);
                            montantTotal += produit.getPrix();
                        } else {
                            logger.error("Product with ID {} not found", productId);
                            model.addAttribute("error", "Product not found: " + productId);
                            return "error";
                        }
                    } catch (NumberFormatException e) {
                        logger.error("Invalid product ID format: {}", produitId, e);
                        model.addAttribute("error", "Invalid product ID format: " + produitId);
                        return "error";
                    }
                }
                a.setProduitsAchetes(produitsAchetes);
                a.setMontantTotal(montantTotal);

                // Insert achat and check for success
                try {
                    sa.ajouterAchat(a);
                    logger.info("Achat successfully inserted: {}", a);
                    return "redirect:/listerAchat";
                } catch (Exception e) {
                    logger.error("Error inserting achat: {}", e.getMessage(), e);
                    model.addAttribute("error", "Error inserting achat");
                    return "error";
                }
            } else {
                logger.error("Client with ID {} not found", idClient);
                model.addAttribute("error", "Client not found");
                return "error";
            }
        }
    }




    

    @PostMapping("/deleteachat")
    public String deleteachat(@RequestParam("id") Integer idachat, @RequestParam("mc") String motCle) {
        sa.supprimerAchat(idachat);
        return "redirect:/rechercheachat?mc=" + motCle;
    }

    @GetMapping("/rechercheachat")
    public String rechercheachat(@RequestParam("mc") String motcle, Model model) {
        List<Achat> listeAchats = sa.rechercherAchatParMotCle(motcle);
        model.addAttribute("listeAchats", listeAchats);
        return "clientvue";
    }

    @GetMapping("/modiffierachat")
    public String updateAchat(@RequestParam("id") Integer idachat, @Valid Achat updatedAchat, BindingResult bindingResult, Model model) {
        Achat achat = sa.rechercherAchat(idachat);
        model.addAttribute("achat", achat);
        if (bindingResult.hasErrors()) {
            return "updateachat";
        } else {
            achat.setDateAchat(updatedAchat.getDateAchat());
            achat.setMontantTotal(updatedAchat.getMontantTotal());
            achat.setProduitsAchetes(updatedAchat.getProduitsAchetes());
            sa.modifierAchat(achat);
            return "redirect:/listerAchat";
        }
    }
}
