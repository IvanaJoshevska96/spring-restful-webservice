package com.example.demo.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.ui.model.response.ErrorMessage;

@ControllerAdvice
public class HandlerExceptions extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handle(Exception ex, WebRequest req) {
		String message = ex.getLocalizedMessage();

		if (message == null)
			message = message.toString();
		ErrorMessage error = new ErrorMessage(new Date(), message);

		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(value = { NullPointerException.class })
	public ResponseEntity<Object> handlerNullException(NullPointerException ex, WebRequest req) {
		String message = ex.getLocalizedMessage();

		if (message == null)
			message = message.toString();
		ErrorMessage error = new ErrorMessage(new Date(), message);

		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
