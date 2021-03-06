package com.bae.personalprojectstarships.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

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

	@Test
	void testRead() {
		Starship savedStarship = new Starship(1L, "Enterprise", "D", 12);
		List<Starship> allStarships = List.of(savedStarship);

		Mockito.when(this.service.getAllStarships()).thenReturn(allStarships);

		assertThat(this.service.getAllStarships()).isEqualTo(allStarships);

	}

	@Test
	void testUpdate() {
		Long id = 1L;

		Starship newStarship = new Starship("Voyager", "M", 12);

		Optional<Starship> optionalStarship = Optional.of(new Starship(id, "Voyager", "M", 12));

		Starship updatedStarship = new Starship(id, newStarship.getName(), newStarship.getModel(),
				newStarship.getAge());

		Mockito.when(this.repo.findById(id)).thenReturn(optionalStarship);

		Mockito.when(this.repo.save(updatedStarship)).thenReturn(updatedStarship);

		assertThat(this.service.updateStarship(id, newStarship)).isEqualTo(updatedStarship);
	}

	@Test
	void testDelete() {
		Long id = 1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(true);

		assertThat(this.service.removeStarship(id)).isEqualTo(false);
	}

	@Test
	void testDeleteTheSequel() {
		Long id = 1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		assertThat(this.service.removeStarship(id)).isEqualTo(true);
	}

}
