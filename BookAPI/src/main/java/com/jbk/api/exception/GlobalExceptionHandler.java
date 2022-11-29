package com.jbk.api.exception;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public HashMap<String, Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("Time", new Date());
		ex.getBindingResult().getFieldErrors().forEach(error -> {
			map.put(error.getField(), error.getDefaultMessage());
		});
		return map;
	}

	@ExceptionHandler(BookAlreadyExistException.class)
	public ResponseEntity<String> productAlreadyExistException(BookAlreadyExistException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@ExceptionHandler(BooktNotFound.class)
	public ResponseEntity<String> BooktNotFound(BooktNotFound ex) {

		String message = ex.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.OK);

	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String>NullPointerException(NullPointerException ex){
		String message = ex.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
