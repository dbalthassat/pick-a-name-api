package com.dbalthassat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person_event", schema = "public")
@AssociationOverrides({
		@AssociationOverride(name = "pk.event",
			joinColumns = @JoinColumn(name = "event_id")),
		@AssociationOverride(name = "pk.person",
			joinColumns = @JoinColumn(name = "person_id"))
})
public class PersonOfEvent implements Serializable {
	@EmbeddedId
	private PersonOfEventId pk = new PersonOfEventId();

	@OneToOne
	private Person friend;

	public PersonOfEvent() {}

	public PersonOfEvent(Event event, Person person) {
		this.pk.setEvent(event);
		this.pk.setPerson(person);
	}

	@Transient
	public Event getEvent() {
		return pk.getEvent();
	}

	@Transient
	public Person getPerson() {
		return pk.getPerson();
	}

	public void setPk(PersonOfEventId pk) {
		this.pk = pk;
	}

	public Person getFriend() {
		return friend;
	}

	public void setFriend(Person friend) {
		this.friend = friend;
	}
}
