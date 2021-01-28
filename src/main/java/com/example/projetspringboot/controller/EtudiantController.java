package com.example.projetspringboot.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.projetspringboot.Model.Classe;
import com.example.projetspringboot.Model.Etudiant;
import com.example.projetspringboot.Model.Matiere;
import com.example.projetspringboot.repos.ClasseRepository;
import com.example.projetspringboot.repos.EtudiantRepository;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value = { "/","/etudiants/"})
@AllArgsConstructor
public class EtudiantController {
	// @RequestMapping(value="/P", method=RequestMethod.GET)
	// public String afficherEtudiant() {

	// return "1";
	// }
	private final EtudiantRepository etudiantRepository;
	private ClasseRepository classeRepository;

	@GetMapping("signup")
	public String showSignUpForm(Etudiant etudiant, Model model) {

		model.addAttribute("classes", classeRepository.findAll());

		return "add-student";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("classes", classeRepository.findAll());
		model.addAttribute("classe", new Classe());

		model.addAttribute("etudiants", etudiantRepository.findAll());
		return "index";
	}

	@PostMapping("/add")
	public String addEtudiants(@Valid Etudiant etudiant, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-student";
		}

		etudiantRepository.save(etudiant);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Etudiant etudiant = etudiantRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		model.addAttribute("classes", classeRepository.findAll());

		model.addAttribute("etudiant", etudiant);
		return "update-student";
	}

	@PostMapping("update/{id}")
	public String updateStudent(@PathVariable("id") long id, @Valid Etudiant etudiant, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			etudiant.setId(id);
			return "update-student";
		}

		etudiantRepository.save(etudiant);
		model.addAttribute("etudiants", etudiantRepository.findAll());
		return "index";
	}

	@GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
		Etudiant etudiant = etudiantRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid etudiant	 Id:" + id));
		etudiantRepository.delete(etudiant);
		model.addAttribute("etudiants", etudiantRepository.findAll());
		return "index";
	}
	
	

	@GetMapping("/matieres")
	@ResponseBody
	public List<Matiere> getListMatiereByEtudId(@RequestParam("ide") Long id) {
		
		System.out.println(id);
		Etudiant  etudiant=etudiantRepository.findById(id).get() ;
		
		return etudiant.getClasse().getMatiere() ;
	} 
	@GetMapping("/erreur")
	public String erreur() {
		return "erreur" ;
	}

}
