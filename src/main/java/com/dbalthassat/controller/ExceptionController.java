package com.dbalthassat.controller;

import com.dbalthassat.dto.ErrorDTO;
import com.dbalthassat.exception.BadRequestException;
import com.dbalthassat.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleBadRequestException(BadRequestException e) {
		return new ErrorDTO(e.getMessage());
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDTO handleNotFoundException(NotFoundException e) {
		return new ErrorDTO(e.getMessage());
	}
}
