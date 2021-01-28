package com.example.projetspringboot.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.projetspringboot.Model.Etudiant;
import com.example.projetspringboot.Model.Matiere;
import com.example.projetspringboot.repos.AbsenceRepository;
import com.example.projetspringboot.repos.EtudiantRepository;
import com.example.projetspringboot.repos.MatiereRepository;
import com.example.projetspringboot.services.ClasseService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
@RequestMapping("/showabs")
@AllArgsConstructor
public class InterfaceController {
	AbsenceRepository absenceRepository;
	MatiereRepository matiereRepository;
	EtudiantRepository etudiantRepository;
	private ClasseService classeSerivce;

	@Data
	private class Msg {
		private Integer matiere, etudiant;
	}

	@GetMapping
	public String showSelectForm(Matiere matiere, Etudiant etudiant, Model model) {

		model.addAttribute("matieres", matiereRepository.findAll());
		model.addAttribute("etudiants", etudiantRepository.findAll());
		model.addAttribute("absences", absenceRepository.findAll());
		model.addAttribute("msg", new Msg());
		return "InterfaceIndex";
	}

	@PostMapping
	public String process(@ModelAttribute("msg") Msg msg) {
		return "redirect:/showabs/" + msg.getMatiere() + "/" + msg.getEtudiant();
	}

	@GetMapping("/{matiere}/{etudiant}")
	public String showDetailAbs(@PathVariable Long matiere, @PathVariable Long etudiant, Model model,
			HttpServletRequest request) {
		// request.setAttribute("totalabs",absenceRepository.findNbHeureAbsByEtudiantIdAndMatiereId(etudiant,
		// matiere));
		model.addAttribute("matiere", matiereRepository.findById(matiere).get());
		Optional<Double> totalabs = absenceRepository.findNbHeureAbsByEtudiantIdAndMatiereId(etudiant, matiere);
		if (totalabs.isPresent())

			model.addAttribute("totalabs", totalabs.get());
		else
			model.addAttribute("totalabs", 0);

		List<?> listSearch = absenceRepository.findByMinValeurAndMatiereId(matiere);
		Object[] minstd = null;
		Object[] maxstd = null;
		if (listSearch != null && !listSearch.isEmpty()) {
			minstd = (Object[]) listSearch.get(0);
			System.out.println(Arrays.toString(minstd));
			model.addAttribute("minabs", minstd[minstd.length - 1]);
			maxstd = ((Object[]) listSearch.toArray()[listSearch.toArray().length - 1]);
			
			model.addAttribute("maxabs", maxstd[maxstd.length - 1]);
		}

		model.addAttribute("etudiant", etudiantRepository.findById(etudiant).get());
		System.out.println(absenceRepository.findNbHeureAbsByEtudiantIdAndMatiereId(etudiant, matiere));
		model.addAttribute("absences", absenceRepository.findByEtudiantIdAndMatiereId(etudiant, matiere));

		return "absdetails";
	}

	@GetMapping("/{classe}")
	public String showDetailAbsMatiere(@PathVariable("classe") Long classe, Model model) {
		// request.setAttribute("totalabs",absenceRepository.findNbHeureAbsByEtudiantIdAndMatiereId(etudiant,
		// matiere));
		model.addAttribute("classeabs", classeSerivce.getClasseAbs(classe));
		return "classeabs";
	}
}
