package Controllers;

import Model.Deck;

import java.util.Scanner;

public class GameController
{

    public void startGame()
    {
        int choice = choose_scenario();
        playGame(choice);
    }

    private int choose_scenario()
    {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("Choose one scenario: \n1. Game of \"exposed cards\" \n2. Directed diagnostic scenario");
            if(scanner.hasNextInt()){
                int value = scanner.nextInt();

                if (value == 1 || value == 2){
                    return value;
                }
            }
            else{
                scanner.next();
            }
            System.out.println("I am sorry, invalid input. Please choose one of the scenarios and type 1 or 2");
        }
    }

    private void playGame(int choice)
    {
        Deck gameDeck = new Deck();
        System.out.println("Playing deck: ");
        gameDeck.printDeck();
        gameDeck.shuffling();
        System.out.println("Playing deck after shuffling");
        gameDeck.printDeck();

        switch (choice) {
            case 1:
                playFirstScenario(gameDeck);
                break;
            case 2:
                playSecondScenario(gameDeck);
            default:
                System.out.println("Error while processing scenario");
        }
    }

    private void playFirstScenario(Deck deck)
    {

    }

    private void playSecondScenario(Deck deck)
    {

    }
}
