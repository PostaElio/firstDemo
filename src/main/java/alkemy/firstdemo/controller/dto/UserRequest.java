package alkemy.firstdemo.controller.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
}
