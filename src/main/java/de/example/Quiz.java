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

        String description2 = "Welches Land hat die größte Bevölkerung?";
        String[] choices2 = {"China", "Indien", "USA", "Brasilien"};
        String[] correctChoices2 = {"China"};
        questionList.add(new Question(description2, choices2, correctChoices2));

        String description3 = "Welches dieser Länder liegen in Asien?";
        String[] choices3 = {"China", "Indien", "USA", "Brasilien"};
        String[] correctChoices3 = {"China", "Indien"};
        questionList.add(new Question(description3, choices3, correctChoices3));

        String description4 = "Welches Land hat die größte Fläche?";
        String[] choices4 = {"Russland", "Kanada", "China", "USA"};
        String[] correctChoices4 = {"Russland"};
        questionList.add(new Question(description4, choices4, correctChoices4));

        String description5 = "Welches dieser Länder gehören zu den G7?";
        String[] choices5 = {"Deutschland", "China", "Italien", "Brasilien"};
        String[] correctChoices5 = {"Deutschland", "Italien"};
        questionList.add(new Question(description5, choices5, correctChoices5));

        String description6 = "Welches dieser Länder haben Euro als Währung?";
        String[] choices6 = {"Deutschland", "Frankreich", "Schweden", "Polen"};
        String[] correctChoices6 = {"Deutschland", "Frankreich"};
        questionList.add(new Question(description6, choices6, correctChoices6));


    }

    public Question randomQuestion() {
        int numberOfQuestion = this.questionList.size();
        int randomNumber = (int) (Math.random() * numberOfQuestion);
        Question question = this.questionList.get(randomNumber);
        if (playedQuestionList.contains(question)) return randomQuestion();
        return question;
    }

    public void playQuestion(Question question) {
        StringBuilder sb = new StringBuilder();
        sb.append("Bei mehreren Anworten Zahlen durch ein Komma trennen.\n");
        sb.append(question.getDescription());
        sb.append("\n");
        sb.append("Deine Auswahlmöglichkeiten:\n");
        int index = 1;
        for (String s : question.getChoices()) {
            sb.append(index + ": " + s + " ");
            ++index;
        }
        System.out.println(sb.toString());
    }

    public User getUser() {
        return user;
    }

    public void newUser(String userName) {
        if (userName.isEmpty()) return;
        userName = userName.strip();
        this.user = new User(userName);
    }

    public boolean checkUserInput(String input, Question question) {
        input = input.strip().replace(" ", "");
        String[] inputString = input.split(",");
        String[] choices = question.getChoices();

        if (inputString.length != question.getCorrectChoices().length) {
            return false;
        }

        for (String s : inputString) {
            int index = Integer.parseInt(s);
            if (index > 0 && index <= choices.length - 1){
                if(!List.of(question.getCorrectChoices()).contains(choices[index-1])) return false;
            }
        }
        return true;
    }

    public void decideGetPoint(boolean isAnswerRight, Question question) {
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

    public String scoreBoard(int questionCounter){
        StringBuilder sb = new StringBuilder();
        sb.append("Dein Spiel ist zu Ende, " + this.user.getUsername() + "!\n");
        int userScore = user.getScore();
        if(userScore <= 0 ){
            sb.append("Du hast keine Punkte bekommen!");
        }else if (userScore == questionCounter){
            sb.append("Du bist der Burner...!🤩 Alle Frage richtig beantwortet!");
        }else {
            sb.append("Du hast von " + questionCounter + " Fragen " + this.user.getScore() + " richtig beantwortet!");
        }
        return sb.toString();
    }


}



