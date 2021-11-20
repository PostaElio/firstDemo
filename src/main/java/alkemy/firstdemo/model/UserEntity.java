package alkemy.firstdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name="users")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @JsonIgnore
    @OneToMany(targetEntity = PostEntity.class,mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<PostEntity> posts;

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
