package Controllers;

import Model.Deck;
import Model.Player;

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
        System.out.println("\nPlaying deck after shuffling");
        gameDeck.printDeck();
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        switch (choice) {
            case 1:
                playFirstScenario(gameDeck, player1, player2);
                break;
            case 2:
                playSecondScenario(gameDeck, player1, player2);
            default:
                System.out.println("Error while processing scenario");
        }
    }

    private void playFirstScenario(Deck deck, Player player1, Player player2)
    {
        for (int i = 0; i<10; i+=2)
        {
            player1.addCard(deck.getDeck().get(i));
            player2.addCard(deck.getDeck().get(i+1));
        }
        player1.printCards();
        player2.printCards();
    }

    private void playSecondScenario(Deck deck, Player player1, Player player2)
    {

    }
}
