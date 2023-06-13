//package com.codegym.c11.controller;
//
//import com.codegym.c11.aspect.AccountAspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import javax.servlet.http.HttpServletRequest;
//import java.sql.SQLException;
//
//@Controller
//public class ExceptionHandlingController {
//
//    private Logger logger = LoggerFactory.getLogger(AccountAspect.class);
//
//    @ResponseStatus(value= HttpStatus.CONFLICT,
//            reason="Data integrity violation")  // 409
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public void conflict() {
//        // Nothing to do
//    }
//
//    // Specify name of a specific view that will be used to display the error:
//    @ExceptionHandler({SQLException.class, DataAccessException.class})
//    public String databaseError() {
//        // Nothing to do.  Returns the logical view name of an error page, passed
//        // to the view-resolver(s) in usual way.
//        // Note that the exception is NOT available to this view (it is not added
//        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
//        // below.
//        return "databaseError";
//    }
//
//    // Total control - setup a model and return the view name yourself. Or
//    // consider subclassing ExceptionHandlerExceptionResolver (see below).
//    @ExceptionHandler(Exception.class)
//    public void handleError(HttpServletRequest req, Exception ex) {
//        logger.error("Request: " + req.getRequestURL() + " raised " + ex);
//    }
//}
