package com.dbalthassat.mapper;

import com.dbalthassat.dto.PersonOfEventDTO;
import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.Person;
import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.exception.NotFoundException;

import java.util.Optional;

/**
 * ${END}
 *
 * @author dbalthassat
 */
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

    public static Event addPeopleToAvoid(Event event, PersonOfEvent personOfEvent) throws NotFoundException {
        for(Person personToAvoid: personOfEvent.getPeopleToAvoid()) {
            Optional<PersonOfEvent> op = event.getPersons().stream().filter(e -> e.getPerson().equals(personToAvoid)).findAny();
            if(!op.isPresent()) {
                throw new NotFoundException("Person " + personOfEvent.getPerson().getName() + " does not exist in the event.");
            }
            personOfEvent.addPeopleToAvoid(op.get().getPerson());
        }
        return event;
    }
}
