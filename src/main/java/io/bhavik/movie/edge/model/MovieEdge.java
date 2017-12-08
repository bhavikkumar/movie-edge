package io.bhavik.movie.edge.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class MovieEdge implements OnCreate, OnUpdate {

	@Null(groups = { OnCreate.class, OnUpdate.class })
	private UUID id;

	@NotNull(groups = { OnCreate.class, OnUpdate.class })
	private String name;

	@NotNull(groups = { OnCreate.class, OnUpdate.class })
	private List<String> genres;

	@NotNull(groups = OnUpdate.class)
	@Null(groups = OnCreate.class)
	private ZonedDateTime createdOn;

	@NotNull(groups = OnUpdate.class)
	@Null(groups = OnCreate.class)
	private ZonedDateTime updatedOn;
	
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

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public ZonedDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(ZonedDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public ZonedDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(ZonedDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the released
	 */
	public Boolean getReleased() {
		return released;
	}

	/**
	 * @param released the released to set
	 */
	public void setReleased(Boolean released) {
		this.released = released;
	}

}
