package com.dbalthassat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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

    @OneToMany
    private List<Person> peopleToAvoid;

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

	public Person getFriend() {
		return friend;
	}

	public void setFriend(Person friend) {
		this.friend = friend;
	}

    public List<Person> getPeopleToAvoid() {
        return peopleToAvoid;
    }

    public void setPeopleToAvoid(List<Person> peopleToAvoid) {
        this.peopleToAvoid = peopleToAvoid;
    }

    public void addPeopleToAvoid(Person person) {
        if(peopleToAvoid == null) {
            peopleToAvoid = new LinkedList<>();
        }
        peopleToAvoid.add(person);
    }
}
