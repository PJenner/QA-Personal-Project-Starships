package com.bae.personalprojectstarships.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.personalprojectstarships.domain.Starship;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:SQLImplementation.sql",
		"classpath:starship-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class StarshipControllerIntegrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Starship newStarship = new Starship("Enterprise", "D", 12);
		String newStarshipAsJSON = this.mapper.writeValueAsString(newStarship);

		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(newStarshipAsJSON);

		Starship savedStarship = new Starship(2L, "Enterprise", "D", 12);

		String savedStarshipAsJSON = this.mapper.writeValueAsString(savedStarship);

		ResultMatcher matchStatus = status().isOk();

		ResultMatcher matchBody = content().json(savedStarshipAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

	@Test
	void testRead() throws Exception {
		Starship testStarship = new Starship(1L, "Enterprise", "D", 12);
		List<Starship> allStarships = List.of(testStarship);
		String testStarshipAsJson = this.mapper.writeValueAsString(allStarships);

		RequestBuilder mockRequest = get("/getAll");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(testStarshipAsJson);

		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdate() throws Exception {
		Starship updateTestStarship = new Starship("Voyager", "F", 17);
		String updateTestStarshipAsJson = this.mapper.writeValueAsString(updateTestStarship);

		RequestBuilder mockRequest = put("/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(updateTestStarshipAsJson);

		Starship savedTestStarship = new Starship(1L, "Voyager", "F", 17);
		String savedTestStarshipAsJSON = this.mapper.writeValueAsString(savedTestStarship);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(savedTestStarshipAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

}
