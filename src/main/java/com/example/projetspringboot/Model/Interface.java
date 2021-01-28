package com.example.projetspringboot.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Interface {
@Id
@GeneratedValue

private long id;
private String nom;
private String prenom;
private String label;

private long nombre_absence;
public Interface() {
}
public Interface(long matricule,String nom, String prenom , String label, long nombre_absence) {
	this.id=matricule;
	this.nom=nom;
	this.prenom=prenom;
	this.label=label;
	this.nombre_absence=nombre_absence;}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getNombre_absence() {
	return nombre_absence;
}
public void setNombre_absence(long nombre_absence) {
	this.nombre_absence = nombre_absence;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}


}
