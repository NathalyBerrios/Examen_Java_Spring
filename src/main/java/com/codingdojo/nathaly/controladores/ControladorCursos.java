package com.codingdojo.nathaly.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.nathaly.modelos.Curso;
import com.codingdojo.nathaly.modelos.Usuario;
import com.codingdojo.nathaly.servicios.Servicios;

@Controller
public class ControladorCursos {

	@Autowired
	private Servicios servicio;
	
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		//revisa que mi usuasrio haya iniciado sesion
		Usuario usuarioEnSesion=(Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioEnSesion==null) {
			return "redirect:/";
		}
		//revisa que mi usuasrio haya iniciado sesion
		model.addAttribute("cursos", servicio.findCursos()); 
		return "dashboard.jsp";
	}
	
	@GetMapping("/agregar/{id}")
		public String agregar(@PathVariable("id")Long id, HttpSession session, Model model) {
		//revisa que mi usuasrio haya iniciado sesion
		Usuario usuarioEnSesion=(Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioEnSesion==null) {
			return "redirect:/";
		}
		//revisa que mi usuasrio haya iniciado sesion
		servicio.unirseCurso(usuarioEnSesion.getId(), id);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(HttpSession session, @ModelAttribute("curso")Curso curso) {
		//revisa que mi usuasrio haya iniciado sesion
		Usuario usuarioEnSesion=(Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioEnSesion==null) {
			return "redirect:/";
		}
		//revisa que mi usuasrio haya iniciado sesion
		
		return "nuevo.jsp";
	}
	
	@PostMapping("/nuevo")
	public String nuevoCurso(@Valid @ModelAttribute("curso") Curso curso, BindingResult result, HttpSession session) {
		//revisa que mi usuasrio haya iniciado sesion
		Usuario usuarioEnSesion=(Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioEnSesion==null) {
			return "redirect:/";
		}
		//revisa que mi usuasrio haya iniciado sesion
		
		if(result.hasErrors()) {
			return "nuevo.jsp";
		}else {
			servicio.guardarCurso(curso);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/mostrar/{cursoId}")
	public String mostrar(@PathVariable("cursoId")Long id, HttpSession session, Model model) {
		//revisa que mi usuasrio haya iniciado sesion
		Usuario usuarioEnSesion=(Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioEnSesion==null) {
			return "redirect:/";
		}
		//revisa que mi usuasrio haya iniciado sesion
		
		
		
		Curso curso= servicio.findCurso(id);
		model.addAttribute("curso", curso);
		return "mostrar.jsp";
	}
	
	@DeleteMapping("/borrar/{id}")
	public String borrar(@PathVariable("id")Long id, HttpSession session) {
		//revisa que mi usuasrio haya iniciado sesion
		Usuario usuarioEnSesion=(Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioEnSesion==null) {
			return "redirect:/";
		}
		//revisa que mi usuasrio haya iniciado sesion
		servicio.borrarCurso(id);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id")Long id, @ModelAttribute("curso")Curso curso, HttpSession session, Model model) {
		//revisa que mi usuasrio haya iniciado sesion
		Usuario usuarioEnSesion=(Usuario)session.getAttribute("usuarioEnSesion");
		if(usuarioEnSesion==null) {
			return "redirect:/";
		}
		//revisa que mi usuasrio haya iniciado sesion
		
		Curso cursoEdit= servicio.findCurso(id);
		model.addAttribute("curso", cursoEdit);
		return "editar.jsp";
	}
	
	@PutMapping("/update")
	public String update(@Valid @ModelAttribute("curso")Curso curso, BindingResult result, HttpSession session) {
		//revisa que mi usuasrio haya iniciado sesion
	    Usuario usuarioEnSesion=(Usuario)session.getAttribute("usuarioEnSesion");
	    if(usuarioEnSesion==null) {
		    return "redirect:/";
	    }
	    //revisa que mi usuasrio haya iniciado sesion
	    if(result.hasErrors()) {
	    	return "editar.jsp";
	    }else {
	    	servicio.guardarCurso(curso);
	    	return "redirect:/dashboard";
	    }
	}
}
