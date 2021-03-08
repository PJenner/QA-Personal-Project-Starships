package com.bae.personalprojectstarships.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.personalprojectstarships.domain.Starship;

@Repository
public interface StarshipRepo extends JpaRepository<Starship, Long> {

}
