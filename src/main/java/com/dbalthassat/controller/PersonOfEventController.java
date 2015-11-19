package com.dbalthassat.controller;

import com.dbalthassat.dto.PersonOfEventDTO;
import com.dbalthassat.dto.PersonDTO;
import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.mapper.PersonOfEventMapper;
import com.dbalthassat.mapper.PersonMapper;
import com.dbalthassat.service.PersonOfEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events/{eventId}/persons")
public class PersonOfEventController {
	@Autowired
	private PersonOfEventService personOfEventService;

	@RequestMapping(value = "/{name}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PersonOfEventDTO findEventPerson(@PathVariable Long eventId, @PathVariable String name) throws NotFoundException {
		return PersonOfEventMapper.map(personOfEventService.find(eventId, name));
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.PUT, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public PersonDTO findFriend(@PathVariable Long eventId, @PathVariable String name) throws NotFoundException, BadRequestException {
		PersonOfEvent personOfEvent = personOfEventService.findFriend(eventId, name);
		PersonDTO person = PersonMapper.map(personOfEvent);
		PersonMapper.mapFriend(personOfEvent, person);
		return person;
	}
}
