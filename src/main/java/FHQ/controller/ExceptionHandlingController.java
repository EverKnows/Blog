package FHQ.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleException(Exception ex, HttpServletRequest request, Model model) {
        model.addAttribute("Message", ex.getMessage());
        model.addAttribute("Url", request.getRequestURL());
        return "error/404";
    }

    @ExceptionHandler(Exception.class)
    public String handException2(Exception ex, HttpServletRequest request, Model model) {
        return "error/404";
    }
}
