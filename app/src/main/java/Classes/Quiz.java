package Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Quiz {
    int id;
    String idCourse;
    String idUser;
    int score;
}
