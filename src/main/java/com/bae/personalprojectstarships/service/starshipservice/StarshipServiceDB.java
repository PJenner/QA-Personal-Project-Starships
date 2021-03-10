package com.bae.personalprojectstarships.service.starshipservice;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Starship updateStarship(Long id, Starship newStarship) {

		Optional<Starship> existingOptional = this.repo.findById(id);
		Starship existing = existingOptional.get();

		existing.setName(newStarship.getName());
		existing.setModel(newStarship.getModel());
		existing.setAge(newStarship.getAge());

		return this.repo.save(existing);

	}

	@Override
	public boolean removeStarship(Long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}

}