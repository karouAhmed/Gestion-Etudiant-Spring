package com.example.projetspringboot.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetspringboot.Model.Classe;
@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {

	Classe findByLabel(String label);
	//long deleteByLabel(String label);
	
	
}
