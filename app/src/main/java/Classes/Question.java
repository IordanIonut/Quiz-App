package Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Question {
    int id;
    int id_course;
    String question;
    int answer;
    String first_col;
    String second_col;
    String third_col;
    String fourth_col;
}
