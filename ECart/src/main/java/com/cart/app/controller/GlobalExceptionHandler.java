package com.cart.app.controller;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.cart.app.exception.ExceptionResponse;
import com.cart.app.exception.TransactionFailedException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@InitBinder
	public void initBinder(WebDataBinder dataBinder)
	{
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<?> handleInvalidFormatException(InvalidFormatException exception,WebRequest webRequest)
	{
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage("Enter only integer value");
		response.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(JsonParseException.class)
	public ResponseEntity<?> handleJsonParseException(JsonParseException exception,WebRequest webRequest)
	{
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage("Enter only integer value");
		response.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleGlobalException(RuntimeException exception,WebRequest webRequest)
	{
		
		Optional<Throwable> rootCause = Stream.iterate(exception, Throwable::getCause)
                .filter(element -> element.getCause() == null)
                .findFirst();

		Throwable t = rootCause.get();
		
		
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(t.getMessage());
		response.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TransactionFailedException.class)
	public ResponseEntity<?> handleTransactionFailedException(TransactionFailedException exception,WebRequest webRequest)
	{
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(exception.getMessage());
		response.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	
}
