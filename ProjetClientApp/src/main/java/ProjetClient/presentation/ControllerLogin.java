package ProjetClient.presentation;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ProjetClient.dao.entities.Client;
import ProjetClient.service.Iservice.IServiceClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


@AllArgsConstructor

@Controller
public class ControllerLogin {
	
	private final IServiceClient is;
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
    public String login() {
        return "login"; 
        
    }
	// ControllerLogin.java

	@PostMapping("/login")
	public String processLogin() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
	        return "redirect:/listerClient";
	    } else {
	        return "redirect:/clientvue";
	    }
	}

	@PostMapping("/signupclient")
	public String insererClient(Model model, @Valid Client c, BindingResult br) {
	    if (br.hasErrors()) {
	        return "login";
	    } else {
	        c.setMotDePasse(passwordEncoder.encode(c.getMotDePasse()));
	        is.ajouterCl(c);
	        return "login";
	    }
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
	    // Invalidate session and clear authentication
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
    }