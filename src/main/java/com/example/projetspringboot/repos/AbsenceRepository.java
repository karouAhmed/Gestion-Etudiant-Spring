package com.example.projetspringboot.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.projetspringboot.Model.Absence;

@Repository
public interface AbsenceRepository extends CrudRepository<Absence, Long> {

	// @Query("select a from Absence a where a.etudiant=?1 and a.matiere= ?2 ")
	List<Absence> findByEtudiantIdAndMatiereId(Long idE, Long idM);

	@Query( value = "select sum(a.valeur) from Absence a where a.etudiant.id=?1 and a.matiere.id= ?2 ")
	Optional<Double> findNbHeureAbsByEtudiantIdAndMatiereId(long id, long id2);

	@Query(value = "select   a.matiere ,a.etudiant ,sum(a.valeur) as  mm  from Absence a "
			+ "where a.matiere.id= ?1"
			+ " group by a.etudiant,a.matiere "
			+ "order by mm asc")
	List<?> findByMinValeurAndMatiereId(Long matiere);
}
