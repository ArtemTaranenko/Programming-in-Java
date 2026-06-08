package app;

import Controllers.FileController;
import Model.Person;
import Model.Player;
import Model.Team;

import java.io.File;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args)
    {
        var players = FileController.readPlayers("players.txt");
        var coaches = FileController.readCoaches("coachs.txt");
        //-----------Zadanie 1-----------
        System.out.println("1) Lista zawodników:");

        for (var player : players){
            System.out.println(player.toString());
        }

        //-----------Zadanie 2-----------
        System.out.println("2) Aktualizacja dokonań napastników z 1-ej ligi:");

        for (var player : players){
            player.updateGoalsForFirstLeague(1);
        }
        //-----------Zadanie 3-----------
        System.out.println("3) Uporządkowana alfabetycznie lista trenerów:");

        coaches.stream()
                .sorted(Comparator.comparing(Person::getName))
                .forEach(System.out::println);

        //-----------Zadanie 4-----------
        System.out.println("4) Klasyfikacja 3-ech najlepszych strzelców 1-ej ligi:");

        players.stream()
                .filter(x-> x.getTeam().getLeague() == 1)
                .sorted(Comparator.comparing(Player::getN_goals).reversed())
                .limit(3)
                .forEach(System.out::println);

        //-----------Zadanie 5-----------
        System.out.println("5) Uporządkowana lista klubów rywalizujących w podanej lidze:");

        System.out.println("Podaj ligę: ");
        Scanner scan = new Scanner(System.in);
        int league = scan.nextInt();

        var teams = coaches.stream()
                .map(Person::getTeam)
                .toList();
        teams.stream()
                .filter(x-> x.getLeague() == league)
                .sorted(Comparator.comparing(Team::getName))
                .forEach(System.out::println);

        //-----------Zadanie 6-----------
        System.out.println("6) Dane poszczególnych klubów:");

        var allPersons = Stream.concat(
                players.stream(),
                coaches.stream()
        );

        allPersons.collect(Collectors.groupingBy(Person::getTeam))
                .forEach((team, people) -> {
                    // 1. Считаем количество ТОЛЬКО игроков
                    long playerCount = people.stream()
                            .filter(p -> p instanceof Player)
                            .count();

                    // 2. Считаем сумму голов игроков
                    int totalGoals = people.stream()
                            .filter(p -> p instanceof Player)
                            .mapToInt(p -> ((Player) p).getN_goals())
                            .sum();

                    // Выводим заголовок команды (обратите внимание на двоеточие в конце)
                    System.out.println("-> Team: " + team.getName()
                            + " league: " + team.getLeague()
                            + ". Liczba zawodników: " + playerCount
                            + ". Liczba bramek " + totalGoals + ":");

                    // 3. Выводим сначала тренеров
                    people.stream()
                            .filter(p -> !(p instanceof Player)) // или p instanceof Coach
                            .forEach(System.out::println);

                    // 4. Затем выводим игроков
                    people.stream()
                            .filter(p -> p instanceof Player)
                            .forEach(System.out::println);
                });
    }
}