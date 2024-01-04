package com.example.loanpayments.ExceptionHandler;

import com.example.loanpayments.DTO.LoanDTO;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Field;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;



@ControllerAdvice
public class RestExceptionHandler {

    Logger logger = org.slf4j.LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleValidationExceptions(Exception ex, WebRequest request) {
        // Get field errors from the exception
        StringBuilder errorMessage = new StringBuilder("Validation errors occurred for fields/values: \n");

        if (ex instanceof MethodArgumentNotValidException) {

            ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach(error -> errorMessage.append(error.getField()).append(" ("+error.getDefaultMessage()+")").append(",\n"));

            errorMessage.delete(errorMessage.length() - 2, errorMessage.length()); // Remove the last comma and space

            // Log the error message
             logger.error("Error Message: "+ errorMessage);


        } else if (ex instanceof HttpMessageNotReadableException) {
            // Regular expression pattern to match "[SIMPLE, COMPOUND]" and content within double quotes
            String regex = "`.*\\.(\\w+)`.*\"(.*?)\".*\\[(.*?)\\]" ;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(ex.getMessage());
            //logger.info("Here is the message: " + //matcher.find());
            // Find and extract matches
            if (matcher.find()) {
                //enum error

                    logger.info("Found a match: " + matcher.group(0)); // The entire match
                    String enumName = matcher.group(1);
                    String enumValues = matcher.group(2);
                    String contentWithinQuotes = matcher.group(3);
                    logger.error(enumName+" = " + enumValues+ " but it should be one of the following: "+contentWithinQuotes);
                    //logger.info("Content within quotes: " + contentWithinQuotes);
                    errorMessage.append(enumName+" = " + enumValues+ " but it should be one of the following: "+ contentWithinQuotes);

            }else {
                //type error

                String reg="`(.*?)`.*(\".*\")" ;
                Pattern pat = Pattern.compile(reg);
                Matcher mat = pat.matcher(ex.getMessage());

                if (mat.find()) {


                    logger.info("Found a match: " + mat.group(0)); // The entire match
                    String classFieldError =  mat.group(1); // Extract class which filed error belongs to
                    logger.info("classFieldError = " + classFieldError);
                    String fieldValue = mat.group(2); // Extract content within double quotes


                    Class<?> loanDTOClass = LoanDTO.class;

                    List<String> probableErrorFields = Stream.of(loanDTOClass.getDeclaredFields()).
                            filter(field -> field.getType().toString().replace("class ","").equals(classFieldError))
                            .map(Field::getName).toList();
                    String errorMessageupdated = "Malformed JSON request/field values please check : "+probableErrorFields+ " one = "+fieldValue;
                    logger.info("probableErrorFields = " + probableErrorFields);
                    logger.error("Malformed JSON request/field values please check :\n {} one = "+fieldValue,probableErrorFields);

                    errorMessage.append(errorMessageupdated);
                }


                logger.error(String.valueOf(ex.getClass()));

            }
        }

        //addd more exceptions here

        return ResponseEntity.badRequest().body(errorMessage.toString());
    }



}
