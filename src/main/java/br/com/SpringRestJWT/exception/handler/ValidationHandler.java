package br.com.SpringRestJWT.exception.handler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.context.request.WebRequest;

import br.com.SpringRestJWT.exception.BadRequestException;
import br.com.SpringRestJWT.exception.dto.ErroFormDto;
import br.com.SpringRestJWT.exception.dto.ExceptionResponseDto;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public List<ErroFormDto> handle(MethodArgumentNotValidException exception) {
		List<ErroFormDto> dto = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String msg = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroFormDto erro = new ErroFormDto(e.getField(), msg);
			dto.add(erro);
		});
		return dto;
	}
	
	@ExceptionHandler({BadRequestException.class , BadRequest.class})
	public final ResponseEntity<ExceptionResponseDto> handleAllExceptionsNotFound(Exception ex, WebRequest request) {
		ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(Calendar.getInstance().getTime() , ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServiceException.class)
	public final ResponseEntity<ExceptionResponseDto> handleAllExceptionsServer(Exception ex, WebRequest request) {
		ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(new Date() , ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
