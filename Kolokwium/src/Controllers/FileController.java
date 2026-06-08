package Controllers;

import Model.Person;
import Model.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileController
{

    public static ArrayList<Player> readPlayers(String filename)
    {
        var players = new ArrayList<Player>();
        FileReader fileReader;
        List<String> lines;
        try {
            fileReader = new FileReader(new File(filename));
            lines = fileReader.readAllLines();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error while opening file: " + e.getMessage());
            return players;
        }
        catch (IOException e)
        {
            System.out.println("Error while reading the file: " + e.getMessage());
            return players;
        }

        for (var line: lines) {
            String[] data = line.split("\\s");
            players.add(new Player(data[0].concat(" " + data[1]), LocalDate.of(Integer.parseInt(data[4]), Integer.parseInt(data[3]), Integer.parseInt(data[2])),
                    data[5], Integer.parseInt(data[6]), data[7], data[8], Integer.parseInt(data[9]) ));
        }

        return players;
    }

    public static ArrayList<Person> readCoaches(String filename)
    {
        var coaches = new ArrayList<Person>();
        FileReader fileReader;
        List<String> lines;
        try {
            fileReader = new FileReader(new File(filename));
            lines = fileReader.readAllLines();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error while opening file: " + e.getMessage());
            return coaches;
        }
        catch (IOException e)
        {
            System.out.println("Error while reading the file: " + e.getMessage());
            return coaches;
        }

        for (var line: lines) {
            String[] data = line.split("\\s");
            coaches.add(new Person(data[0].concat(" " + data[1]), LocalDate.of(Integer.parseInt(data[4]), Integer.parseInt(data[3]), Integer.parseInt(data[2])),
                    data[5], Integer.parseInt(data[6]), data[7]));
        }

        return coaches;
    }

}
