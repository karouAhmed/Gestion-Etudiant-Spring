package com.example.projetspringboot.services;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetspringboot.Model.Classe;
import com.example.projetspringboot.Model.Etudiant;
import com.example.projetspringboot.Model.Matiere;
import com.example.projetspringboot.repos.ClasseRepository;
import com.example.projetspringboot.repos.EtudiantRepository;
import com.example.projetspringboot.repos.MatiereRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Service
@AllArgsConstructor
public class ClasseService {
     private  ClasseRepository classeRepository;
	private EtudiantRepository etudiantRepository ;
	private MatiereRepository matiereRepository ;
	/*public List<Classe> listAll(){
		return classeRepository.findAll();
	}*/
	public void save(Classe classe) {
		classeRepository.save(classe);
	}
	/*public void delete(int id) {
		classeRepository.deleteById(id);
	}*/
	public ClasseDTO getClasseAbs(Long classe) {
		ClasseDTO classeDTO=new ClasseDTO();
		classeDTO.etudiants=etudiantRepository.findByClasseId(classe);
		classeDTO.matieres=matiereRepository.findByClassesId(classe);
  		// TODO Auto-generated method stub
		return classeDTO;
	}

	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	class ClasseDTO {
		
		List<Etudiant> etudiants ;
		List<Matiere> matieres ;
		
		
	}
}
