package alkemy.firstdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String category;
    @Temporal(value = TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private Date creationdate;
    @ManyToOne
    private UserEntity author;

    public PostEntity(String title, String content, String image, String category, Date creationdate, UserEntity author) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.category = category;
        this.creationdate = creationdate;
        this.author = author;
    }

}
