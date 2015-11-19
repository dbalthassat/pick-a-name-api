package com.dbalthassat.entity;

import com.dbalthassat.entity.listener.SlugEntityListener;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "event", schema = "public")
@EntityListeners(SlugEntityListener.class)
public class Event implements Serializable, Slugable {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.event", cascade = CascadeType.ALL)
    private Set<PersonOfEvent> persons;

    public Event() {
    }

    public Event(long id) {
        this.id = id;
    }

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

	public Set<PersonOfEvent> getPersons() {
		return persons;
	}

	public void setPersons(Set<PersonOfEvent> persons) {
		this.persons = persons;
	}

	public String getSlug() {
      return slug;
    }

	@Override
	public void setSlug(String slug) {
      this.slug = slug;
    }

	@Override
	public String itemToSlug() {
		return name;
	}
}
