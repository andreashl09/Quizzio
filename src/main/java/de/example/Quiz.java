package de.example;

import java.util.ArrayList;
import java.util.Arrays;
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

        String description2 = "Welches Land hat die größte Bevölkerung?";
        String[] choices2 = {"China", "Indien", "USA", "Brasilien"};
        String[] correctChoices2 = {"China"};
        questionList.add(new Question(description2, choices2, correctChoices2));

        String description3 = "Welches dieser Länder liegen in Asien?";
        String[] choices3 = {"China", "Indien", "USA", "Brasilien"};
        String[] correctChoices3 = {"China", "Indien"};
        questionList.add(new Question(description3, choices3, correctChoices3));

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
        sb.append("Bei mehreren Anworten Zahlen durch ein Komma trennen.\n");
        sb.append(playQuestion.getDescription());
        sb.append("\n");
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

    private boolean checkUserInput(String input, Question question) {
        input = input.strip().replace(" ", "");
        String[] inputString = input.split(",");
        String[] choices = question.getChoices();
        List<String> correctChoice = new ArrayList<>(Arrays.asList(question.getCorrectChoices()));
        if (inputString.length > question.getCorrectChoices().length || inputString.length < question.getCorrectChoices().length) {
            return false;
        }

        List<String> userAnswer = new ArrayList<>();
        for (String s : inputString) {
            if (Integer.parseInt(s) > 0 && Integer.parseInt(s) <= choices.length - 1) {
                userAnswer.add(choices[Integer.parseInt(s) - 1]);
            } else {
                return false;
            }
        }

        for (int i = 0; i < userAnswer.size(); i++) {
            boolean isRight = correctChoice.contains(userAnswer.get(i));
            if (!isRight) return false;
        }
        return true;
    }

    public void decideGetPoint(String input, Question question) {
        boolean isAnswerRight = checkUserInput(input, question);
        if (isAnswerRight) {
            this.user.incrementScore(1);
            System.out.println("Deine Anwort war richtig! Weiter geht's....\n");
        }else{
            System.out.println("Deine Anwort ist leider falsch! Mache weiter, vielleicht hast du mal Glück!\n");
        }
        playedQuestionList.add(question);

    }

    public boolean enoughQuestionsAvailable() {
        return (questionList.size() > playedQuestionList.size());

    }

    public String scoreBoard(){
        StringBuilder sb = new StringBuilder();
        sb.append("Dein Spiel ist zu Ende, " + this.user.getUsername() + "!\n");
        int userScore = user.getScore();
        if(userScore <= 0 ){
            sb.append("Du hast keine Punkte bekommen!");
        }else if (userScore == questionList.size()){
            sb.append("Du bist der Burner...!🤩 Alle Frage richtig beantwortet!");
        }else {
            sb.append("Du hast von " + questionList.size() + " Fragen " + this.user.getScore() + " richtig beantwortet!");
        }
        return sb.toString();
    }


}



