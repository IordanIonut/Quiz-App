package Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Answer {
    int id;
    String idCourse;
    String idUser;
    String idQuestion;
    String answer;
}
