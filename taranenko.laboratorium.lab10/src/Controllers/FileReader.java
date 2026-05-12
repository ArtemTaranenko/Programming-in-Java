package Controllers;

import Model.Fishing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileReader
{
    private String filename;

    public FileReader(String filename)
    {
        this.filename = filename;
    }

    public List<Fishing> readFile()
    {
        Path filePath = Paths.get(filename);
        List<String> lines = new ArrayList<>();
        try{
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Fishing> fishingData = new ArrayList<>();
        for (var line: lines) {
            String[] data = line.split(";");
            fishingData.add(new Fishing(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]), data[3],
                    data[4], Integer.parseInt(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7])));
        }
        return fishingData;
    }
}
