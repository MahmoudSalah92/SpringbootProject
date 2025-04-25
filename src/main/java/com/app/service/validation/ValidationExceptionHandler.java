package com.app.service.validation;

import java.io.FileNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.app.service.dto.RequestExceptionErrors;

@RestControllerAdvice
public class ValidationExceptionHandler {

	public Map<String, Object> returnBadReqErrorTemp(RequestExceptionErrors requestExceptionErrors) {
		Map<String, Object> tempJsonData = new HashMap<String, Object>();
		tempJsonData.put("StatusDetails", requestExceptionErrors);
		return tempJsonData;
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handleExceptions(MethodArgumentTypeMismatchException exception) {
		// exception.printStackTrace();
		System.out.println("NumberFormatException");
		List<String> errors = new ArrayList<>();
		// ((Errors) exception).getAllErrors().forEach(err ->
		// errors.add(err.getDefaultMessage()));
		// errors.add(exception.getMessage());
		errors.add("Invalid Number  --> Must Insert Number Format");
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.out.println("NumberFormatException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, Object>> handleExceptions(DataIntegrityViolationException exception) {
		// exception.printStackTrace();
		System.out.println("DataIntegrityViolationException");
		List<String> errors = new ArrayList<>();
		// ((Errors) exception).getAllErrors().forEach(err ->
		// errors.add(err.getDefaultMessage()));
		// errors.add(exception.getMessage());
		errors.add("invalid SQL statement : " + exception.getMessage());
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.out.println("DataIntegrityViolationException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleExceptions(SQLIntegrityConstraintViolationException exception) {
		// exception.printStackTrace();
		System.out.println("SQLIntegrityConstraintViolationException");
		List<String> errors = new ArrayList<>();
		// ((Errors) exception).getAllErrors().forEach(err ->
		// errors.add(err.getDefaultMessage()));
		// errors.add(exception.getMessage());
		errors.add("invalid SQL statement : " + exception.getMessage());
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.out.println("SQLIntegrityConstraintViolationException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, Object>> handleExceptions(ConstraintViolationException exception) {
		// exception.printStackTrace();
		System.out.println("ConstraintViolationException");
		List<String> errors = new ArrayList<>();
		// ((Errors) exception).getAllErrors().forEach(err ->
		// errors.add(err.getDefaultMessage()));
		// errors.add(exception.getMessage());
		errors.add("invalid SQL statement : " + exception.getMessage());
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.out.println("ConstraintViolationException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnexpectedRollbackException.class)
	public ResponseEntity<Map<String, Object>> handleExceptions(UnexpectedRollbackException exception) {
		// exception.printStackTrace();
		System.out.println("UnexpectedRollbackException");
		List<String> errors = new ArrayList<>();
		// ((Errors) exception).getAllErrors().forEach(err ->
		// errors.add(err.getDefaultMessage()));
		// errors.add(exception.getMessage());
		errors.add("JTA invalid SQL statement (Failed to execute Query)");
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.out.println("UnexpectedRollbackException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadSqlGrammarException.class)
	public ResponseEntity<Map<String, Object>> handleExceptions(BadSqlGrammarException exception) {
		// exception.printStackTrace();
		System.out.println("BadSqlGrammarException");
		List<String> errors = new ArrayList<>();
		// ((Errors) exception).getAllErrors().forEach(err ->
		// errors.add(err.getDefaultMessage()));
		// errors.add(exception.getMessage());
		errors.add("invalid SQL statement : " + exception.getMessage());
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.out.println("BadSqlGrammarException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, Object>> handleExceptions(HttpMessageNotReadableException exception) {
		// exception.printStackTrace();
		System.out.println("HttpMessageNotReadableException");
		List<String> errors = new ArrayList<>();
		// ((Errors) exception).getAllErrors().forEach(err ->
		// errors.add(err.getDefaultMessage()));
		errors.add("Required request body is missing --> Must inserted all JSON parameter correctly");
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.out.println("HttpMessageNotReadableException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exception) {
		// exception.printStackTrace();
		System.out.println("MethodArgumentNotValidException");
		List<String> errors = new ArrayList<>();
		((Errors) exception).getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
		// errors.add(exception.getMessage());
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.out.println("MethodArgumentNotValidException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(
			MissingServletRequestParameterException exception) {
		// exception.printStackTrace();
		System.err.println("MissingServletRequestParameterException");
		List<String> errors = new ArrayList<>();
		errors.add(exception.getMessage());
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.err.println("MissingServletRequestParameterException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(RuntimeException exception) {
		exception.printStackTrace();
		System.err.println("RuntimeException");
		List<String> errors = new ArrayList<>();
		// ((Errors) ex).getAllErrors().forEach(err ->
		// errors.add(err.getDefaultMessage()));
		// exception.printStackTrace();
		errors.add(exception.getMessage());
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.err.println("RuntimeException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(FileNotFoundException exception) {
		// exception.printStackTrace();
		System.err.println("FileNotFoundException");
		List<String> errors = new ArrayList<>();
		// ((Errors) ex).getAllErrors().forEach(err ->
		// errors.add(err.getDefaultMessage()));
		// exception.printStackTrace();
		errors.add(exception.getMessage());
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.err.println("FileNotFoundException");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> handleExceptions(Exception exception) {
		// exception.printStackTrace();
		// exception.printStackTrace();
		// System.out.println("Exception : "+exception.getStackTrace());
		List<String> errors = new ArrayList<>();
		errors.add(exception.getMessage());
		RequestExceptionErrors error = new RequestExceptionErrors();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setStatusMessage(errors.toString());
		error.setTime(new Date());
		System.out.println("Exception");
		Map<String, Object> errorTemp = returnBadReqErrorTemp(error);
		return new ResponseEntity<>(errorTemp, HttpStatus.BAD_REQUEST);
	}
}
