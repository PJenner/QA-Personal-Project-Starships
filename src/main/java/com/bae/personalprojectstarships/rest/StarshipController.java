package com.bae.personalprojectstarships.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.personalprojectstarships.domain.Starship;
import com.bae.personalprojectstarships.service.starshipservice.StarshipService;

@RestController
public class StarshipController {

	private StarshipService service;

	public StarshipController(StarshipService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public Starship addStarship(@RequestBody Starship starship) {
		return this.service.createStarship(starship);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Starship>> getAll() {
		return ResponseEntity.ok(this.service.getAllStarships());
	}

	@PutMapping("/update/{id}")
	public Starship updateStarship(@PathVariable Long id, @RequestBody Starship starship) {
		return this.service.updateStarship(id, starship);
	}

}
