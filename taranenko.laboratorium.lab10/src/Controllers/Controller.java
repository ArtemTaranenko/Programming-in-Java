package Controllers;

import Model.Fishing;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller
{

    public void processData(String filename)
    {
        FileReader fileReader = new FileReader(filename);
        List<Fishing> fishingList = fileReader.readFile();
        printData(fishingList);

        //Fishing data sorted by fisher and date
        System.out.println("\nFishing data sorted by fisher and date:\n");
        fishingList.stream().
                sorted(Comparator.comparing(Fishing::getId)
                    .thenComparing(Fishing::getDate))
                .forEach(System.out::println);

        //Unique fish names sorted alphabetically
        System.out.println("\nUnique fish names sorted alphabetically:\n");
        fishingList.stream().map(Fishing::getSpecies)
                .distinct()
                .sorted()
                .forEach(System.out::println);

        //Data about catching 1-2 kg pike sorted from the most recent catch
        System.out.println("\nInformation about catching 1-2 kg pike sorted from the most recent catch:\n");
        fishingList.stream()
                .filter(fish -> fish.getSpecies().equals("szczupak") && fish.getWeight() >= 1 && fish.getWeight() <= 2)
                .sorted(Comparator.comparing(Fishing::getDate).reversed())
                .forEach(System.out::println);

        //Information about fishing on each day of the week
        System.out.println("\nInformation about fishing on each day of the week\n");
        fishingList.stream()
                .sorted(Comparator.comparing(Fishing::getDay)
                        .thenComparing(Fishing::getId))
                .forEach(System.out::println);

        //Information about how many fishes longer than 50 cm have been caught by each fisher
        System.out.println("\nInformation about how many fishes longer than 50 cm have been caught by each fisher\n");
        fishingList.stream()
                .filter(fish -> fish.getLength() > 50)
                .collect(Collectors.groupingBy(Fishing::getFisherName, Collectors.counting()))
                .forEach((fisherName, count) -> System.out.println(fisherName + " -> " + count ));

        //Information about total weight of caught pikes and pike-perches
        System.out.println("\nInformation about total weight of caught pikes and pike-perches\n");
        double count = fishingList.stream()
                .filter(fish -> fish.getSpecies().equals("szczupak") || fish.getSpecies().equals("sandacz"))
                .mapToDouble(Fishing::getWeight)
                .reduce(0.0, Double::sum);
        System.out.printf("Total weight of caught pikes and pike-perches: %.2f", count);

        //Information about average fish length, how many fishes were caught and the longest and shortest fishes caught each day
        System.out.println("\nInformation about average fish length, how many fishes were caught and the longest and shortest fishes caught each day\n");
        fishingList.stream()
                .collect(Collectors.groupingBy(Fishing::getDay, Collectors.summarizingDouble(Fishing::getLength)))
                .forEach((Day, info) -> System.out.printf("\n" + Day + "\nAverage fish length: %.2f cm " +
                        "Number of fishes: %d, min: %.2f, max: %.2f", info.getAverage(), info.getCount(), info.getMin(), info.getMax()));
    }

    private void printData(List<Fishing> fishingList)
    {
        for (var fish: fishingList)
        {
            System.out.println(fish);
        }
    }
}
