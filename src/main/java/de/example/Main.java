package de.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Quiz quiz = new Quiz();
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        sb.append("Willkommen bei Quizzio, dem absolut genialsten und unvergleichlich packendsten Quiz, das die Welt je gesehen hat! \n");
        sb.append("Gib deinen Spielernamen ein und das Quiz startet!\n");
        System.out.print(sb.toString());

        String user = null;

        while (user == null) {
            System.out.println("Dein Name: ");
            String userName = scanner.next();
            quiz.newUser(userName);
            user = quiz.getUser().getUsername();
        }

        int questionCounter = 0;
        System.out.println("Lass uns starten " + user + "!");
        System.out.println("Du kannst das Quiz jeder Zeit mit der Eingabe '9' beenden!\n");


        while (quiz.enoughQuestionsAvailable()) {

            System.out.println((questionCounter + 1) + ". Frage:");

            Question playQuestion = quiz.randomQuestion();
            quiz.playQuestion(playQuestion);

            System.out.print("Deine Anwort/en: ");
            String userInput = scanner.next();

            if (userInput.equals("9")) break;

            boolean isAnswerRight = quiz.checkUserInput(userInput, playQuestion);
            quiz.decideGetPoint(isAnswerRight, playQuestion);
            questionCounter++;
        }
        System.out.println(quiz.scoreBoard(questionCounter));


    }
}