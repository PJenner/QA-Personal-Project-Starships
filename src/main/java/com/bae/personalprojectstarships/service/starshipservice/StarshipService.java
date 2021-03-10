package com.bae.personalprojectstarships.service.starshipservice;

import java.util.List;

import com.bae.personalprojectstarships.domain.Starship;

public interface StarshipService {

	Starship createStarship(Starship starship);

	List<Starship> getAllStarships();

	Starship updateStarship(Long id, Starship newStarship);

}
