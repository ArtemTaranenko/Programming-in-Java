package Controller;

import Model.Closest;
import Model.GameSettings;
import Model.Board;
import Model.Point;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class GameController implements GameSettings
{
    private List<Point> scores = new ArrayList<>();
    @Override
    public void printGameSetting() {
        System.out.println("Number of games:" + game_numbers + ", Basic level: " + consolation_points);
        for (int i = 0; i<5; i++){
            System.out.println("Round: " + (i+1) + ": " + points.get(i) + " points");
        }
    }

    public void startGame()
    {
        for(int i = 0; i < game_numbers; i++) {
           start_single_game( i+1);
        }
        printGameResult();
        saveBestResult(calculateTotalPoints());
    }

    private void start_single_game(int game_number)
    {
        System.out.println("Game number " + game_number);
        int x_searched = (int)(Math.random() * 8 +1);
        int y_searched = (int)(Math.random() * 8 +1);
        Board board = new Board(x_searched, y_searched);
        Point score = new Point();
        for (int j = 0; j < round_numbers; j++){
           boolean finished = play_single_round(j, board, score);
           if (finished){
               break;
           }
        }
        scores.add(score);
    }

    private boolean play_single_round(int j, Board board, Point score)
    {
        System.out.println("Round " + (j+1));
        read_and_set_points(board);
        var result = board.calculate_distance();
        board.printDebugState();
        if (result.getValue() == 0) {
            handleWin(score, result, j);
            return true;
        }
        else if (j == 4){
            handleLoss(board, score);
            return true;
        }
        score.calculate_score(consolation_points, result.getValue());
        printDistanceInfo(board, result);
        return false;
    }

    private void read_and_set_points(Board board)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter coordinates for point A: ");
        int A_x = readCoordinate(scanner);
        int A_y = readCoordinate(scanner);
        board.setA_point(A_x, A_y);
        System.out.println("Enter coordinates for point B: ");
        int B_x = readCoordinate(scanner);
        int B_y = readCoordinate(scanner);
        board.setB_point(B_x, B_y);
    }

    private int readCoordinate(Scanner scanner) {
        int value;

        while (true) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();

                if (value >= 1 && value <= 8) {
                    return value;
                }
            } else {
                scanner.next();
            }

            System.out.println("I am sorry, invalid input. Please, try again. Coordinates should be in range 1-8");
        }
    }
    private void handleWin(Point score, java.util.Map.Entry<Closest, Integer> result, int j)
    {
        System.out.println("Point " + result.getKey() + " was the searched point!");
        score.setNum_rounds(j+1);
        score.Add_Score(points.get(j));
        score.print_ending_score();

    }

    private void handleLoss(Board board, Point score)
    {
        System.out.println("Unfortunately, we have to inform you that you did not manage to guess the right point, " +
                "but it was a descent try from you! \nOur team wishes you good luck next time!" + " You have scored " + score.getScore() + " points"
                + "\nThe searched point was X(" + board.getSearched_point_x() + ", " + board.getSearched_point_y() + ").");
    }

    private void printDistanceInfo(Board board, java.util.Map.Entry<Closest, Integer> result)
    {
        if (result.getKey() == Closest.EQUAL) {
            System.out.println("Both points are at the same distance, distance = " + result.getValue());
        } else {
            int x = result.getKey() == Closest.A ? board.getA_x() : board.getB_x();
            int y = result.getKey() == Closest.A ? board.getA_y() : board.getB_y();

            System.out.println(
                    "The closest point is point " + result.getKey() +
                            " (" + x + ", " + y + "), distance " + result.getValue()
            );
        }
    }

    private int calculateTotalPoints()
    {
        int sum = 0;
        for (Point score: scores){
            sum += score.getScore();
        }
        return sum;
    }

    private void printGameResult()
    {
        System.out.println("#### SUMMARY ####\n");
        for (int i = 0; i<game_numbers; i++)
        {
            System.out.println("Game number " + (i+1) + ", Scored points: " + scores.get(i).getScore() + " Rounds: " + scores.get(i).getNum_rounds() + "\n");
        }
        System.out.println("Total scored points: " + calculateTotalPoints());
    }

    private void saveBestResult(int totalScore) {
        String fileName = "best_result.txt";

        try {
            int bestScore = readBestScore(fileName);

            if (totalScore > bestScore) {
                FileWriter writer = new FileWriter(fileName, false);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
                String record = "Best score: " + totalScore +
                        ", Date: " + LocalDateTime.now().format(formatter);

                writer.write(record);
                writer.close();

                System.out.println("Congratulations, you have beaten the best result! You are a very strong player. New best result saved!");
            }

        } catch (IOException e) {
            System.out.println("Error while saving best result: " + e.getMessage());
        }
    }
    private int readBestScore(String fileName) {
        File file = new File(fileName);

        if (!file.exists() || file.length() == 0) {
            return 0;
        }

        try (Scanner scanner = new Scanner(file)) {

            if (!scanner.hasNextLine()) {
                return 0;
            }

            String line = scanner.nextLine();

            String scorePart = line.split(",")[0];
            return Integer.parseInt(scorePart.replaceAll("\\D+", ""));

        } catch (Exception e) {
            return 0;
        }
    }
}
