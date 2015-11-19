package com.dbalthassat.service;

import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.repository.EventRepository;
import com.dbalthassat.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private PersonRepository personRepository;

	public Event create(Event event) {
		event.getPersons().stream().map(PersonOfEvent::getPerson).forEach(p -> {
			if(p.getId() == null) {
				personRepository.save(p);
			}
		});
		return eventRepository.save(event);
	}

	public Event find(Long id) throws NotFoundException {
		Event event =  eventRepository.findOne(id);
		if(event == null) {
			throw new NotFoundException("The event " + id + " doesn't exist.");
		}
		return event;
	}
}
