package com.example.projetspringboot.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Classe {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;
	
	private String label;

	private String nom_compte;
	
     @OneToMany(mappedBy = "classe")
	 List<Etudiant> etudiant;
     
     @ManyToMany(mappedBy = "classes")
     List<Matiere> matiere=new ArrayList<Matiere>();

     @Override
public String toString() {
return this.label ;	
}

}