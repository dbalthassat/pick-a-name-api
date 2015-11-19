package com.dbalthassat.mapper;

import com.dbalthassat.dto.EventDTO;
import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.exception.NotFoundException;

import java.util.Objects;
import java.util.stream.Collectors;

public class EventMapper {
	private EventMapper() {}

	public static Event map(EventDTO dto) throws NotFoundException {
		if(dto == null) {
			return null;
		}
		Event event = new Event();
		event.setId(dto.getId());
		event.setName(dto.getName());
		event.setSlug(dto.getSlug());
        event.setPersons(dto.getPersons().stream().map(p -> new PersonOfEvent(event, PersonMapper.map(p))).collect(Collectors.toSet()));
		PersonOfEventMapper.addPeopleToAvoid(dto, event);
        return event;
	}

	public static EventDTO map(Event event) {
		if(event == null) {
			return null;
		}
		EventDTO dto = new EventDTO();
		dto.setId(event.getId());
		dto.setName(event.getName());
		dto.setSlug(event.getSlug());
		return dto;
	}

	public static EventDTO mapPersons(Event event, EventDTO dto) {
		Objects.requireNonNull(event);
		Objects.requireNonNull(dto);
		dto.setPersons(event.getPersons().stream()
                .map(poe -> PersonMapper.mapPeopleToAvoid(poe, PersonMapper.map(poe.getPerson())))
                .collect(Collectors.toList()));
		return dto;
	}
}
