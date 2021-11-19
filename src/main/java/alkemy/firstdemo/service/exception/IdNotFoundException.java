package alkemy.firstdemo.service.exception;

import lombok.Data;

@Data
public class IdNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private Long idNotFound;
    private String errorCode;

    public IdNotFoundException(String errorCode,Long idNotFound) {
        this.idNotFound = idNotFound;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return "Post with id "+this.idNotFound+" not exists in database";
    }
}
