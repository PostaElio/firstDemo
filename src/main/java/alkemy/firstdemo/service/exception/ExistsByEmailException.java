package alkemy.firstdemo.service.exception;

import lombok.Data;

@Data
public class ExistsByEmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String email;
    // private String errorCode;

    public ExistsByEmailException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "Email: "+this.email+" is already taken";
    }
}
