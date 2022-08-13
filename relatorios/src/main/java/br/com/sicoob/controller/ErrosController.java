package br.com.sicoob.controller;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sicoob.model.ErrorMessage;

	@ControllerAdvice
	public class ErrosController extends ResponseEntityExceptionHandler{
		
		@ExceptionHandler(value= {Exception.class})
		public String handleAnyException(Exception e, WebRequest request, Model model){
			ErrorMessage errorMessage = new ErrorMessage(new Date(), e.getLocalizedMessage(), "500");
			model.addAttribute("erro", errorMessage);
			return "pagina-erro";
		}
		
	}
