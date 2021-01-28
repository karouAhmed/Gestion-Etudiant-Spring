package com.example.projetspringboot.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.projetspringboot.Model.Absence;
import com.example.projetspringboot.Model.Matiere;
import com.example.projetspringboot.repos.AbsenceRepository;
import com.example.projetspringboot.repos.EtudiantRepository;
import com.example.projetspringboot.repos.MatiereRepository;
import com.example.projetspringboot.services.CalculAbs;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/absences")
@AllArgsConstructor
public class AbsenceController {

	 
	
	
	AbsenceRepository absenceRepository;
	MatiereRepository matiereRepository;
	EtudiantRepository etudiantRepository;
	CalculAbs calulServ;

	@GetMapping("add")
	public String showSignUpForm(Absence absence, Model model) {
		model.addAttribute("matieres", new ArrayList<Matiere>());
		model.addAttribute("etudiants", etudiantRepository.findAll());
		model.addAttribute("abslist", Arrays.asList("1.5", "3"));
		return "add-absence";
	}

	@GetMapping("list")
	public String showUpdateForm(Absence absence, Model model) {
		model.addAttribute("matieres", matiereRepository.findAll());
		model.addAttribute("etudiants", etudiantRepository.findAll());
		model.addAttribute("absences", absenceRepository.findAll());
		return "AbsenceIndex";

	}

	@PostMapping("/add")
	public String addabsence(@Valid Absence absence, BindingResult result, Model model) {
		 
		System.out.println(absence.getMatiere().getId());
		if (result.hasErrors()) {
			return "redirect:add";
		}
		absenceRepository.save(absence);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Absence absence = absenceRepository.findById(id)

				.orElseThrow(() -> new IllegalArgumentException("Invalid class Id:" + id));
		model.addAttribute("absence", absence);
		model.addAttribute("matieres", matiereRepository.findAll());
		model.addAttribute("etudiants", etudiantRepository.findAll());
		model.addAttribute("abslist", Arrays.asList("1.5", "3"));

		return "update-absence";
	}

	@PostMapping("update/{id}")
	public String updateAbsence(@PathVariable("id") long id, @Valid Absence absence, BindingResult result,
			Model model) {
		if (result.hasErrors()) {

			return "update-absence";
		}
		Absence abs = absenceRepository.findById(id).get();
		absence.setId(id);
		absence.setEtudiant(abs.getEtudiant());
		absence.setMatiere(abs.getMatiere());
		absenceRepository.save(absence);
		model.addAttribute("absences", absenceRepository.findAll());
		return "AbsenceIndex";
	}

	@GetMapping("delete/{id}")
	public String deleteabsence(@PathVariable("id") long id, Model model) {
		Optional<Absence> absence = absenceRepository.findById(id);
		if (absence.isPresent())
		{
			absenceRepository.delete(absence.get());
			model.addAttribute("absences", absenceRepository.findAll());
			return "AbsenceIndex";
		}
		else return "redirect:/erreur" ;

	}

	@GetMapping("/envoi")
	void sendMail() {
		try {
			calulServ.sendEmail("ihebhasni6@gmail.com", "MAilTest ", "Mail Mail");
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
