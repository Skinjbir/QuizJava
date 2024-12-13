package common;

import java.io.Serializable;

/**
 * The Question class represents a quiz question with multiple choice options.
 * It implements the Serializable interface to allow its instances to be serialized.
 */
public class Question implements Serializable {
    private int id;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctOption;

    /**
     * Constructs a new Question with the specified details.
     *
     * @param id the unique identifier of the question
     * @param questionText the text of the question
     * @param option1 the first option
     * @param option2 the second option
     * @param option3 the third option
     * @param option4 the fourth option
     * @param correctOption the correct option number (1-4)
     */
    public Question(int id, String questionText, String option1, String option2, String option3, String option4, int correctOption) {
        this.id = id;
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
    }

    /**
     * Returns the unique identifier of the question.
     *
     * @return the id of the question
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the text of the question.
     *
     * @return the question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Returns the first option.
     *
     * @return the first option
     */
    public String getOption1() {
        return option1;
    }

    /**
     * Returns the second option.
     *
     * @return the second option
     */
    public String getOption2() {
        return option2;
    }

    /**
     * Returns the third option.
     *
     * @return the third option
     */
    public String getOption3() {
        return option3;
    }

    /**
     * Returns the fourth option.
     *
     * @return the fourth option
     */
    public String getOption4() {
        return option4;
    }

    /**
     * Returns the correct option number.
     *
     * @return the correct option number (1-4)
     */
    public int getCorrectOption() {
        return correctOption;
    }

    /**
     * Returns an array of all options.
     *
     * @return an array containing all options
     */
    public String[] getOptions() {
        return new String[]{option1, option2, option3, option4};
    }

    /**
     * Returns a string representation of the question.
     *
     * @return a string representation of the question
     */
    @Override
    public String toString() {
        return "common.Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", correctOption=" + correctOption +
                '}';
    }
}