package com.example.projetspringboot.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Matiere {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;
	@NonNull
	@Size(max=45, message = "label must be less than 45 characters")
	@Column(name = "label",length = 45)
    @NotBlank(message = "le label est obligatoire")
	private String label;
	@NonNull
	private double nombre_heure;
	@JsonIgnore
 	@ManyToMany
	private List<Classe> classes =new ArrayList<Classe> ();

	
	public String toString(){
		return label ;
	}
}
