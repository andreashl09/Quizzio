package de.example;

// User
// Quiz - Geographie Quiz // questions[]

// Question - description: "Was ist die Hauptstadt von Luxemburg?"
// choices[] = { "Luxemburg", "Angola", "Berlin", "NRW" }
// correctChoices[] = { "Luxemburg" }
// AnswerTry - chosen[] = { "Luxemburg" }

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Question {
    private String description;
    private String[] choices;
    private String[] correctChoices;

    public Question(String description, String[] choices, String[] correctChoices) {
        this.setDescription(description);
        this.setChoices(choices);
        this.setCorrectChoices(correctChoices);
    }

    public boolean answerQuestion(String[] chosen) {
        if(chosen.length != correctChoices.length) {
            return false;
        }
        for(String correctChoice : this.correctChoices) {
            if(!List.of(chosen).contains(correctChoice)) {
                return false;
            }
        }
        return true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(!description.endsWith("?")) {
            throw new IllegalArgumentException("Question description invalid");
        }
        this.description = description;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        if(choices.length < 2) {
            throw new IllegalArgumentException("At least two choices must be provided");
        }
        this.choices = choices;
    }

    public String[] getCorrectChoices() {
        return correctChoices;
    }

    public void setCorrectChoices(String[] correctChoices) {
        if(correctChoices.length < 1 || hasUnknownChoice(this.choices, correctChoices)) {
            throw new IllegalArgumentException("Correct choices must be a subset of choices");
        }
        this.correctChoices = correctChoices;
    }

    private boolean hasUnknownChoice(String[] choices, String[] correctChoices) {
        for(String correctChoice : correctChoices) {
            if(!List.of(choices).contains(correctChoice)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(getDescription(), question.getDescription()) && Objects.deepEquals(getChoices(), question.getChoices()) && Objects.deepEquals(getCorrectChoices(), question.getCorrectChoices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), Arrays.hashCode(getChoices()), Arrays.hashCode(getCorrectChoices()));
    }
}
