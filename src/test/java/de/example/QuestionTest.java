package de.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    // correct array
    String[] choices = {"Atlantischer Ozean",
            "Indischer Ozean",
            "Arktischer Ozean",
            "Pazifischer Ozean"};

    //failed array
    String[] choices1 = {"e"};

    // correct answer
    String[] correctChoices = {"Pazifischer Ozean"};

    // failed answer
    String[] incorrectChoices = {"Alantic"};
    Question q = new Question("Welcher Ozean ist der größte der Welt?", choices, correctChoices);



    @Test
    @DisplayName("Should throw an error because the '?' is missing at the end of the sentence")
    void missingQuestionMark(){
        assertThrows(IllegalArgumentException.class, () -> q.setDescription("Welcher Ozean ist der größte der Welt"));
    }

    @Test
    @DisplayName("answer must not have less than 2 characters")
    void answerHasNotTwoChar(){
        assertThrows(IllegalArgumentException.class, () -> q.setChoices(choices1));
    }

    @Test
    @DisplayName("Should throw an error if not the correct answer in the list of choices")
    void hasUnknownChoice(){
        assertThrows(IllegalArgumentException.class, () -> q.setCorrectChoices(incorrectChoices));
    }

}