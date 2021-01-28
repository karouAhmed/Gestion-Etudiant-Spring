package com.example.projetspringboot.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.projetspringboot.Model.Etudiant;
@Repository
public interface EtudiantRepository extends CrudRepository<Etudiant,Long> {

	
	//@Query("select e from Etudiant e where e.classe=?1")
	List<Etudiant> findByClasseId(Long classe);


}
