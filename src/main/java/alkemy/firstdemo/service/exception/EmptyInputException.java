package alkemy.firstdemo.service.exception;

import lombok.Data;

@Data
public class EmptyInputException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String typeInString;
    //private String errorCode;

    public EmptyInputException(String typeInString) {
        this.typeInString = typeInString;
    }

    @Override
    public String getMessage() {
        return "Any atributes for a "+this.typeInString+" are null";
    }
}
