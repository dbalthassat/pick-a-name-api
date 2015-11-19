package com.dbalthassat.controller;

import com.dbalthassat.dto.EventDTO;
import com.dbalthassat.dto.groups.Create;
import com.dbalthassat.entity.Event;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.exception.NotFoundException;
import com.dbalthassat.mapper.EventMapper;
import com.dbalthassat.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
	@Autowired
	private EventService eventService;

    @SuppressWarnings("MVCPathVariableInspection")
    @RequestMapping(value = {"", "/", "/{id}"},
            method = RequestMethod.POST, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public EventDTO createEvent(@Validated(Create.class) @RequestBody EventDTO event, BindingResult result) throws BadRequestException, NotFoundException {
        if(result.hasErrors()) {
            throw new BadRequestException(result.getAllErrors().toString());
        }
        Event entity = eventService.create(EventMapper.map(event));
        EventDTO dto = EventMapper.map(entity);
        return EventMapper.mapPersons(entity, dto);
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public EventDTO findEvent(@PathVariable Long id) throws NotFoundException {
		return EventMapper.map(eventService.find(id));
	}
}
