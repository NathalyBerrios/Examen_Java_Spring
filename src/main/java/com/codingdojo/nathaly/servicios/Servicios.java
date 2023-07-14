package com.codingdojo.nathaly.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.nathaly.modelos.Curso;
import com.codingdojo.nathaly.modelos.Usuario;
import com.codingdojo.nathaly.repositorios.RepositorioCursos;
import com.codingdojo.nathaly.repositorios.RepositorioUsuarios;

@Service
public class Servicios {

	@Autowired
	private RepositorioUsuarios repoUsuarios;
	
	@Autowired 
	private RepositorioCursos repoCursos;
	
	//metodo para registrar un nuevo usuario
	public Usuario registrar(Usuario nuevoUsuario, BindingResult result) {
			
		String email= nuevoUsuario.getEmail();
		Usuario existeUsuario= repoUsuarios.findByEmail(email); 
		if(existeUsuario!=null) {
			result.rejectValue("email", "Unique", "El correo ingresado ya esta en uso"); 
		}
		
		//comparamos contraseñas
		String contrasena= nuevoUsuario.getContrasena();
		String confirmacion= nuevoUsuario.getConfirmacion();
		if(!contrasena.equals(confirmacion)) {// si contraseña no es igual a confirmacion
			result.rejectValue("confirmacion", "Matches", "La contraseña no coincide"); //campo en el que se equivoco=confirmacion
		}
		if(result.hasErrors()) {
			return null;
		}else {
			//encriptamos contraseña
			String contra_encriptada= BCrypt.hashpw(contrasena, BCrypt.gensalt());
			nuevoUsuario.setContrasena(contra_encriptada);
			return repoUsuarios.save(nuevoUsuario);
		}
	}
	
	public Usuario login(String email, String password) {
		
		Usuario existeUsuario= repoUsuarios.findByEmail(email);
		if(existeUsuario==null) {
			return null;
		}
		if(BCrypt.checkpw(password, existeUsuario.getContrasena())) {
			return existeUsuario;
		}else {
			return null;
		}
	}
	
	//guardar cursos
	public Curso guardarCurso(Curso curso) {
		return repoCursos.save(curso);
	}
	
	//encontrar un usuario en base a su id
	public Usuario findUsuario(Long id) {
		return repoUsuarios.findById(id).orElse(null);
	}
	
	//lista de cursos
	public List<Curso> findCursos(){
		return repoCursos.findAll();
	}
	
	//encontrar curso en base a su id
	/*public Curso findCurso(Long id) {
		return repoCursos.findById(id).orElse(null);
	}*/
	public Curso findCurso(Long id) {
		Optional<Curso> c= repoCursos.findById(id);
		if(c.isPresent()) {
            return c.get();
        }else {
            return null;
        }
	}
	
	//usuario se une a curso
	public void unirseCurso(Long usuarioId, Long cursoId) {
		Usuario usuario=findUsuario(usuarioId); 
		Curso curso=findCurso(cursoId); 
		
		curso.getUsuarios().add(usuario);
		repoCursos.save(curso);
	}
	
	//borrar curso
	public void borrarCurso(Long id) {
		repoCursos.deleteById(id);
	}
	
}
