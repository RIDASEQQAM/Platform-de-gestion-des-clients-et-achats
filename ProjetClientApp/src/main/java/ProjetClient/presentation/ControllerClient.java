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

import ProjetClient.dao.entities.Client;
import ProjetClient.service.Iservice.IServiceClient;


@AllArgsConstructor
@Controller
public class ControllerClient {
	
	private final IServiceClient sc;
	@GetMapping("/listerClient")
	public String getListeClient(@RequestParam(defaultValue = "0") int numPage, Model model) {
		Page<Client> listecl = sc.listerCl(numPage);
		int pageCourante=numPage;
		int totalPage=listecl.getTotalPages();
		model.addAttribute("listeClients", listecl);
		model.addAttribute("pageCourante",pageCourante);
		model.addAttribute("totalPage",totalPage);
		return "index";
	}
	@GetMapping("/clientvue")
	public String getcvue(Model model) {
	
		return "clientvue";
	}
	@GetMapping("/addform")
	public String getAddForm(Model model) {
		model.addAttribute("client",new Client ());
		return "formajouter";
	}
	@PostMapping("/insertclient")
	public String insererClient(Model model ,@Valid Client c, BindingResult br) {
		if(br.hasErrors()){
			
			return "formajouter";
			
		}
		else {
		sc.ajouterCl(c);
		return "redirect:/listerClient";
		}
}
	@PostMapping("/deleteclient")
	public String delete(@RequestParam("id") Integer clientId, @RequestParam("mc") String motCle) {
	    sc.supprimerCl(clientId);
	    return "redirect:/recherche?mc="+motCle;
	}
	@GetMapping("/recherche")
	public String recherche(@RequestParam("mc") String motcle, Model model) {
	    List<Client> listeClients = sc.rechercherParMotCle(motcle);
	    model.addAttribute("listeClients", listeClients);
	    return "index";
	}
		
	@GetMapping("/updateclient")
	public String updateClient(@RequestParam("id") Integer clientId, @Valid Client updatedClient, BindingResult bindingResult,Model model) {
		Client client = sc.rechercherCl(clientId);
		model.addAttribute("client",client);
		if (bindingResult.hasErrors()) {
	        return "updateform"; 
	    } else {
	        client.setNom(updatedClient.getNom());
	        client.setPrenom(updatedClient.getPrenom());
	        client.setAdresse(updatedClient.getAdresse());
	        client.setTelephone(updatedClient.getTelephone());
	        sc.modifierCl(client);
	        return "redirect:/listerClient";
	    }
	}
	}

