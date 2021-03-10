package com.bae.personalprojectstarships.service.starshipservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.personalprojectstarships.domain.Starship;
import com.bae.personalprojectstarships.repos.StarshipRepo;

@Service
public class StarshipServiceDB implements StarshipService {

	private StarshipRepo repo;

	public StarshipServiceDB(StarshipRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Starship createStarship(Starship starship) {
		return this.repo.save(starship);
	}

	@Override
	public List<Starship> getAllStarships() {
		return this.repo.findAll();
	}

}