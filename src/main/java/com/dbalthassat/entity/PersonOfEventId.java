package com.dbalthassat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PersonOfEventId implements Serializable {
	@ManyToOne(fetch = FetchType.LAZY)
	private Event event;

	@ManyToOne(fetch = FetchType.LAZY)
	private Person person;

	public PersonOfEventId() {
	}

	public PersonOfEventId(Event event, Person person) {
		this.event = event;
		this.person = person;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
