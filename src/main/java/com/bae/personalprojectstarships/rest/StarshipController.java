package com.bae.personalprojectstarships.rest;

import org.springframework.web.bind.annotation.PostMapping;
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

}
