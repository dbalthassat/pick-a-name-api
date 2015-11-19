package com.dbalthassat.mapper;

import com.dbalthassat.dto.EventDTO;
import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.PersonOfEvent;

import java.util.Objects;
import java.util.stream.Collectors;

public class EventMapper {
	private EventMapper() {}

	public static Event map(EventDTO dto) {
		if(dto == null) {
			return null;
		}
		Event event = new Event();
		event.setId(dto.getId());
		event.setName(dto.getName());
		event.setSlug(dto.getSlug());
		event.setPersons(dto.getPersons().stream().map(p -> new PersonOfEvent(event, p)).collect(Collectors.toSet()));
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
		dto.setPersons(event.getPersons().stream().map(PersonOfEvent::getPerson).collect(Collectors.toList()));
		return dto;
	}
}
