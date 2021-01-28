package com.example.projetspringboot.Model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Etudiant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long id;
	//private long matricule;
	@NonNull
	@Size(max = 45, message = "Name must be less than 45 characters")
	@Column(name = "nom", length = 45)
	@NotBlank(message = "le nom est obligatoire")

	private String nom;
	@NonNull
	@Size(max = 45, message = "LastName must be less than 45 characters")
	@Column(name = "prenom", length = 45)
	@NotBlank(message = "Le prénom est obligatoire")

	private String prenom;
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date_naissance;
	@NotBlank(message = "Email est obligatoire")
	@Column(name = "email", length = 45)
	@Email
	private String email;
	@ManyToOne
	private Classe classe;

	public String toString() {
		return nom + " " + prenom;
	}

}
