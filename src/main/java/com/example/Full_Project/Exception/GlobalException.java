package com.example.Full_Project.Exception;

import com.example.Full_Project.Response.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleException(MethodArgumentNotValidException ex){
        Map<String,String>errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(),error.getDefaultMessage()));
        return new ApiResponse<>(
                false,
                "Validation Failed",
                errors
        );
    }

    // ✅ ADD THIS
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?> handleRuntimeException(RuntimeException ex){
        Map<String,String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ApiResponse<>(
                false,
                ex.getMessage(),
                null
        );
    }
}
