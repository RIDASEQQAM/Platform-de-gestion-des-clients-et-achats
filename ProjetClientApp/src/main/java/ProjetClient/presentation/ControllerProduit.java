package ProjetClient.presentation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;


import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ProjetClient.dao.entities.Produit;
import ProjetClient.service.Iservice.IServiceProduit;


@AllArgsConstructor
@Controller
public class ControllerProduit {
	
	private final IServiceProduit sp;
	@GetMapping("/listerProduits")
	public String getListeClient(@RequestParam(defaultValue = "0") int numPage, Model model) {
		Page<Produit> listepr = sp.listerPr(numPage);
		int pageCourante=numPage;
		int totalPage=listepr.getTotalPages();
		model.addAttribute("listeProduits", listepr);
		model.addAttribute("pageCourante",pageCourante);
		model.addAttribute("totalPage",totalPage);
		return "produits";
	}
	@GetMapping("/addproduit")
	public String getAddForm(Model model) {
		model.addAttribute("produit",new Produit ());
		return "ajouterproduit";
	}
	@PostMapping("/insertproduit")
	public String insererClient(Model model ,@Valid Produit p, BindingResult br) {
		if(br.hasErrors()){
			
			return "ajouterproduit";
			
		}
		else {
			sp.ajouterProduit(p);
		return "redirect:/listerProduits";
		}
}
	@PostMapping("/deleteproduit")
	public String delete(@RequestParam("id") Integer ProduitId, @RequestParam("mc") String motCle) {
	    sp.supprimerPr(ProduitId);
	    return "redirect:/recherchepr?mc="+motCle;
	}
	@GetMapping("/recherchepr")
	public String recherche(@RequestParam("mc") String motcle, Model model) {
	    List<Produit> listeProduits = sp.rechercherprParMotCle(motcle);
	    model.addAttribute("listeProduits", listeProduits);
	    return "produits";
	}
		
	@GetMapping("/modiffierproduit")
	public String updateClient(@RequestParam("id") Integer produitId, @Valid Produit updatedProduit, BindingResult bindingResult,Model model) {
		Produit produit = sp.rechercherPr(produitId);
		model.addAttribute("produit",produit);
		if (bindingResult.hasErrors()) {
	        return "updateproduit"; 
	    } else {
	        produit.setNom(updatedProduit.getNom());
	        produit.setType(updatedProduit.getType());
	        produit.setPrix(updatedProduit.getPrix());
	        produit.setQuantite(updatedProduit.getQuantite());
	        
	        sp.modifierPr(produit);
	        return "redirect:/listerProduits";
	    }
	}
	@GetMapping("/getproduits")
    public String getProduits(Model model) {
        List<Produit> produits = sp.getAllProduits(); 
        model.addAttribute("produits", produits);
        return "ajouterachat"; 
    }
	}

