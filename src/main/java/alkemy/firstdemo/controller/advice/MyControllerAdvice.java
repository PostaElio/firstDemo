package alkemy.firstdemo.controller.advice;

import alkemy.firstdemo.controller.dto.ApiResponse;
import alkemy.firstdemo.service.exception.EmptyInputException;
import alkemy.firstdemo.service.exception.ExistsByEmailException;
import alkemy.firstdemo.service.exception.IdNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleBadCredentials(BadCredentialsException badCredentialsException){
        return new ResponseEntity<>(new ApiResponse(false,badCredentialsException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistsByEmailException.class)
    public ResponseEntity<ApiResponse> handleExistsByEmail(ExistsByEmailException existsWithEmailException) {
        return new ResponseEntity<>(new ApiResponse(false, existsWithEmailException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<ApiResponse> handleEmptyInput(EmptyInputException emptyInputException){
        return new ResponseEntity<>(new ApiResponse(false, emptyInputException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ApiResponse> handleIdNotFound(IdNotFoundException idNotFoundException){
        return new ResponseEntity<>(new ApiResponse(false, idNotFoundException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiResponse> handleSignature(SignatureException signatureException){
        return new ResponseEntity<>(new ApiResponse(false,signatureException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse> handleExpiredJwt(ExpiredJwtException expiredJwtException){
        return new ResponseEntity<>(new ApiResponse(false,expiredJwtException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleNullPointerException(NullPointerException nullPointerException){
        return new ResponseEntity<>(new ApiResponse(false,"Any atributes are not exists for the object"), HttpStatus.BAD_REQUEST);
    }
}
