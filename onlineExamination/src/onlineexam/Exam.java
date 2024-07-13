
package onlineexam;

import java.io.Serializable;
import java.util.List;

public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Question> questions;

    public Exam(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
