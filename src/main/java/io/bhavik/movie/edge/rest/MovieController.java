package io.bhavik.movie.edge.rest;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.bhavik.movie.edge.model.MovieEdge;
import io.bhavik.movie.edge.model.OnCreate;
import io.bhavik.movie.edge.service.MovieService;

@RestController
@RequestMapping("/movie-edge")
public class MovieController {

	@Inject
	private MovieService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MovieEdge createMovie(@Validated(OnCreate.class) @RequestBody(required = true) MovieEdge movie) {
		return service.createMovie(movie);
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public MovieEdge getMovie(@PathVariable UUID id) {
		return service.getMovie(id);
	}
}
