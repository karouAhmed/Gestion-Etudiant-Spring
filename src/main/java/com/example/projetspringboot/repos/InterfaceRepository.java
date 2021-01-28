package com.example.projetspringboot.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projetspringboot.Model.Interface;

@Repository
public interface InterfaceRepository  extends JpaRepository<Interface,Long>{

}
