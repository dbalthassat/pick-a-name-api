package com.dbalthassat.dto;

import com.dbalthassat.dto.groups.Create;
import com.dbalthassat.dto.groups.Update;
import com.dbalthassat.entity.Person;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;

public class EventDTO {
	@NotBlank(groups = Update.class)
	@Null(groups = Create.class)
	private Long id;

	@NotBlank(groups = { Create.class, Update.class })
	private String name;

	private String slug;

	@Valid
	private List<PersonDTO> persons;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public List<PersonDTO> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonDTO> persons) {
		this.persons = persons;
	}
}
