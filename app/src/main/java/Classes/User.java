package Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    int id;
    String name;
    String email;
    String password;
    String type;

    public User(String email, String password, String  type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
