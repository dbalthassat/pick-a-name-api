package com.dbalthassat.service;

import com.dbalthassat.entity.Event;
import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.repository.EventRepository;
import com.dbalthassat.repository.PersonOfEventRepository;
import com.dbalthassat.utils.FriendshipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonOfEventService {
	@Autowired
	private PersonOfEventRepository personOfEventRepository;

	@Autowired
	private EventRepository eventRepository;

	public PersonOfEvent find(Long eventId, String firstname) throws NotFoundException {
		return findEventPerson(eventId, firstname);
	}

	public PersonOfEvent findFriend(Long eventId, String firstname) throws NotFoundException, BadRequestException {
		PersonOfEvent personOfEvent = findEventPerson(eventId, firstname);
		if(personOfEvent.getFriend() != null) {
			throw new BadRequestException("The person " + firstname + " already has a friend.");
		}
		personOfEvent.setFriend(FriendshipUtils.findFriend(personOfEvent, personOfEvent.getEvent().getPersons()));
		personOfEventRepository.save(personOfEvent);
		return personOfEvent;
	}

	private PersonOfEvent findEventPerson(Long eventId, String firstname) throws NotFoundException {
		Event event = eventRepository.findOne(eventId);
		if(event == null) {
			throw new NotFoundException("The event " + eventId + " doesn't exist.");
		}
		Optional<PersonOfEvent> op = event.getPersons().stream()
				.filter(e -> e.getPerson().getName().equals(firstname))
				.findAny();
		if(!op.isPresent()) {
			throw new NotFoundException("The person " + firstname + " doesn't exist in the event.");
		}
		return op.get();
	}
}
