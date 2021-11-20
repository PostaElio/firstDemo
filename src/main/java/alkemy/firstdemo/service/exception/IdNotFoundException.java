package alkemy.firstdemo.service.exception;

import lombok.Data;

@Data
public class IdNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private Long idNotFound;
    private String errorCode;
    private String typeInString;

    public IdNotFoundException(String errorCode,Long idNotFound,String typeInString) {
        this.idNotFound = idNotFound;
        this.errorCode = errorCode;
        this.typeInString = typeInString;
    }

    @Override
    public String getMessage() {
        return this.typeInString+" with id "+this.idNotFound+" not exists in database";
    }
}
