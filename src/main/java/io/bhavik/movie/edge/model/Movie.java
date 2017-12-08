package io.bhavik.movie.edge.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Movie implements OnCreate, OnUpdate {

	private UUID id;

	private String name;

	private List<UUID> genres;

	private ZonedDateTime createdDateTime;

	private ZonedDateTime updatedDateTime;
	
	private Boolean released;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UUID> getGenres() {
		return genres;
	}

	public void setGenres(List<UUID> genres) {
		this.genres = genres;
	}

	public ZonedDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(ZonedDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public ZonedDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(ZonedDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public Boolean getReleased() {
		return released;
	}

	public void setReleased(Boolean released) {
		this.released = released;
	}
}
