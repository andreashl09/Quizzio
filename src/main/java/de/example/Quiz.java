package de.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Quiz {
    private List<Question> questionList = new ArrayList<>();
    private HashSet<Question> playedQuestionList = new HashSet<>();
    private User user = null;

    public Quiz() {
        String description1 = "Was ist die Hauptstadt von Luxemburg?";
        String[] choices1 = {"Luxemburg", "Angola", "Berlin", "NRW"};
        String[] correctChoices1 = {"Luxemburg"};
        questionList.add(new Question(description1, choices1, correctChoices1));


    }

    public Question randomQuestion() {
        int numberOfQuestion = this.questionList.size();
        int randomNumber = (int) (Math.random() * numberOfQuestion);
        Question question = this.questionList.get(randomNumber);
        if (playedQuestionList.contains(question)) return randomQuestion();
        return question;
    }

    public Question playQuestion() {
        Question playQuestion = randomQuestion();
        StringBuilder sb = new StringBuilder();
        sb.append(playQuestion.getDescription());
        sb.append("\n");
        sb.append("Bei mehreren Anworten Zahlen durch ein Komma trennen.");
        sb.append("Deine Auswahlmöglichkeiten:\n");
        int index = 1;
        for (String s : playQuestion.getChoices()) {
            sb.append(index + ": " + s + " ");
            ++index;
        }
        System.out.println(sb.toString());
        return playQuestion;
    }

    public User getUser() {
        return user;
    }

    public User newUser(String userName) {
        if (userName.isEmpty()) return null;
        userName = userName.strip();
        return this.user = new User(userName);
    }


}
