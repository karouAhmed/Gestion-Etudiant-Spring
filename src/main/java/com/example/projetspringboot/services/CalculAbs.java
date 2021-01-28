package com.example.projetspringboot.services;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.projetspringboot.Model.Etudiant;
import com.example.projetspringboot.repos.AbsenceRepository;
import com.example.projetspringboot.repos.EtudiantRepository;
import com.example.projetspringboot.repos.MatiereRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@AllArgsConstructor
@Data
public class CalculAbs {

	AbsenceRepository absenceRepository;
	EtudiantRepository etudiantRepo;
     MatiereRepository matiereRepo ;
	@Scheduled(cron = "45 11 20 * 1-6,9-12 Wed")

	void Calcul() {
		System.out.println("Concul des absences pour toutes les etudiants");

		Iterable<Etudiant> etudiants = etudiantRepo.findAll();
		etudiants.forEach(e -> {

			matiereRepo.findByClassesId(e.getClasse().getId()).forEach(m->{
				
				
				Optional<Double> nbabs = absenceRepository.findNbHeureAbsByEtudiantIdAndMatiereId(e.getId(), m.getId());
				if (nbabs.isPresent() & m.getNombre_heure() * 3 / 10 < nbabs.orElse(0.0)) {
					try {
						sendEmail(e.getEmail(), "Elimination ",
								"elimene en mtiere " + m.getLabel() + "vous avez " + nbabs.get() + "hure d'absence");
					} catch (MessagingException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
				
			});
			 
		});
		System.out.println("finish ..... !");
	}

	public boolean sendEmail(String mail, String subject, String mailText)
			throws AddressException, MessagingException, IOException {

		System.out.println("send main to " + mail);

		final String username = "bessem.manita@gmail.com"; // enter your mail id
		final String password = "Mkfrlrtb13457396";// enter ur password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bessem.manita@gmail.com")); // same email id
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			message.setSubject(subject);
			message.setText(mailText);

			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return true;

	}
}
