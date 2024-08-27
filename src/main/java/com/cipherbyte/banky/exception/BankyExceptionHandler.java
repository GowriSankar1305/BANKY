package com.cipherbyte.banky.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BankyExceptionHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Map<String, List<String>> handleValidationErrors(MethodArgumentNotValidException e)	{
		Map<String, List<String>> errMap = new HashMap<>();
		List<String> errosList = new ArrayList<>();
		e.getFieldErrors().forEach(err -> {
			errosList.add(err.getDefaultMessage());
		});
		errMap.put("errorMsg", errosList);
		return errMap;
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public Map<String, String> handleError(Exception e)	{
		Map<String, String> errMap = new HashMap<>();
		errMap.put("errorMsg", e.getMessage());
		return errMap;
	}
}
