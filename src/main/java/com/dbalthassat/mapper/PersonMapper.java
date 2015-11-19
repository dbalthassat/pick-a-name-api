package com.dbalthassat.mapper;

import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.dto.PersonOfEventDTO;
import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.entity.Person;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonMapper {
    public static Person map(PersonDTO dto) {
        if(dto == null) {
            return null;
        }
        Person person = new Person();
        person.setId(dto.getId());
        person.setName(dto.getName());
        return person;
    }

	public static PersonDTO map(PersonOfEvent personOfEvent) {
		if(personOfEvent == null) {
			return null;
		}
		PersonDTO dto = map(personOfEvent.getPerson());
		dto.setHasFriend(personOfEvent.getFriend() != null);
		return dto;
	}

	public static PersonDTO map(Person person) {
		if(person == null) {
			return null;
		}
		PersonDTO dto = new PersonDTO();
		dto.setId(person.getId());
		dto.setName(person.getName());
		return dto;
	}

	public static PersonDTO mapFriend(PersonOfEvent personOfEvent, PersonDTO person) {
		Objects.requireNonNull(personOfEvent);
		Objects.requireNonNull(person);
		person.setFriend(personOfEvent.getFriend() != null ? personOfEvent.getFriend().getName() : "");
		return person;
	}

    public static PersonDTO mapPeopleToAvoid(PersonOfEvent personOfEvent, PersonDTO person) {
        Objects.requireNonNull(personOfEvent);
        Objects.requireNonNull(person);
        if(personOfEvent.getPeopleToAvoid() != null) {
            person.setPeopleToAvoid(personOfEvent.getPeopleToAvoid().stream()
                .map(Person::getName).collect(Collectors.toList()));
        }
        return person;
    }
}
