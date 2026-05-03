package Controllers;

import Model.*;

import javax.swing.text.html.StyleSheet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        for (int i = 0; i < 5; i++) {
            player1.addCard(deck.getDeck().removeFirst());
            player2.addCard(deck.getDeck().removeFirst());
        }
        player1.printCards();
        player2.printCards();
        exchangeCard(player1, deck);
        exchangeCard(player2, deck);
        handleWin(player1, player2);
    }

    private void handleWin(Player player1, Player player2)
    {
        CardController cardController = new CardController();
        WinSituation win = cardController.andTheWinnerIs(player1, player2);
        Player winner;
        switch (win.getWin())
        {
            case PLAYER1:
                winner = player1;
                printWinner(winner);
                break;
            case PLAYER2:
                winner = player2;
                printWinner(winner);
                break;
            case DRAW:
                System.out.println("DRAW!");
        }
        printResults(cardController, player1, player2);
    }

    private void exchangeCard(Player player, Deck deck)
    {
        System.out.println("Move " + player.getName());
        System.out.println("Do you want to try your luck and exchange cards? Please, type in number of cards you want to exchange: ");
        Scanner scanner = new Scanner(System.in);
        int numberCards = scanner.nextInt();
        if (numberCards != 0)
        {
            System.out.println("Please, indicate cards to be exchanged (from the left");
            List<Integer> cardsIndexes = new ArrayList<>();
            List<Card> cards = new ArrayList<>(player.getCards());
            for (int i = 0; i < numberCards; i++) {
                int index = scanner.nextInt();
                cardsIndexes.add(index);
                if (index < 1 || index > 5) {
                    System.out.println("Invalid index");
                    i--;
                }
            }
            for (int i : cardsIndexes)
            {
                Card oldCard = cards.get(i-1);
                player.getCards().remove(oldCard);
            }
            System.out.println("Left " + player.getName() + " cards " + player.getCards());
            for (int i = 0; i<cardsIndexes.size(); i++) {
                player.addCard(deck.getDeck().removeFirst());
            }
            System.out.println(player.getName() + " cards after exchanging " + player.getCards());
        }

    }

    private void printWinner(Player winner)
    {
        System.out.println("The winner is: "+ winner.getName());
    }

    private void printResults(CardController cardController, Player player1, Player player2)
    {
        System.out.println("\n"+ player1.getName() + " result is : " + cardController.result(player1) + " " + player1.getCards());
        System.out.println("\n"+ player2.getName() + " result is : " + cardController.result(player2) + " " + player2.getCards());
    }

    private void playSecondScenario(Deck deck, Player player1, Player player2)
    {
        dealCards(deck, player1);
        dealCards(deck, player2);
        handleWin(player1, player2);

    }

    private void dealCards(Deck deck, Player player) {
        System.out.println("Please, type card indexes that " + player.getName() + " will get");
        Scanner scanner = new Scanner(System.in);

        List<Card> chosen = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int index = scanner.nextInt();
            if (index < 1 || index > deck.getDeck().size()) {
                System.out.println("Invalid index");
                i--;
                continue;
            }
            indexes.add(index - 1);
        }

        for (int i : indexes)
            chosen.add(deck.getDeck().get(i));

        for (var card : chosen) {
            player.addCard(card);
            deck.getDeck().remove(card);
        }

        System.out.println("Left cards in the deck");
        deck.printDeck();
    }
}

