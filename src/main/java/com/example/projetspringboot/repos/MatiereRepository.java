package com.example.projetspringboot.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.projetspringboot.Model.Matiere;

@Repository
public interface MatiereRepository extends CrudRepository<Matiere, Long> {

	//@Query("select m from Matiere m ")
	//List<Matiere> findAll();

	List<Matiere> findByClassesId(long id);

}