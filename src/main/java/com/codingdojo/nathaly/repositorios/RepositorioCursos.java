package com.codingdojo.nathaly.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.nathaly.modelos.Curso;


@Repository
public interface RepositorioCursos extends CrudRepository<Curso, Long>{

	List<Curso>findAll();
	
	Optional<Curso> findById(Long id);
	void deleteById(Long id);
}
