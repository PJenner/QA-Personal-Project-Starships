package com.bae.personalprojectstarships.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.bae.personalprojectstarships.domain.Starship;
import com.bae.personalprojectstarships.repos.StarshipRepo;
import com.bae.personalprojectstarships.service.starshipservice.StarshipService;

@SpringBootTest
@ActiveProfiles("test")
public class StarshipServiceDBUnitTest {

	@Autowired
	private StarshipService service;

	@MockBean
	private StarshipRepo repo;

	@Test
	void testCreate() {
		Starship newStarship = new Starship("Enterprise", "D", 20);
		Starship savedStarship = new Starship(1L, "Enterprise", "D", 20);

		Mockito.when(this.repo.save(newStarship)).thenReturn(savedStarship);

		assertThat(this.service.createStarship(newStarship)).isEqualTo(savedStarship);

		Mockito.verify(this.repo, Mockito.times(1)).save(newStarship);
	}

}
