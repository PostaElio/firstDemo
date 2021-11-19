package alkemy.firstdemo.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostCompactResponse {
    private Long id;
    private String title;
    private String image;
    private Date creationdate;
}
