package com.dbalthassat.mapper;

import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.entity.Person;

import java.util.Objects;

public class PersonMapper {
	public static PersonDTO map(PersonOfEvent personOfEvent) {
		if(personOfEvent == null) {
			return null;
		}
		PersonDTO dto = map(personOfEvent.getPerson());
		dto.setHasFriend(personOfEvent.getFriend() != null);
		return dto;
	}

	private static PersonDTO map(Person person) {
		if(person == null) {
			return null;
		}
		PersonDTO dto = new PersonDTO();
		dto.setId(person.getId());
		dto.setName(person.getName());
		return dto;
	}

	public static PersonDTO mapFriend(PersonOfEvent op, PersonDTO person) {
		Objects.requireNonNull(op);
		Objects.requireNonNull(person);
		person.setFriend(op.getFriend() != null ? op.getFriend().getName() : "");
		return person;
	}
}
