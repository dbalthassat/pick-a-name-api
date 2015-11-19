package com.dbalthassat.mapper;

import com.dbalthassat.dto.EventDTO;
import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.dto.PersonOfEventDTO;
import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.Person;
import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public class PersonOfEventMapper {
	public static PersonOfEventDTO map(PersonOfEvent personOfEvent) {
		if(personOfEvent == null) {
			return null;
		}
		PersonOfEventDTO dto = new PersonOfEventDTO();
		dto.setEventId(personOfEvent.getEvent().getId());
		dto.setPerson(PersonMapper.map(personOfEvent));
		return dto;
	}

    public static void addPeopleToAvoid(EventDTO dto, Event event) throws NotFoundException {
        for(PersonOfEvent personOfEvent: event.getPersons()) {
            PersonDTO actualPerson = dto.getPersons().stream().filter(e -> e.getName().equals(personOfEvent.getPerson().getName())).findAny().get();
            for(String nameToAvoid: actualPerson.getPeopleToAvoid()) {
                Optional<PersonOfEvent> op = event.getPersons().stream().filter(e -> e.getPerson().getName().equals(nameToAvoid)).findAny();
                if(!op.isPresent()) {
                    throw new NotFoundException(nameToAvoid + " does not exist in the event.");
                }
                personOfEvent.addPeopleToAvoid(op.get().getPerson());
            }
        }
    }
}
