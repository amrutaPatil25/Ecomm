package com.bank.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.bank.demo.exception.ExceptionResponse;
import com.bank.demo.exception.InsufficientBalanceException;
import com.bank.demo.exception.InvalidAccountException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder)
	{
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	@ExceptionHandler(InvalidAccountException.class)
	public ResponseEntity<?> handleInvalidAccountException(InvalidAccountException exception,WebRequest webRequest)
	{
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(exception.getMessage());
		response.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<?> 	handleInsufficientBalanceException(	InsufficientBalanceException exception,WebRequest webRequest)
	{
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(exception.getMessage());
		response.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception,WebRequest webRequest)
	{
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage(exception.getMessage());
		response.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

}
