package com.codingdojo.nathaly.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.nathaly.modelos.Usuario;
import com.codingdojo.nathaly.servicios.Servicios;

@Controller
public class ControladorUsuarios {

	@Autowired
	private Servicios servicio;
	
	@GetMapping("/")
	public String index(@ModelAttribute("nuevoUsuario")Usuario nuevoUsuario) {
		return "index.jsp";
	}
	
	@PostMapping("/registro")
	public String registro(@Valid @ModelAttribute("nuevoUsuario")Usuario nuevoUsuario,
							BindingResult result,
							HttpSession session) {
		servicio.registrar(nuevoUsuario, result);
		
		if(result.hasErrors()) {
			return "index.jsp";
		}else {
			session.setAttribute("usuarioEnSesion", nuevoUsuario);
			return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email,
						@RequestParam("contrasena") String contrasena,
						RedirectAttributes redirectAttributes,
						HttpSession session) {
		
		Usuario usuarioLogin= servicio.login(email, contrasena);
		if(usuarioLogin==null) {
			redirectAttributes.addFlashAttribute("error_login","El correo/password es incorrecto");
			return "redirect:/";
		}else {
			session.setAttribute("usuarioEnSesion", usuarioLogin);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("usuarioEnSesion");
		return "redirect:/";
	}
}
