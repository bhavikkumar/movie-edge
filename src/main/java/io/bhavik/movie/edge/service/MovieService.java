package io.bhavik.movie.edge.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import io.bhavik.movie.edge.model.Genre;
import io.bhavik.movie.edge.model.Movie;
import io.bhavik.movie.edge.model.MovieEdge;

@Named
public class MovieService {

	private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

	@Inject
	RestTemplate restTemplate;

	private static final String movieEndPoint = "http://movie/movie";
	private static final String genreEndPoint = "http://genre/genre";

	public MovieEdge createMovie(MovieEdge movie) {
		Map<UUID, String> genres = new HashMap<UUID, String>();

		for (String genre : movie.getGenres()) {
			Genre[] searchResult = this.restTemplate.getForEntity(genreEndPoint + "?name={name}", Genre[].class, genre)
					.getBody();
			if (searchResult.length == 0) {
				Genre toCreate = new Genre();
				toCreate.setName(genre);

				Genre created = this.restTemplate.postForEntity(genreEndPoint, toCreate, Genre.class).getBody();
				genres.put(created.getId(), created.getName());
			} else {
				genres.put(searchResult[0].getId(), searchResult[0].getName());
			}
		}

		Movie toCreate = new Movie();
		toCreate.setName(movie.getName());
		List<UUID> genreIds = new ArrayList<UUID>();
		genreIds.addAll(genres.keySet());
		toCreate.setGenres(genreIds);
		Movie response = this.restTemplate.postForEntity(movieEndPoint, toCreate, Movie.class).getBody();

		movie.setId(response.getId());
		movie.setName(response.getName());
		List<String> genreNames = new ArrayList<String>();
		genreNames.addAll(genres.values());
		movie.setGenres(genreNames);
		movie.setCreatedOn(response.getCreatedDateTime());
		movie.setUpdatedOn(response.getUpdatedDateTime());
		movie.setReleased(response.getReleased());

		return movie;
	}

	public MovieEdge getMovie(UUID id) {
		Movie response = this.restTemplate.getForEntity(movieEndPoint + "/{id}", Movie.class, id).getBody();

		List<String> genres = new ArrayList<String>();
		for (UUID genreId : response.getGenres()) {
			genres.add(
					this.restTemplate.getForEntity(genreEndPoint + "/{id}", Genre.class, genreId).getBody().getName());
		}

		MovieEdge movie = new MovieEdge();
		movie.setId(response.getId());
		movie.setName(response.getName());
		movie.setGenres(genres);
		movie.setCreatedOn(response.getCreatedDateTime());
		movie.setUpdatedOn(response.getUpdatedDateTime());
		movie.setReleased(response.getReleased());

		return movie;
	}

}
